# Learn Java Spring Framework

I will be documenting my Spring learning journey in this repo, defining each topic with code examples where necessary.

## What is the Spring Framework?
The Spring framework is a java framework that makes building java applications much easier and faster.
It presents the ability to easily build java web apps, serverless, cloud, microservices, automation and much more. 

## Tools worth mentioning
- Gradle: This is a widely used and mature build tool with an active community and a strong developer ecosystem.
- Maven: This is also a build tool

## Scaffolding a Spring Project
To start a new Spring project, you need an IDE and java development kit (JDK) downloaded.

Starting a new Spring Boot project is easier in two ways:
- Using Spring Initializr (start.spring.io) on the web: This tool is super easy to use because you can easily choose your build, edit project details and add dependencies on the go and then simply downloading the zip file on your local machine and launch in your IDE.
- Using IDE: You can easily click the "Start New Project" button on your IDE and choose `Spring Boot` as your generator, include the details of the project and add dependencies. 

## Spring MVC in Java

The Model View Controller (MVC) is one of the frequently used Software Architecture Design pattern. It separates applications functionality and promote organized programming.
- Model: This handles database related logics, communicate with the controller and can sometimes update the view.
- View: The view includes the UI shown to the user in the browser, usually HTML/CSS. It communicates with the Controller and has a Template Engine for dynamic computations.
- Controller: this is usually the functions or methods in a class which receives input from UI or URL, perform CRUD, gets data from _Model_ and pass to the _View_.

Spring is the most popular MVC framework. How Sprint handles MVC:
- First, there will be a `Request`, usually from the web browser.
- Any request coming in will be welcomed by a _Dispatch Servlet_. This servlet has the knowledge of all the controllers, view resolvers, views and all handler mapping.
- From servlet, the  request finds the right handler/controller for it by using the _handler mapping_ component/logic.
- Once the controller is found, the controller will invoke the right model to process the business logic.
- Once the business logic is processed, the controller then returns a Model and View object, and sent back to the Dispatcher Serlet.
- View is pointing to the UI response which will be sent back to the user/browser through View Resolver.

### Spring MVC Project folder structure
After scaffolding a Spring Project, the MVC approach basically has a `src` folder which contains the `main` folder. 
And in the `main` folder, there are two important folders the completes the MVC structure in Spring which are:
1. the `java` folder: this folder contains the package, and in the package exists your _Controller_ classes. 
```java
// src/main/java/me/stanleyazi/hellospringworld/ServingWebContentApp.java
@SpringBootApplication
public class ServingWebContentApp {

  public static void main(String[] args) {
    SpringApplication.run(ServingWebContentApp.class, args);
  }
}
// this file serves the contents to the web
```
```java
// example greeting page controller
// src/main/java/me/stanleyazi/hellospringworld/GreetingsController.java
@Controller
public class GreetingsController {
  @GetMapping("/greeting") // @GetMapping creates the "/greeting" route
  public String greeting(@RequestParam(value = "name", defaultValue = "Spring World") String  name, Model model) {
    model.addAttribute("name", name); // make param known to the page
    return "greeting"; // return "greeting.html", an HTMl file created in the template folder
  }
}
// take note of the @Controller and the structure of the Controller class
// you can add params with @RequestParam
```
You can add other Controller classes in this package folder. and then 
2. the `resource` folder contains `static` folder which can contain the static html files that are not controlled/dynamic, `templates` folder which contains every template `html` file controlled by your controller class, and the `application.properties` file which can contain environment variables.
```html
<!--static folder-->
<!--src/main/resources/static/index.html-->
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p>Get your greeting <a href="/greeting">here</a></p>
</body>
</html>
```
```html
<!--templates folder which containers all dynamic html file being controlled-->
<!--src/main/resources/templates/greeting.html-->
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <title>Hello Java Spring World</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p th:text="|Hello, ${name}!|" /> 
</body>
</html>
<!--notice how Thymeleaf parses the greeting.html template and evaluates 
the th:text expression to render the value of the ${name} parameter that 
was set in the controller.-->
```

This is the basic MVC project structure in Spring. So basically, everything happens in the `java` folder and the `resource` folder. 

There also exists a `test` folder in the `src` for writing tests.

## Spring RESTful Web Services
This is another unique way of building Web applications in Java using the Spring Framework. 

"REST (Representational State Transfer) has quickly become the de facto standard for building web services on the web because REST services are easy to build and easy to consume."

### Why REST?
What benefits? The web and its core protocol, `HTTP`, provide a stack of features:
- Suitable actions (`GET`, `POST`, `PUT`, `DELETE`, and others)
- Caching
- Redirection and forwarding
- Security (encryption and authentication)

### Spring RESTful project structure compared to Spring MVC project structure
Unlike the MVC Spring project, a RESTful Spring project doesn't need the `resource` folder or the html file, you can directly return the template in the Controller class in the `java` folder.
```java
// src/main/java/com/example/restservice/RestServiceApplication.java
@SpringBootApplication
public class RestServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestServiceApplication.class, args);
  }

}
```
```java
// src/main/java/com/example/restservice/GreetingController.java
@RestController // specify the controller
public class GreetingController {

	private static final String template = "Hello, %s!";

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}
}
```
Note: we are explicitly specifying the controller to be `@RestController`.

## @SpringBootApplication (from doc)
`@SpringBootApplication` is a convenience annotation that adds all the following:
- `@Configuration`: Tags the class as a source of bean definitions for the application context.
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package, letting it find the controllers.