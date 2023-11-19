# User Service

## Overview
The User Service is a microservice designed to handle user authentication and account management. It utilizes JWT (JSON Web Token) authentication with RSA key pairs for securing endpoints.

## Token-based Authentication
The User Service employs a token-based authentication mechanism using JWT. When a user successfully logs in, a JWT token is generated, signed with an RSA private key, and sent to the client. 

Subsequent requests to secure endpoints should include this token in the Authorization header for authentication. 
Each service validates the token using public key.
JWT contains user-id and roles claims.

## Account Registration and Activation
* When a user registers, a confirmation token (UUID) is generated and associated with the user's account.
* An event is produced and sent to the Kafka 'account-topic'.
* The Notification Service consumes this event and sends an account activation email to the user.
* The user clicks on the activation link in the email, and the confirmation token is used to activate the account.
* User can log in after activating created account.