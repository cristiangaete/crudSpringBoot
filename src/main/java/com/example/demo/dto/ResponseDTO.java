package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseDTO {
    private int codigoRetorno;
	private String glosaRetorno;
	private Object resultado;
	private Date timestamp;
}
