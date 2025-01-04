package com.example.orderService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Order {
    private String id;
    private String name;
    private String department;


    // Constructors, getters, and setters
    public Order() {}

    public Order(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
