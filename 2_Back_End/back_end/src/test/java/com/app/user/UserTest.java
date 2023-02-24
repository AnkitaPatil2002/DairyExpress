package com.app.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.dao.UserRepository;
import com.app.pojo.Role;
import com.app.pojo.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserTest {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Test
	void test() {
		assertNotNull(userRepo);
	}

	@Test
	void userTest() {
		List<User> list = List.of(
				new User("Mahendra", "Kolhe", "mkolhe23@gmail.com", encoder.encode("Mahikolhe"), "7350458073",
						Role.ADMIN),
				new User("Sujay", "Mathane", "sujaymathane96@gmail.com", encoder.encode("Sujay123"), "8237510542",
						Role.ADMIN));
		userRepo.saveAll(list);
	}
}
