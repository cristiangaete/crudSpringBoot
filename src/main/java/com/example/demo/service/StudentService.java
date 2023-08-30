package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Student;

public interface StudentService {

    public List<Student> findEstudentAll();
    public Optional<Student> getEstudent(Long id); 
    public Student createEstudent(Student estudent);
    public Student updateEstuden(Student estudent);
    public void deleteEstudent(Long id);
    

    
}
