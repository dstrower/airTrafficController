package com.atcs.rest.dequeue;

import com.atcs.impl.QueueingManager;
import com.atcs.objects.DequeueResult;
import com.atcs.objects.Plane;
import com.atcs.objects.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    QueueingManager queingManager = new QueueingManager();

    @RestController
    class MessageRestController {


        @Value("${connection.url}")
        private String connection;



        @RequestMapping(method = RequestMethod.GET,
        value = "/deplaneQueue",
                produces = MediaType.APPLICATION_JSON_VALUE)
        Plane dequeuePlane() {
            System.out.println("Connection = "+ connection);
            DequeueResult dequeueResult = queingManager.dequeuePlane(connection);
            if(dequeueResult.getStatus().isError()) {
                throw new IllegalArgumentException(dequeueResult.getStatus().getErrorMessage());
            }
            return dequeueResult.getPlane();
        }

        @ExceptionHandler
        void handleIllegalArgumentException(
                IllegalArgumentException e,
                HttpServletResponse response) throws IOException {

            response.sendError(HttpStatus.BAD_REQUEST.value());

        }
    }
}
