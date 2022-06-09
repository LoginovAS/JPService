package org.example.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class PresentType {

    private long id;
    private String typeName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "present_type_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    // TODO: Check again and rewrite hashCode/equals section because of some doubts about id field.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PresentType)) return false;
        PresentType that = (PresentType) o;
        return id == that.id &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        int hash = Long.hashCode(id);
        hash = typeName != null ? 31 * hash + typeName.hashCode() : 0;

        return hash;
    }
}
