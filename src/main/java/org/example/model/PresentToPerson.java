package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "present_to_person")
public class PresentToPerson {

    private long id;
    private PresentType presentType;
    private Person person;

    public PresentToPerson() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "present_type_id", referencedColumnName = "present_type_id")
    public PresentType getPresentType() {
        return presentType;
    }

    public void setPresentType(PresentType presentType) {
        this.presentType = presentType;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
