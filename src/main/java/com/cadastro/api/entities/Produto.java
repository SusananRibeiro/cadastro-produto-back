package com.cadastro.api.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.SQLDelete;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
@Entity
@SQLDelete(sql = "UPDATE produto SET delete_at = now() WHERE id=?") // para fazer o Soft-Delete, "now()" substitui pela data atual
@Where(clause = "delete_at is null") // vai criar uma nova coluna
public class Produto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column // (nullable = false)
    private String nome;
    @Column // (nullable = false)
    private String categoria;
    @Column
    private double valor;
    @Column
    private LocalDateTime delete_at; // Soft Delete

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(LocalDateTime delete_at) {
        this.delete_at = delete_at;
    }
}
