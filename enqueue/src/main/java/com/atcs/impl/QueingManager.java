package com.atcs.impl;

import com.atcs.data.DataDelegate;
import com.atcs.objects.Plane;
import com.atcs.objects.Status;

import java.util.ArrayList;
import java.util.List;

public class QueingManager {

    DataDelegate dataDelegate = new DataDelegate();

    List<Plane> planeList = new ArrayList<>();

    public Status addPlaneToQueue(Plane plane) {
        Status status = validatePlane(plane);
        if(!status.isError()) {
            System.out.println("Adding to the queue.");
            status = dataDelegate.addPlaneToQueue(plane);
        }
        return status;
    }

    public Status validatePlane(Plane plane) {
        Status status = new Status();
        if(plane.getId() <= 0) {
            status.setError(true);
            status.setErrorMessage("Invalid id number");
            return status;
        }
        if(plane.getType().isEmpty()) {
            status.setError(true);
            status.setErrorMessage("The plane type value is missing");
            return status;
        }
        if(!typeIsValid(plane.getType())) {
            status.setError(true);
            status.setErrorMessage("The plane type must be either 'Emergency','VIP,'Passenger', or 'Cargo'.");
            return status;
        }
        if(plane.getSize().isEmpty()) {
            status.setError(true);
            status.setErrorMessage("The plane size value is missing");
            return status;
        }
        if(!sizeIsValid(plane.getSize())) {
            status.setError(true);
            status.setErrorMessage("The size must be 'Small' or 'Large'");
            return status;
        }
        status.setError(false);
        return status;
    }

    private boolean sizeIsValid(String size) {
        if("Small".equalsIgnoreCase(size) || "Large".equalsIgnoreCase(size)) {
            return true;
        }
        return false;
    }

    private boolean typeIsValid(String type) {
        if("Emergency".equalsIgnoreCase(type) || "VIP".equalsIgnoreCase(type) || "Passenger".equalsIgnoreCase(type) || "Cargo".equalsIgnoreCase(type)) {
            return true;
        }
        return false;
    }

    /**
     * This calls the delegate to get the plane queue
     * @return List of Plane objects.
     */
    public List<Plane> getPlaneList() {
        return dataDelegate.getPlaneList();
    }
}
