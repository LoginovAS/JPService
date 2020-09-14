package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "present_to_person")
public class PresentToPerson {

    private long id;
    private PresentType presentType;
    private Person person;
    private Date date;

    public PresentToPerson() {}

    public PresentToPerson(PresentType presentType, Person person) {
        this.presentType = presentType;
        this.person = person;
        this.setDate(new Date());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "present_type_id")
    public PresentType getPresentType() {
        return presentType;
    }

    public void setPresentType(PresentType presentType) {
        this.presentType = presentType;
    }

    @ManyToOne
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column(name = "present_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
