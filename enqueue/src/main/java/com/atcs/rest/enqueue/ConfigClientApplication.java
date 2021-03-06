package com.atcs.rest.enqueue;

import com.atcs.impl.QueingManager;
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
import java.util.List;


@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    QueingManager queingManager = new QueingManager();

    @RestController
    class MessageRestController {

        @Value("${message:Hello default}")
        private String message;

        @Value("${connection.url}")
        private String connection;



        @RequestMapping(method = RequestMethod.GET,
        value = "/planeQueue",
                produces = MediaType.APPLICATION_JSON_VALUE)
        List<Plane> getPlaneList() {
            System.out.println("Connection = "+ connection);
            return queingManager.getPlaneList(connection);
        }



        @RequestMapping(method = RequestMethod.POST,
                value = "/addToQueue",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public String addToQueue(@RequestBody Plane plane) {
            Status status = queingManager.addPlaneToQueue(plane,connection);
            if(status.isError()) {
                throw new IllegalArgumentException(status.getErrorMessage());
            }
            return "OK";
        }


        @ExceptionHandler
        void handleIllegalArgumentException(
                IllegalArgumentException e,
                HttpServletResponse response) throws IOException {

            response.sendError(HttpStatus.BAD_REQUEST.value());

        }
    }
}
