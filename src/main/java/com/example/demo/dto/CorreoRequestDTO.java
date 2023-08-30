package com.example.demo.dto;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CorreoRequestDTO {
    private String destinatario;
    private String asunto;
    private String cuerpo;
}
