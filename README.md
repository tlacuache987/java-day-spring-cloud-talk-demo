Microservices with Spring Cloud talk at Java Day 2017

Talk slides: 
https://goo.gl/xwQNSS

How to compile.

1. clone repository.

2. Register in hosts file:

127.0.0.1 eureka-host1

127.0.0.1 eureka-host2

this is for properly usage of Spring Cloud Config Server

3. Launch two instances of Eureka Server, enter to /spring-cloud-netflix-eureka folder and execute following comands:
(In two separated terminal or cmd)

xvhx@:~/java-day-spring-cloud-talk-demo/spring-cloud-netflix-eureka\
$ mvn spring-boot:run -Dspring.profiles.active=eureka-host1

and

xvhx@:~/java-day-spring-cloud-talk-demo/spring-cloud-netflix-eureka\
$ mvn spring-boot:run -Dspring.profiles.active=eureka-host2

This will launch two Spring Cloud Netflix Eureka Instances.

Open in browser http://eureka-host1:8761/, and http://eureka-host2:8762/

both Eureka servers are replica of each other.

4. Launch Spring Cloud Config Server

Enter into /spring-cloud-config-server/src/main/resources/bootstrap.properties and 
update spring.cloud.config.server.native.searchLocations property with the correct path of /spring-cloud-config-repo folder

In a new terminal or cmd enter into /spring-cloud-config-server folder and launch following command:

xvhx@:~/ws-java-day/spring-cloud-config-server\
$ mvn spring-boot:run

This command will launch the Spring Cloud Config Server, open following links.

http://localhost:9000/user-microservice/default/master (this link should respond something similar like)

{
"name": "user-microservice",
"profiles": [
"default"
],
"label": "master",
"version": null,
"state": null,
"propertySources": [
{
"name": "file:/Users/xvhx/ws-java-day/spring-cloud-config-repo/user-microservice.properties",
"source": {
"message": "Hola Mundo Java Day! (default) xD"
}
}
]
}

http://localhost:9000/salute-microservice/default/master




