package com.atcs.objects;

/**
 * @author davidtrower
 * This class holds values for the Plane
 */
public class Plane {

    private int id;
    private String type;
    private String size;
    private String errorMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isValid() {
        boolean valid = true;
        if(id <= 0) {
            errorMessage = "Invalid id number";
            return false;
        }
        if(type.isEmpty()) {
            errorMessage = "The plane type value is missing";
            return false;
        }
        if(!typeIsValid(type)) {
            errorMessage = "The plane type must be either 'Emergency','VIP,'Passenger', or 'Cargo'.";
            return false;
        }
        if(size.isEmpty()) {
            errorMessage = "The plane size value is missing";
            return false;
        }
        if(!sizeIsValid(size)) {
            errorMessage = "The plane size must be either 'Small' or 'Large'.";
            return false;
        }
        return true;
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

    public String getErrorMessage() {
        return errorMessage;
    }
}
