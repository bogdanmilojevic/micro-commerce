# Discovery Server (Eureka) README

## Overview

The Discovery Server is a microservice responsible for service discovery within a microservices architecture. It uses Netflix Eureka to manage and register microservices, allowing them to discover and communicate with each other dynamically.

## Configuration

The Discovery Server is configured using the `application.yml` file. Below are key configurations:

```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
server:
  port: 8761
```
* instance.hostname: The hostname of the Discovery Server.
* client.register-with-eureka: Set to false to prevent the Discovery Server from registering itself with Eureka.
* client.fetch-registry: Set to false to disable fetching the registry information.
* server.port: The port on which the Discovery Server will run.

### How it Works
**1. Service Registration:**

Microservices register themselves with the Discovery Server on startup.
The Discovery Server keeps a registry of registered microservices.

**2. Service Discovery:**

Microservices use the Discovery Server to discover the locations (host and port) of other microservices.
This enables dynamic communication between microservices without hardcoding service endpoints.

**3. Heartbeat and Eviction:**

Microservices send regular heartbeats to the Discovery Server to indicate they are still alive.
If a microservice fails to send heartbeats, the Discovery Server evicts it from the registry.

### Accessing the Eureka Dashboard
The Eureka Dashboard is accessible at http://localhost:8761 (assuming the default port). This web-based dashboard provides insights into the registered microservices, their status, and other relevant information.