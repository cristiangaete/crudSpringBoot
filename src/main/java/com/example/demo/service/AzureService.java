package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AzureService {

    void uploadAzure(MultipartFile file);

    String dowloadAzure(String name);

    String deleteAzure(String name);

    
}
