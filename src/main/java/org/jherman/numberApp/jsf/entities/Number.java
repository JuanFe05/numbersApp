package org.jherman.numberApp.jsf.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "numeros")
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numeroingresado", nullable = false)
    private Float numeroingresado;

    public Number() {
    }

    public Number(Long id, Float numeroingresado) {
        this.id = id;
        this.numeroingresado = numeroingresado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNumeroingresado() {
        return numeroingresado;
    }

    public void setNumeroingresado(Float numeroingresado) {
        this.numeroingresado = numeroingresado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return Objects.equals(id, number.id);
    }

}
