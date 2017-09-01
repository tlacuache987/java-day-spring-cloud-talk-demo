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
```
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
```

http://localhost:9000/salute-microservice/default/master (this link should respond something similar like)

```
{
"name": "salute-microservice",
"profiles": [
"default"
],
"label": "master",
"version": null,
"state": null,
"propertySources": [
{
"name": "file:/Users/xvhx/ws-java-day/spring-cloud-config-repo/salute-microservice.properties",
"source": {
"salute": "Hello I''m the proxy user microservice !"
}
}
]
}
```

5. Launch 5 instances of user-microservice

Open a new terminal or cmd and enter into /user-microservice folder and run following command:

xvhx@:~/java-day-spring-cloud-talk-demo/user-microservice\
$ mvn spring-boot:run -Dspring.profiles.active=dev -Dserver.port=9101

Open http://localhost:9101/user or http://localhost:9101/user.json or http://localhost:9101/user.xml in browser

This REST Endpoint should return something similar like:

```
{
"name": "Ivan García",
"message": "Hola Mundo Java Day! (dev), response by: 9101"
}
```

Look that the message property sends between round brackets the name of the active profile also, 
sends the port number of this microservice. This properties are set by the -Dspring.profiles.active and -Dserver.port
arguments given by the mvn spring-boot:run command.

Launch 4 more instances of this microservice, changing the active profile and port numbers.

Available profiles for user-microservice: default, prod and dev.
You can choose whatever unused port, I suggest using 9101, 9102, 9103, 9104 and 9105 just for keep things clear.

so, open new terminal windows and run:

xvhx@:~/java-day-spring-cloud-talk-demo/user-microservice\
$ mvn spring-boot:run -Dspring.profiles.active=dev -Dserver.port=9102

xvhx@:~/java-day-spring-cloud-talk-demo/user-microservice\
$ mvn spring-boot:run -Dspring.profiles.active=dev -Dserver.port=9103

xvhx@:~/java-day-spring-cloud-talk-demo/user-microservice\
$ mvn spring-boot:run -Dspring.profiles.active=dev -Dserver.port=9104

and

xvhx@:~/java-day-spring-cloud-talk-demo/user-microservice\
$ mvn spring-boot:run -Dspring.profiles.active=dev -Dserver.port=9105

Give some time to each microservice to get registered into Eureka, and review in each eureka-host1 and eureka-host2 if they are registered.

6. Launch 1 instance of salute-microservice

Using a terminal or cmd, enter into /salute-microservice folder and execute following command:

xvhx@:~/java-day-spring-cloud-talk-demo/salute-microservice\
$ mvn spring-boot:run

This microservice will be deployed using 9201 port by default.

Open in browser http://localhost:9201/salute, http://localhost:9201/salute.json or http://localhost:9201/salute.xml

This REST Endpoint should respond something similar like:

```
{
"salute": "Hello I''m the proxy user microservice !",
"user": {
"name": "Ivan García",
"message": "Hola Mundo Java Day! (dev), response by: 9101"
}
}

```
But the message property of the user object should vary on the profile and on port number, this is because 
salute-microservice (internally) calls to one user-microservice to get the User data. Each deployed user-microservice 
sends different profile and different port number, here is the example using Feing to remote calls using load balancer.


If you take down all user-microservice microservices (using ctrl+c on each terminal) and then you try to reach http://localhost:9201/salute.json 
again you will see the following response:

```
{
"salute": "Hello I''m the proxy user microservice !",
"user": {
"name": "Dummy User",
"message": "returned by hystrix circuit breaker"
}
}
```

Here is the example using Hystrix for failure tolerance. The circuit breaker opens if salute-microservice can't reach any
user-microservice. You can re-launch whatever number of user-microservice microservices again, let them register into eureka 
and then try to reach http://localhost:9201/salute.json again, the salute-microservice will not reach user-microservice 
as soon this will be deployed because hystrix and circuit-breaker implements the fail-fast principle so, it will take 
some time to salute-microservice for close the circuit-breaker and again start reaching user-microservice.

Enjoy !
