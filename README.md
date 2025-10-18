🚀 Spring Boot Application
This project uses Spring Boot to create a standalone, production-ready application with minimal setup.

✅ Benefits of Spring Boot
- Automatic dependency resolution to avoid version conflicts
- Minimal configuration required
- Embedded servers like Tomcat and Jetty (no need to deploy a WAR file)
- Absolutely no boilerplate code and no requirement for XML Configuration
- Provide production-ready features such as metrics, health checks
- Auto Configuration

📦 Spring Boot Starter Dependencies
Spring Boot offers a wide range of starter dependencies that simplify the setup of common Spring modules. These starters aggregate relevant libraries and configurations so you can get started quickly without worrying about version conflicts.

🔧 Popular Starters
 1. spring-boot-starter - Core starter including logging and auto-configuration support
 2. spring-boot-starter-web - Builds web applications using Spring MVC; includes embedded Tomcat
 3. spring-boot-starter-data-jpa  - Simplifies database access with Spring Data JPA and Hibernate
 4. spring-boot-starter-security  - Adds Spring Security for authentication and authorization
 5. spring-boot-starter-thymeleaf - Enables server-side rendering with Thymeleaf templates
 6. spring-boot-starter-test - Includes testing libraries like JUnit, Mockito, and Spring Test
 7. spring-boot-starter-actuator - Adds production-ready features like metrics, health checks, and monitoring
 
🧩 @SpringBootApplication
This is a composite annotation that combines three crucial Spring annotations:
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan

It marks the main class of a Spring Boot application and enables auto-configuration, component scanning, and configuration registration in one go

🛠️ @Configuration
Indicates that the class can be used by the Spring IoC container as a source of bean definitions.
It's a part of the core Spring Framework.
Classes annotated with @Configuration typically contain @Bean methods that define beans to be managed by Spring.
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}

⚙️ @EnableAutoConfiguration (often used via @SpringBootApplication)
Tells Spring Boot to automatically configure your application based on the dependencies present in the classpath.
For example, if Spring MVC is on the classpath, it will configure a web application automatically.
Example- @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

🔍 @ComponentScan
Enables Spring to scan for components (@Component, @Service, @Repository, @Controller) in the specified package and register them as beans.
By default, it scans the package of the class where it's declared and all sub-packages.
Example - @ComponentScan(basePackages = {"com.example.service", "com.example.repository"})


🔍 Internal Working Steps of a Spring Boot Application
🚀 1. Application Startup
The entry point is the main() method calling SpringApplication.run().
This initializes the Spring Boot framework and begins setting up the application context.

🔎 2. Determine Application Type
Spring Boot inspects the classpath to identify the application type:
Servlet-based (e.g., using Tomcat)
Reactive (e.g., using Netty)
Non-web (e.g., CLI or batch)

🏗️ 3. Create ApplicationContext
Based on the detected type, Spring Boot creates the appropriate context:
AnnotationConfigApplicationContext for non-web
WebApplicationContext for servlet apps
ReactiveWebApplicationContext for reactive apps

🧩 4. Load Initializers and Listeners
Spring Boot loads:
ApplicationContextInitializer for customizing context setup
ApplicationListener for handling lifecycle events

🔍 5. Perform Component Scanning
Spring scans for annotated classes:
@Component, @Service, @Repository, @Controller
These beans are registered in the ApplicationContext.

⚙️ 6. Apply Auto-Configuration
Using @EnableAutoConfiguration, Spring Boot configures beans based on classpath dependencies.
Example: If Spring MVC is present, it auto-configures a DispatcherServlet.

📦 7. Load External Configuration
Reads settings from application.properties or application.yml.
These influence server ports, database connections, logging, etc.

🏃 8. Run CommandLineRunner / ApplicationRunner
Beans implementing these interfaces are executed after context initialization.
Useful for executing startup logic or bootstrapping tasks.

🌐 9. Start Embedded Server
For web apps, Spring Boot starts an embedded server:
Tomcat (default), Jetty, or Undertow
The server listens on the configured port (default: 8080).

✅ 10. Ready to Serve Requests
The application is now fully initialized.
Controllers and REST endpoints are ready to handle incoming HTTP requests.







