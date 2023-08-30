package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    private StudentRepository estudentRepository;

    @Override
    public List<Student> findEstudentAll() {
        
        return estudentRepository.findAll();
    }

    @Override
    public Student createEstudent(Student estudent) {
        
        return estudentRepository.saveAndFlush(estudent);
    }

    @Override
    public Student updateEstuden(Student estudent) {
        return estudentRepository.save(estudent);
        
    }

    @Override
    public void deleteEstudent(Long id) {
        estudentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> getEstudent(Long id) {
       return estudentRepository.findById(id);
    }
    
    
}
