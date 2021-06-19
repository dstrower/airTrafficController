package com.atcs.impl;

import com.atcs.objects.Plane;
import com.atcs.objects.Status;

public class QueingManager {

    public Status addPlaneToQueue(Plane plane) {
        Status status = new Status();
        if(plane.isValid()) {
            System.out.println("Adding to the queue.");
        } else {
            status.setError(true);
            status.setErrorMessage(plane.getSize());
            status.setHttpStatusCode(400);
        }
        return status;
    }
}
