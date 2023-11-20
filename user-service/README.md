# User Service

## Overview

The User Service is a microservice designed to handle user authentication and account management. It utilizes JWT (JSON Web Token) authentication with RSA key pairs for securing endpoints.
Project uses Spring Security for security configuration and implementation of login/register flows.

## Token-based Authentication

The User Service employs a token-based authentication mechanism using JWT. When a user successfully logs in, a JWT token is generated, 
signed with an RSA private key, and sent to the client.

Subsequent requests to secure endpoints should include this token in the Authorization header for authentication. 
Each service validates the token using public key.
JWT contains user-id and roles claims.

## Account Registration and Activation

- When a user registers, a confirmation token (UUID) is generated and associated with the user's account.
- An event is produced (AccountCreatedEvent) and sent to the Kafka 'account-topic'.
- The Notification Service consumes this event and sends an account activation email to the user.
- The user clicks on the activation link in the email, and the confirmation token is used to activate the account.
- User can log in after activating created account.

### Roles

- Currently implemented roles include: ADMIN and USER roles.
- Role is defined and stored as a separate entity in the database.

## Spring Security Configuration

Spring security configuration can be found in com.microcommerce.userservice.configuration package. 

### Password Encryption

The application uses the BCryptPasswordEncoder to securely hash and store passwords. This is configured in the `passwordEncoder()` method,
providing a strong defense against unauthorized access to user credentials.

### Authentication Manager

The `authenticationManager()` method configures an Authentication Manager using a `DaoAuthenticationProvider` that integrates with 
a custom `UserDetailsService`. This ensures proper authentication with encrypted passwords and user details.

### Security Filter Chain

The security filter chain is set up in the `filterChain()` method, providing a comprehensive set of security measures:

- **CSRF Protection:** Cross-Site Request Forgery protection is disabled to facilitate seamless communication with the application.

- **Endpoint Authorization:** Specific endpoints are configured for different levels of access. The `/api/auth/**` endpoints are permitted for all, while all other requests require authentication.

- **OAuth2 Resource Server:** Configuration includes the use of JWT tokens for authorization, enhancing the security of resource access.

- **Session Management:** The session management policy is set to `STATELESS`, ensuring that no sessions are created.

### JWT Decoding and Encoding

JWT decoding is handled by the `jwtDecoder()` method, which uses the Nimbus JWT library and a provided public key. On the other hand, the `jwtEncoder()` method configures a Nimbus JWT encoder with a JSON Web Key (JWK) based on the RSA key pair.

### JWT Authentication Converter

The `jwtAuthenticationConverter()` method configures a `JwtAuthenticationConverter` with a `JwtGrantedAuthoritiesConverter`, defining the authorities claim name and prefix. This facilitates the extraction of roles from JWT tokens.

### JWT Generation

The `JwtGenerator` class is responsible for creating JWTs based on the provided authentication information.
The generated JWT plays a crucial role in the user login operation. In the `loginUser` method, after successful authentication, the `JwtGenerator` is utilized to create a JWT. This JWT is then included in the response to the client, providing a secure token for subsequent authorized requests.

## Kafka Configuration

- Kafka ProducerFactory and KafkaTemplate are configured using Java configuration classes present in <code>com.microcommerce.userservice.configuration</code> package.
- Kafka topic ('account-topic' for now) is configured using Java configuration classes as well.

## Further development

- JWT token validation flow should be reconsidered. With current implementation each microservice is validating the tokens on its own, which means every service needs access to public key.
- Keypair rotation should be implemented.
- Refresh tokens should be implemented.