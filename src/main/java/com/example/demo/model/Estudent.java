package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="tbl_estudiante")
public class Estudent {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        @Column(name="nombres")
        String nombres;
        @Column(name="apellidos")
        String apellidos;

        @Column(name="edad")
        Integer edad;
}
