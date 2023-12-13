package com.example.demo.serviceImpl;

import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.AzureService;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Service
public class AzureServiceImpl implements AzureService{

    private static final String storageConnectionString = "";
    private static final String containerName = "";
    private static final String hashkey = "";

    @Override
    public void uploadAzure(MultipartFile file)  {
        try {
            CloudStorageAccount account = 
            CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceBlodCliend = account.createCloudBlobClient();
            CloudBlobContainer container =
            serviceBlodCliend.getContainerReference(containerName);

            CloudBlockBlob blob = container.getBlockBlobReference(file.getOriginalFilename());
            InputStream fInputStream = file.getInputStream();
            blob.upload(fInputStream, file.getSize());
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
            
    }

    @Override
    public String dowloadAzure(String name) {
        try {
            CloudStorageAccount account = 
            CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceBlodCliend = account.createCloudBlobClient();
            CloudBlobContainer container = serviceBlodCliend.getContainerReference(containerName);

            CloudBlockBlob blockBlob = container.getBlockBlobReference(name);
            blockBlob.downloadToFile(name);
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return name;
    }

    @Override
    public String deleteAzure(String name) {
        try {
            CloudStorageAccount account = 
            CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceBlodCliend = account.createCloudBlobClient();
            CloudBlobContainer container = serviceBlodCliend.getContainerReference(containerName);

            CloudBlockBlob blockBlob = container.getBlockBlobReference(name);
            blockBlob.deleteIfExists();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
        
    }
    
}
