# airTrafficController

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




