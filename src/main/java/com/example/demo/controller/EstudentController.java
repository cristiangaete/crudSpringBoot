package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Estudent;
import com.example.demo.service.EstudentService;

@RestController
@RequestMapping("/estudents")
public class EstudentController {

    @Autowired
    private EstudentService estudentService;

    @GetMapping
    public ResponseEntity<List<Estudent>> list() {

        List<Estudent> estudent = estudentService.findEstudentAll();
        return new ResponseEntity<List<Estudent>>(estudent, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudent> getEstudianteById(@PathVariable("id") Long id) {
        Estudent estudent = estudentService.getEstudent(id)
                .orElseThrow(() -> new ModeloNotFoundException("Estudiante no encontrado $id"));
        return new ResponseEntity<Estudent>(estudent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estudent> crearEstudiante(@RequestBody Estudent estudent) {
        estudentService.createEstudent(estudent);
        return new ResponseEntity<Estudent>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
	public Estudent updateEstudiante(@PathVariable("id") Long id,  @RequestBody Estudent estudent){
		Estudent dbestudiante =  estudentService.getEstudent(id).orElseThrow(() -> new ModeloNotFoundException("Estudiante No enocntrado"));
		dbestudiante.setNombres(estudent.getNombres());
		dbestudiante.setApellidos(estudent.getApellidos());
		dbestudiante.setEdad(estudent.getEdad());
		return estudentService.updateEstuden(dbestudiante);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Estudent> deleteEstudiante(@PathVariable("id") Long id){
		estudentService.deleteEstudent(id);
		return ResponseEntity.ok().build();
	}

}
