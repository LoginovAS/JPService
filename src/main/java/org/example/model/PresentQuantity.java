package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "present_quantity")
public class PresentQuantity {

    private long id;
    private PresentType presentType;
    private int quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "present_type_id", referencedColumnName = "present_type_id")
    public PresentType getPresentType() {
        return presentType;
    }

    public void setPresentType(PresentType presentType) {
        this.presentType = presentType;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
