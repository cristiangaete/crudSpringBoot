package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estudent;
import com.example.demo.repository.EstudentRepository;

@Service
public class EstudentServiceImpl implements EstudentService{
    
    @Autowired
    private EstudentRepository estudentRepository;

    @Override
    public List<Estudent> findEstudentAll() {
        
        return estudentRepository.findAll();
    }

    @Override
    public Estudent createEstudent(Estudent estudent) {
        
        return estudentRepository.saveAndFlush(estudent);
    }

    @Override
    public Estudent updateEstuden(Estudent estudent) {
        return estudentRepository.save(estudent);
        
    }

    @Override
    public void deleteEstudent(Long id) {
        estudentRepository.deleteById(id);
    }

    @Override
    public Optional<Estudent> getEstudent(Long id) {
       return estudentRepository.findById(id);
    }
    
    
}
