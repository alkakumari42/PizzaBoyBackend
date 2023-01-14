Online Pizza Delivery App

//Modules

## Dependencies used

1. spring-boot-starter : "Core starter, including auto-configuration support, logging and YAML"
   https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-starters/spring-boot-starter/build.gradle
2. spring-boot-starter-web : "Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container"
   https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-starters/spring-boot-starter-web/build.gradle
3. spring-boot-starter-test - "Starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito"
   https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-starters/spring-boot-starter-test/build.gradle
4. spring-boot-starter-parent - "Parent pom providing dependency and plugin management for applications built with Maven"


## APIs
1. /health : To check if the server is running or not - returns "Healthy" if success


## Spring Features Used
### Annotations  
    Annotations make it easier to configure the dependency injection in Spring. Instead of using XML configuration files, we can use Spring Bean annotations on classes and methods to define beans. After that, the Spring IoC container configures and manages the beans.
1. @SpringBootApplication - It marks the main class of a Spring Boot application
   @SpringBootApplication encapsulates @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
2. @Configuration - Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
3. @ComponentScan
   1. we need to tell the Spring framework to look for Spring-managed components. @ComponentScan enables Spring to scan, detect, and register beans for classes annotated with @Component, @Controller, @Service, and @Repository.
   2. It is used with @Configuration annotation to specify the package for Spring to scan for components,
   3. Spring can also start scanning from the specified package, which we can define using basePackageClasses() or basePackages(). If no package is specified, then it considers the package of the class declaring the @ComponentScan annotation
4. @EnableAutoConfiguration - It enables Spring Boot to auto-configure the application context. Therefore, it automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us.
5. Difference between @EnableAutoConfiguration and @ComponentScan
   1. @EnableAutoConfiguration annotation tells Spring Boot to "guess" how you will want to configure Spring, based on the jar dependencies that you have added. For example, If HSQLDB is on your classpath, and you have not manually configured any database connection beans, then Spring will auto-configure an in-memory database. 
   2. @ComponentScan tells Spring to look for other components, configurations, and services in the specified package. Spring is able to auto scan, detect and register your beans or components from pre-defined project package. If no package is specified current class package is taken as the root package.
6. @RestController - This annotation in order to simplify the creation of RESTful web services. It's a convenient annotation that combines @Controller and @ResponseBody, which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
7. @Controller - We can annotate classic controllers with the @Controller annotation. This is simply a specialization of the @Component class, which allows us to auto-detect implementation classes through the classpath scanning.
8. @ResponseBody - We annotated the request handling method with @ResponseBody. This annotation enables automatic serialization of the return object into the HttpResponse.
9. @Component - https://medium.com/codex/spring-boot-controller-vs-component-c72814721fb7
10. @RequestMapping - 

//Build & Deploy Process

//Pom.xml - error faced

## Testing

https://www.baeldung.com/spring-boot-testing

https://www.vogella.com/tutorials/JUnit/article.html

## ToDo

1. Maven Configs - Mirrors, Repositories, distributionManagement
2. How to add custom scripts during build process
