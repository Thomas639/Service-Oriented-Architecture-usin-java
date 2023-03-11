# Service-Oriented-Architecture-using-java

Service-oriented architecture (SOA) is a method of software development that uses software components called services to create business applications. Each service provides a business capability, and services can also communicate with each other across platforms and languages. Developers use SOA to reuse services in different systems or combine several independent services to perform complex tasks.

Service provider

The service provider creates, maintains, and provides one or more services that others can use. Organizations can create their own services or purchase them from third-party service vendors.

Service consumer

The service consumer requests the service provider to run a specific service. It can be an entire system, application, or other service. The service contract specifies the rules that the service provider and consumer must follow when interacting with each other. Service providers and consumers can belong to different departments, organizations, and even industries.

Service registry/ broker

A service registry, or service repository, is a network-accessible directory of available services. It stores service description documents from service providers. The description documents contain information about the service and how to communicate with it. Service consumers can easily discover the services they need by using the service registry.


I have added two service providers.
  1. Random number Generator
  2. Hash function
  
These 2 services can be registered in service broker

Service consumer can find set of services through the service broker and utilize the service given by service provider.

I have also enabled authentication for service providers so that only registered users can add services into broker.

Run each program in a separate terminal at the same time to start.
