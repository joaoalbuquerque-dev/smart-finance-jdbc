package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class TransactionType implements Serializable {

    private Integer id;
    private String name;

    public TransactionType() {
    }

    public TransactionType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
