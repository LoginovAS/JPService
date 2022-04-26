package org.example.modelui;

public class SuccessResponse extends Response {

    public SuccessResponse(String firstName, String lastName, String present) {
        super(firstName, lastName, present);
    }

    @Override
    public String addAction() {
        return "received";
    }

    @Override
    public String addReason() {
        return "good behavior";
    }
}
