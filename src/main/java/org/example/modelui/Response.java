package org.example.modelui;

/**
 * Response for user's HTTP requests. Can be success or bad.
 */
public abstract class Response {

    private String message;

    public Response(String firstName, String lastName, String present) {
        StringBuilder builder = new StringBuilder(firstName + " " + lastName);
        builder
                .append(" ")
                .append(addAction())
                .append(" ")
                .append(present)
                .append(" because of ")
                .append(addReason());

        message = builder.toString();
    }

    public Response(String message) {
        this.message = message;
    }

    protected abstract String addAction();
    protected abstract String addReason();

    public String getMessage() {
        return message;
    }
}
