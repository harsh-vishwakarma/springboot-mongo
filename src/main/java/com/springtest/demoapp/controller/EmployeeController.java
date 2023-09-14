package com.springtest.demoapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.demoapp.dao.EmployeRepositoryM;
import com.springtest.demoapp.entities.EmployeeMongo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {



    @Autowired
    private EmployeRepositoryM employeRepositoryMongo;



    
    // @GetMapping("/{id}/")
    // public Employee getEmployee(@PathVariable(value = "id") int id,  HttpServletResponse response){
    //     Employee employee = employeeRepository.findById(id);
    //     if(employee == null){
    //         response.setStatus(HttpStatus.NOT_FOUND.value());
    //     }
    //     return employee;
    // }

    @PostMapping("/mongo/")
    public ResponseEntity<EmployeeMongo> getEmployeeFromMongo(@RequestBody EmployeeMongo employeeMongo){
        EmployeeMongo employee = this.employeRepositoryMongo.save(employeeMongo);
        if(employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(employee));
    }

    @GetMapping("/mongo/{id}/")
    public ResponseEntity<EmployeeMongo> getEmployeeFromMongo(@PathVariable(value = "id") int id){
        Optional<EmployeeMongo> employee = this.employeRepositoryMongo.findById(id);
        if(employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(employee);
    }
}
