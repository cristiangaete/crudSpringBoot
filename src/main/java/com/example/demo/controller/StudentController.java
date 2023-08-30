package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

import com.example.demo.dto.CorreoRequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.ResponseMailDTO;
import com.example.demo.dto.ResultEstudentDTO;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.service.EmailService;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/estudents")
public class StudentController {

    @Autowired
    private StudentService estudentService;

    @Autowired
    private EmailService emailService;

    Date date = Date.from(Instant.now());

    private Object csvFunction(HttpServletResponse response, List<String> headers, List<?> list, String name)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos, "UTF-8"));

        writer.println(String.join(",", headers));

        for (Object rowData : list) {
            writer.println(String.join(",", rowData.toString()));
        }

        writer.close();

        byte[] csvBytes = baos.toByteArray();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + name + ".csv");

        response.getOutputStream().write(csvBytes);
        response.getOutputStream().flush();
        return csvBytes;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> list() {

        ResultEstudentDTO resultDTO = new ResultEstudentDTO();
        ResponseDTO responseDTO = new ResponseDTO();

        List<Student> student = estudentService.findEstudentAll();

        resultDTO.setStudent(student);
        responseDTO.setCodigoRetorno(200);
        responseDTO.setGlosaRetorno("OK");
        responseDTO.setResultado(resultDTO);
        responseDTO.setTimestamp(date);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getEstudianteById(@PathVariable("id") Long id) {
        ResultEstudentDTO resultDTO = new ResultEstudentDTO();
        ResponseDTO responseDTO = new ResponseDTO();

        Optional<Student> student = estudentService.getEstudent(id);

        if (!student.isEmpty()) {
            resultDTO.setStudent(student);
            responseDTO.setCodigoRetorno(200);
            responseDTO.setGlosaRetorno("OK");
            responseDTO.setResultado(resultDTO);
            responseDTO.setTimestamp(date);
            return ResponseEntity.ok(responseDTO);
        } else {
            resultDTO.setStudent(student);
            responseDTO.setCodigoRetorno(404);
            responseDTO.setGlosaRetorno("OK");
            responseDTO.setResultado("No se encontraron resultados");
            responseDTO.setTimestamp(date);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> crearEstudiante(@RequestBody Student estudent) {

        ResponseDTO responseDTO = new ResponseDTO();
        estudentService.createEstudent(estudent);

        responseDTO.setCodigoRetorno(201);
        responseDTO.setGlosaRetorno("OK");
        responseDTO.setResultado("¡Estudiante creado con exito!");
        responseDTO.setTimestamp(date);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        // return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @PostMapping("/enviar-correo")
    public ResponseEntity<ResponseMailDTO> enviarCorreo(HttpServletResponse response,
            @RequestBody CorreoRequestDTO correoRequest, String string) throws IOException {

        ResponseMailDTO responseDTO = new ResponseMailDTO();
        List<String> headers = Arrays.asList("ID", "NOMBRE", "APELLIDO",
                "EDAD");

        List<Student> estudent = estudentService.findEstudentAll();

        Object cvs = csvFunction(response, headers, estudent, "estudents");

        emailService.enviarCorreoConAdjunto(correoRequest.getDestinatario(), correoRequest.getAsunto(),
                correoRequest.getCuerpo(), cvs, "estudents");

        responseDTO.setCodigoRetorno(200);
        responseDTO.setGlosaRetorno("OK");
        responseDTO.setResultado("Correo enviado exitosamente");
        responseDTO.setTimestamp(date);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateEstudiante(@PathVariable("id") Long id, @RequestBody Student estudent) {

        ResponseDTO responseDTO = new ResponseDTO();
        Student dbestudiante = estudentService.getEstudent(id)
                .orElseThrow(() -> new ModeloNotFoundException("Estudiante No enocntrado"));
        dbestudiante.setNombres(estudent.getNombres());
        dbestudiante.setApellidos(estudent.getApellidos());
        dbestudiante.setEdad(estudent.getEdad());
        Object data = estudentService.updateEstuden(dbestudiante);
        if (data != null) {

            responseDTO.setCodigoRetorno(200);
            responseDTO.setGlosaRetorno("OK");
            responseDTO.setResultado("Estudiante Actualizado");
            responseDTO.setTimestamp(date);
            return ResponseEntity.ok(responseDTO);
        } else {
            responseDTO.setCodigoRetorno(409);
            responseDTO.setGlosaRetorno("OK");
            responseDTO.setResultado("Estudiante NO Actualizado");
            responseDTO.setTimestamp(date);
            return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEstudiante(@PathVariable("id") Long id) {

        ResponseDTO responseDTO = new ResponseDTO();

        estudentService.deleteEstudent(id);

            responseDTO.setCodigoRetorno(200);
            responseDTO.setGlosaRetorno("OK");
            responseDTO.setResultado("Estudiante Eliminado");
            responseDTO.setTimestamp(date);
        return ResponseEntity.ok(responseDTO);
       
    }

}
