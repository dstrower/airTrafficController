# airTrafficController

##Requirements
Objective: Using a language of your choice, develop an Air Traffic Control system (ATCS) that meets the requirements listed below. <br>
The ATCS will allow the queuing and dequeuing of aircraft (AC). <br>

**Layers** <br>
The ATCS should have the following layers:
1. REST layer <br>
a. This layer provides access to the ATCS. <br>
b. It should provide endpoints for all of the ATC methods. <br>
c. Appropriate error messages should be displayed / returned. <br>
Ex: A method was called before the system has booted. <br>
c. It must be a REST interface. <br>
Hint: Follow traditional REST best practices
1. Logic layer <br>
a. This layer provides the logical operations of the ATCS. <br>
b. The it should provide the following functions <br>
Boot – start the system <br>
Enqueue – Add an AC to the queue <br>
Dequeue- Remove an AC from the queue based on priority <br>
List – Provide the current order of the AC in the queue <br>
It should access the queue through the data layer. <br>
1. Data layer
a. This layer holds the data structure for the queue. <br>
b. The queue must be accessed through this layer, not directly. <br>

**Objects** <br>
The ATCS should have the following objects with the listed properties, <br>

Aircraft (AC) <br>
ID <br>
This field will store the AC id <br>
Type <br>
Emergency, VIP, Passenger, or Cargo <br>
Size <br>
Small or Large <br>

**Dequeue Logic**
The ATCS should dequeue planes with the following logic <br>
VIP are more important than other types, except Emergency. <br>
Emergency AC always have the highest priority. <br>
Passenger ACs have a higher priority than Cargo ACs. <br>
Large ACs of a given type have priority over Small ACs of the same type. <br>
Earlier enqueued ACs of a given type and size have precedence over later enqueued ACs of the same type and size. <br>

**Requirements**
Implement the ATCS with the requirements specified above. <br>
1. Assume multiple users will concurrently access they system. Multiple air traffic controllers will ask for the next plane to be dequeued. <br>
1. To the greatest extent possible, show all of your code.  Feel free to use standard libraries provided by your chosen implementation language. <br>
1. Please send your complete project including artifacts.  If the project is built in Java, please use Maven, Gradle or Ivy/ANT for the build.  <br>
1. Please include a README me file with instructions on how to run the project locally.  <br>
**Optional Bonuses:** <br>
1. Use a non in-memory/persistent datastore in the data layer. <br>
1. Implement a UI to show the queue and add/remove ACs. <br>





## Basic Design
This application uses two microservices to service 3 REST calls <br>
to allow the user to view, add to and remove from the airplane queue. <br>
The data is stored in an AWS mysql database. <br>
The following picture shows the architecture. <br>
![High Level Design](https://github.com/dstrower/airTrafficController/blob/master/images/microservice.jpg)


## Requirements to Run Program

1. Must have java
1. Must have maven installed.
1. Must have git installed.

## Instructions to Install

1. Get all the code out of git using the following command: <br>
   `git clone https://github.com/dstrower/airTrafficController.git`
1. cd airTrafficController/   
1. There are several projects that need to be build.
    1. Build the  atcs-web-project
        1. cd atcs-web-project
        1. `mvn clean install`
        1. cd ..
    1. Build the cloudConfig project.
        1. cd cloudConfig
        1. `mvn clean install`
        1. cd ..
    1. Build the dequeue project.
        1. cd dequeue
        1.  `mvn clean install`
        1. cd ..
    1. Build the enqueue project.
        1. cd enqueue
        1. `mvn clean install`
        1. cd ..
    1. Build the eureka-server
        1. cd eureka-server
        1. `mvn clean install`
        1. cd ..
    1. Build the zuul-server
        1. cd zuul-proxy
        1. `mvn clean install`
        1. cd ..
1. Change the permissions on the scripts so that they can be run.
    1. chmod 777 *.sh
1. Now start the projects. Each project will require a shell prompt.
    1. Start the euruka_server
        1. ./startEureka.sh
        1. Verify that it is running by going to [Eureka](http://localhost:8761/)
        1. You should see the Eureka page running on port 8761 <br>
           ![Eureka](https://github.com/dstrower/airTrafficController/blob/master/images/EurekaStart.png)
    1. Start cloudConfig
        1. ./startCloudConfig.sh
    1. Start the zuul-proxy
        1.  ./startZuul.sh
    1. Start the enqueue microservice.
        1. ./startEnqueue.sh
    1. Start the dequeue microservice.
        1. ./startDequeue.sh
    1. Verify that 3 microservices are running by doing a refresh of Eureka [Eureka](http://localhost:8761/) <br>
       ![Eureka](https://github.com/dstrower/airTrafficController/blob/master/images/Eureka3.png)
1. Now start the webserver
    1. ./startWebServer.sh
    
## Running the Program

Point your browser to [Main Page](http://localhost:8080/) <br>
![Eureka](https://github.com/dstrower/airTrafficController/blob/master/images/mainWebpage.png)




