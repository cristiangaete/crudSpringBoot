package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.AzureService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cloud")
public class AzureController {

    @Autowired
    private AzureService azureService;

      @GetMapping("/dowload")
    public ResponseEntity<String> dowloadAzure(@PathVariable String name) {
      String result =  azureService.dowloadAzure(name);
       return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    
    @PostMapping("/upload")
    public String uploadAzure(@RequestParam("file") MultipartFile file) {
        // Genera un nombre único para el archivo en Azure Blob Storage
        String blobName = "nuevo" + file.getOriginalFilename();

        try (InputStream fileStream = file.getInputStream()) {
                azureService.uploadAzure(file);


            return "Archivo subido exitosamente a Azure Blob Storage con el nombre único: " + blobName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir el archivo a Azure Blob Storage.";
        }
    }

        @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAzure(@PathVariable String name) {
      String result =  azureService.deleteAzure(name);
       return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
