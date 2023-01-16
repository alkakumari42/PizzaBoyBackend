Online Pizza Delivery App

## Modules

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
2. /menu/modify : To update/modify the menu
3. /menu : get menu


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
10. @RequestMapping - this annotation is used to map web requests to Spring Controller methods.Starting with Spring 3.1, the @RequestMapping annotation now has the produces and consumes attributes, (https://www.baeldung.com/spring-requestmapping)
11. @Autowired - Starting with Spring 2.5, the framework introduced annotations-driven Dependency Injection. The main annotation of this feature is @Autowired. It allows Spring to resolve and inject collaborating beans into our bean. https://www.baeldung.com/spring-autowire
12. @PostMapping - The @PostMapping is a specialized version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.POST). The @PostMapping annotated methods handle the HTTP POST requests matched with the given URI expression. As a best practice, always specify the media types (XML, JSON etc.) using the ‘consumes’ and ‘produces’ attributes.
13. @GetMapping - The @GetMapping annotated methods handle the HTTP GET requests matched with the given URI expression. https://howtodoinjava.com/spring5/webmvc/controller-getmapping-postmapping/#:~:text=The%20%40PostMapping%20is%20a%20specialized,(XML%2C%20JSON%20etc.)
14. @Repository - It is a specialization of @Component annotation. @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects. https://www.digitalocean.com/community/tutorials/spring-repository-annotation
15. @Data - @Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor together. It generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) and beans. https://projectlombok.org/features/Data
16. @Value("${amazon.dynamodb.endpoint}") - This annotation can be used for injecting values into fields in Spring-managed beans, and it can be applied at the field or constructor/method parameter level from properties file or from system variable as well. https://www.baeldung.com/spring-value-annotation
17. @Bean(?) - It is applied on a method to specify that it returns a bean to be managed by Spring context. Spring Bean annotation is usually declared in Configuration classes methods.
18. 


## DynamoDB Related Annotations
1. @DynamoDBTable - Identifies the target table in DynamoDB. (https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.Annotations.html#DynamoDBMapper.Annotations.DynamoDBTable)
2. @DynamoDBHashKey - Maps a class property to the partition key of the table. The property must be one of the scalar string, number, or binary types. The property can't be a collection type. It maps the primary key of the table.
3. @DynamoDBAutoGeneratedKey - It Marks a partition key or sort key property as being autogenerated. @DynamoDBMapper generates a random UUID when saving these attributes. Only String properties can be marked as autogenerated keys.
4. @DynamoDBAttribute - Maps a property to a table attribute. By default, each class property maps to an item attribute with the same name. However, if the names are not the same, you can use this annotation to map a property to the attribute.
5. @EnableDynamoDBRepositories(?) - This annotation is used to enable DynamoDB repositories. We need to add the Spring Data repository base package here. Then DynamoDB repository capabilities will be added to our Spring data repositories.
6. @EnableScan - 

## Build & Deploy Process
1. Install Git, mvn and JDK - set MAVEN_HOME & JAVA_HOME to env variable and path (Windows specific)
2. Build Steps : mvn clean && mvn install && mvn compile && mvn spring-boot:run

//Pom.xml - error faced

## Testing
2 types of testing - Unlike with integration or functional tests, where the real system is being tested as a whole, unit tests should focus on a single class. Everything else should be either a simple class or a mock. Mockito now seems to be the dominant Mocking framework in Java, and is now in its second version. Spock is also a great solution that we will explore in a future article.

https://semaphoreci.com/community/tutorials/stubbing-and-mocking-with-mockito-2-and-junit
1. Unit Testing : https://www.vogella.com/tutorials/JUnit/article.html
2. Integration Testing - https://www.baeldung.com/spring-boot-testing

## Unit Test (Junit)
1. @Test on Method - using Junit - (use @Test(expected = RuntimeException.class) - to verify exception)
2. @Before - runs before each test, use to do setup 
3. @After - runs after each test, used to do after test clean up


## Unit Test (Mockito)
1. @Mock - is used to create mock of any dependency object. Or use mock(Anyclass.class) method.
2. @InjectMocks - is used to inject all the mocked(@Mock) class, defined in the class. 
3. MockitoAnnotations.openMocks(this) - to make @Mock & @InjectMocks effective

## Integration Test

## ToDo

1. Maven Configs - Mirrors, Repositories, distributionManagement
2. How to add custom scripts during build process
