package org.example.modelui;

/**
 * User present request.
 */
public class Request {

    private String firstName;
    private String lastName;
    private String presentType;

    public Request() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPresentType() {
        return presentType;
    }

    public void setPresentType(String presentType) {
        this.presentType = presentType;
    }
}
