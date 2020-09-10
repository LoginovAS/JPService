package org.example.model;

public class PresentQuantity {

    private long id;
    private PresentType presentType;
    private int quantity;

    public PresentQuantity() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PresentType getPresentType() {
        return presentType;
    }

    public void setPresentType(PresentType presentType) {
        this.presentType = presentType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
