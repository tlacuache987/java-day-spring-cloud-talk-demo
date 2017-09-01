Microservices with Spring Cloud talk at Java Day 2017

Presentación URL:
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




