package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Estudent;

public interface EstudentService {

    public List<Estudent> findEstudentAll();
    public Optional<Estudent> getEstudent(Long id); 
    public Estudent createEstudent(Estudent estudent);
    public Estudent updateEstuden(Estudent estudent);
    public void deleteEstudent(Long id);
    

    
}
