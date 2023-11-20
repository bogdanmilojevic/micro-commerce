# Notification Service

## Overview

Notification Service is a service which listens to various kafka topics for events, processes those events and sends
relevant notification based on the received event. Current implementation is able to send simple mail messages as well as HTML5 emails.
The service is using gmail SMTP server for sending emails.

Event listeners defined in <code>com.microcommerce.notificationservice.listener</code> represent entry point for receiving all events. Once an event is 
received, an appropriate event handler which deals with business logic should be called.

## Kafka Configuration

Kafka consumer is configured using Java configuration classes present in <code>com.microcommerce.notificationservice.config</code> package.
Each event has its own listener configured.

## Events

### AccountCreatedEvent

**Topic:** account-topic<br>
```java
public class AccountCreatedEvent {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String activationToken;
}
```

- Event produced by user service once new account is created successfully.
- Event contains basic account data as well as account activation token.
- AccountCreatedEventListener listens for the event.
- Simple email with account confirmation link is sent once the event is processed.

### OrderCreatedEvent

**Topic:** notification-topic<br>

```java
public class OrderCreatedEvent {
    private String orderNumber;
    private List<EventOrderItemDto> orderItems;
    private UUID userId;

    // TODO: Add field User(uuid, email, firstName, lastName)
}
```

- Event produced by order service once new order is placed successfully.
- OrderCreatedEventListener listens for the event.
- Order confirmation HTML5 email with order items as well as total value is sent one the event is processed.
- HTML5 template can be found in resources/templates directory.

## Further Development

- User data should be added to OrderCreatedEvent so order confirmation emails can be sent to the appropriate user. Current implementation is hard codes the address where emails are sent.
- When a product is low in stock an email should be sent to the administrators. ProductLowInStock event, listener and handler should all be implemented.
- Notification service could be used for real-time notification as well. Web-hook implementation should be considered.
- Code refactor and restructure is needed. All events should have listener configuration, listener class and event handler class.