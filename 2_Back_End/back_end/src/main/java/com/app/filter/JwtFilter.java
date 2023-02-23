package com.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ui.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.service.UserDetailServiceImpl;
import com.app.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	public JwtFilter() {
		System.out.println("------in jwt filter--------");
	}

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private UserDetailServiceImpl userdetailsImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("-------in jwt filter------");
		String authHeader = request.getHeader("Authorization");
		String token = null;
		Long id = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			id = jwtutil.extractIdFromToken(token);
		}
		if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userdetailsImpl.loadUserById(id);
			if (jwtutil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
