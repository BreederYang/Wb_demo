package com.mybaitspuls.demo.entity;

public class Picture {
    private Integer id;
    private String uuid;
    private String name;

    public Picture() {
    }

    public Picture(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
