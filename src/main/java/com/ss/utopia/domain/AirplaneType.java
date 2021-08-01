package com.ss.utopia.domain;

public class AirplaneType {

    private Integer id;
    private Integer maxCapacity;

    public AirplaneType() {}

    public AirplaneType(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public AirplaneType(int id, Integer maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return id.equals(that.id);
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
