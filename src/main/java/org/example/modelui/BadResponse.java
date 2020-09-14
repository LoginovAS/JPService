package org.example.modelui;

public class BadResponse extends Response {

    public BadResponse(String firstName, String lastName, String present) {
        super(firstName, lastName, present);
    }

    @Override
    protected String addAction() {
        return "cannot receive";
    }

    @Override
    protected String addReason() {
        return "bad behavior";
    }
}
