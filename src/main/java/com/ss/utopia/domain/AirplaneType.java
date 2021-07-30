package com.ss.utopia.domain;

public class AirplaneType {

    private int id;
    private int maxCapacity;

    public AirplaneType() {}

    public AirplaneType(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public AirplaneType(int id, int maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneType that = (AirplaneType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AirplaneType{" +
                "id=" + id +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
