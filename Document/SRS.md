# Dairy Express
## Document:
System Requirement Specification Document

## Title:
System Requirement for Online Dairy Products delivery portal

## Team:
Direct Customer,Indirect Customer, Architect,Business Analyst, Quality Assurance Team, System Analyst

## Objective(Purpose):
DairyExpress Platform can be the greatest blessing for the customer’s everyday morning needs of dairy products. With this platform customer can register and order their daily requirements of dairy products and receives assured delivery at the desire time every day at their doorstep.

## Scope:
- This System allows Admin to manage thier products from Dairy Exprss Catalog based on their availablity, And also manage the Employee and Delivery Person. 
- Customer will be able to login to portal, order the products and can view the order history.
- Employee will able to login to portal and also able to assign order to delivery person.
- Delivery person will able to login to portal and deliver the order which is assign by employee.

## Defination:
<ul>
  <li>QA:  Quality Assurance</li>
  <li>Portal: Peronalized Online Web Application</li>
  <li>MIS: Management Information System</li>
  <li>CRM: Customer Relation Managment</li>
  <li>BI:  Business Intelligence</li>
  <li>KPI:Key Perfomornace Indicator</li>
  <li>Dashboard: Personalized information presented using  BI techniques such grid, score card, graph, KPI</li>
 </ul>

## Functional Requirements
Any annonymous User will be able to view different products avaialble for sale. Any User will be able select product to view from categories avaialble.Employee will be to track and maintain stock of products available for sale. Employee will be able to raise reqests for product updatation to Admin.

## NonFunctional Requirement:
### Security
Registered Customer will allowed to place an order. Each stakeholder will be to access system through authentication process. Who are you ? System will provide access to the content , operations using Role based security (Authorization) (Permissions based on Role) Using SSL in all transactions which will be performed stakeholder. It would protect confidential information shared by system to stake holder of Shared by stakeholder to system. System will automatically log of all stakeholder after some time due to inactiveness. System will block operations for inactive stakeholder and would redirect for authentication. System will internally maintain secure communiction channel between Servers ( Web Servers, App Servers, databse Server) Sensitive data will be always encrypted across communcation. User proper firewall to protect servers from out side fishing, velnerable attacks.

### Reliability
The system will backup business data on regular basis and recover in short time duration to keep system operational Continous updates are matained , continous Adminstration is done to keep system operational. During peak hours system will maintain same user experaince by managing load balacning .

### Availability
uptime: 24* 7 available 99.999%

### Maintainability:
A Commercial database software will be used to maintain System data Persistence. A readymade Web Server will be installed to host online DairyExpress portal (Web Site) to management server capabilities. IT operations team will easily monitor and configure System using Adminstrative tools provided by Servers. Separate enviornment will be maintained for system for isolation in production, testing, and development.

### Portablility:
PDA: Portable Device Application System will provide portable User Interface ( HTML, CSS, JS) through users will be able to access online DairyExpress portal. System can be deployed to single server, multi server, to any OS, Cloud (Azure or AWS or GCP)

### Accessibility:
only registered customer will be able to place an order after authentication. BOD team will be able to view daily, weekly, monthly, annual businss Growth throgh customized dashboard.

### Durability:
System will retain customer shopping cart for 15 minutes even though customer loose internet connection and join again. System will maintain wishlist for customer . customer will be able to add products from wishlist and add to shopping cart whenever needed. System will implement backup and recovery for retaining stake holders data, bussiness operation data and business data over time.

### Efficiency:
on Festival season, maximum number of users will place order, view products with same response time. System will be able to manage all transactions with isolation.

### Modularity:
System will designed and developed using reusable, independent or dependent business senarios in the form of modules. These modules will be loosely coupled and highly cohesive. System will contain CRM , Inventory , shopping cart, order processing, payment processing, Delivery module, membership and Roles managment modules.

### Scalability:
System will be able to provide consistent user exeprience to stake holder as well as vistors irrespective of load.

### Safety:
online DairyExpress portal will be secure from malicious attack, fishing. online DairyExpress portal functionalilites are protected from outside with prper firewall configuration. online DairyExpress portal will be always kept updated with latest anit virus software. Bussiness data will be backed up periodically to ensure safty of data using increamental back up strategy. Role based security will be applied for Application data and operations accessibility.















