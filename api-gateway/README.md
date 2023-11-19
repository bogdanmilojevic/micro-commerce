# API Gateway

Api Gateway serves as an entry point to the entire backend. Backend consumer can use single base URL for making
API calls instead of calling individual services and using theirs URLs.

API Gateway receives HTTP requests and routes them to the appropriate service
based on routing configuration in **application.yml** file

## Configuration Example

Following configuration is defining a route with the ID "product-service-route" that forwards 
requests with a path starting with "/product" to a load-balanced URI 
(either from the PRODUCT_SERVICE_URI environment variable or the default "product-service"). 

The request path is then modified by adding the "/api" prefix before forwarding it to the target service. Keep in mind that the gateway registers itself
with Spring Cloud Eureka discovery server, allowing us to use defined service name (e.g. "product-service") as the uri instead of actual uri with host and port.


```yaml
- id: product-service-route
  uri: lb://${PRODUCT_SERVICE_URI:product-service}
  predicates:
    - Path=/product/**
  filters:
    - PrefixPath=/api
```

* id - Unique identifier for the route.
* uri - The uri specifies the target URI to which the request will be forwarded. 
* predicates - Defines conditions that must be met for route to be activated. In this case, the route will be activated if the request path matches /product/**.
* filters - Modifies the request before routing it. Here, the PrefixPath filter is applied, adding /api as a prefix to the request path.