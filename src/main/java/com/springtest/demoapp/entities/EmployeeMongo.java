package com.springtest.demoapp.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "employees")
@Getter @Setter @NoArgsConstructor
public class EmployeeMongo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String city;
    
    private String status;

    public EmployeeMongo(int id, String name, String city, String status) {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", status=" + status + "]";
    }   
}
