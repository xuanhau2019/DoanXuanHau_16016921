package com.example.onthicuoikysqlitecontenprovider;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String gioiTinh;
    private int phoneNumber;

    public Employee(int id, String name, String gioiTinh, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.gioiTinh = gioiTinh;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return  "id=" + id +
                "\n name='" + name +
                "\n gioiTinh='" + gioiTinh +
                "\n phoneNumber='" + phoneNumber;
    }
}
