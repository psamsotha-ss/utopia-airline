package com.ss.utopia.domain;

public class Airplane {

    private Integer id;
    private AirplaneType type;

    public Airplane() {}

    public Airplane(AirplaneType type) {
        this.type = type;
    }

    public Airplane(Integer id, AirplaneType type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return id.equals(airplane.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
