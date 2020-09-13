package org.example.modelui;

import java.io.Serializable;

public class Response implements Serializable {

    private String firstName;
    private String lastName;
    private String typeName;

    public Response() {}

    public Response(String firstName, String lastName, String typeName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setTypeName(typeName);
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
