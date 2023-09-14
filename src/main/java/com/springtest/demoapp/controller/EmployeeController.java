package com.springtest.demoapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springtest.demoapp.dao.EmployeRepositoryM;
import com.springtest.demoapp.entities.EmployeeMongo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final String UPLOAD_PATH = "D:\\Code\\Spring Boot\\springboot-mongo\\src\\main\\resources\\static\\images";
    // public final String UPLOAD_PATH = new ClassPathResource("static/images").getFile().getAbsolutePath();

    public EmployeeController() throws IOException {
        
    }
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

    @PostMapping("/upload/")
    public ResponseEntity<String> fileUpload(@RequestParam MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(UPLOAD_PATH);
        try {
            Files.copy(file.getInputStream(), Path.of(UPLOAD_PATH, File.separator, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
    }

    @PostMapping("/multi-upload/")
    public ResponseEntity<String> fileUpload(@RequestParam MultipartFile[] files){
        for (MultipartFile multipartFile : files) {
            System.out.println(multipartFile.getOriginalFilename());
        }
        // System.out.println(file.getOriginalFilename());
        return ResponseEntity.ok("uploaded");
    }
}
