# UberApp

UberApp is a backend service for a ride-hailing application inspired by Uber. This application is built using the Spring Boot framework and follows a monolithic/microservices architecture (based on requirements). The backend handles various functionalities such as user management, driver management, ride booking, payment processing, and real-time ride tracking.

## Features

- **User Management**: 
  - User signup, login, and profile management.
  - Role-based authentication for customers and drivers.
  
- **Driver Management**: 
  - Add, update, and manage driver profiles and availability.
  - Driver onboarding and document verification.

- **Ride Booking**:
  - Customers can book rides, track ongoing rides, and view ride history.
  - Dynamic pricing based on demand and supply.
  
- **Real-Time Location Tracking**:
  - Real-time ride tracking using location services.
  - Driver and customer receive live updates during a ride.

- **Payment Management**:
  - Integration with payment gateways supporting card, wallet, and cash payments.
  - Invoice generation and payment status updates.

- **Notifications**:
  - SMS and email notifications for ride updates and invoices.
  - In-app notifications for critical updates.

- **Security**:
  - JWT-based authentication and role-based access control.
  - Secure handling of user and payment data.

## Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Data JPA, Spring Security, Hibernate
- **Database**: PostgreSQL / MySQL
- **Messaging**: Twilio for SMS notifications, Gmail SMTP for email
- **API Documentation**: Swagger / OpenAPI
- **Testing**: JUnit, Mockito
- **Containerization**: Docker
- **Template Engine**: Thymeleaf (if applicable for admin panels or web views)

## Architecture

- **Core Services**:
  - User Service
  - Driver Service
  - Ride Management Service
  - Payment Service
  - Notification Service
  - Location Tracking Service

- **Flow Diagram (LLD)**:
  [View Flow Diagram](https://cs-prod-assets-bucket.s3.ap-south-1.amazonaws.com/Uber_Design_Flow_347dad012b.png)
  
  [View UML Diagram](https://cs-prod-assets-bucket.s3.ap-south-1.amazonaws.com/Uber_UML_Diagram_6bee8f589b.pdf)
