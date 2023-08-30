package com.example.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseMailDTO {
    private int codigoRetorno;
	private String glosaRetorno;
	private String resultado;
	private Date timestamp;
}
