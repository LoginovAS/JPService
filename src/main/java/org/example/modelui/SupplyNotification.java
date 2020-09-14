package org.example.modelui;

/**
 * External request to increase quantity for specified present type.
 */
public class SupplyNotification {

    private String presentType;
    private int quantity;

    public SupplyNotification() {

    }

    public String getPresentType() {
        return presentType;
    }

    public void setPresentType(String presentType) {
        this.presentType = presentType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
