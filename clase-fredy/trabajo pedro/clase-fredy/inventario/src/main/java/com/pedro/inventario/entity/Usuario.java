package com.pedro.inventario.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;
    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;
    @Email(message = "El email debe tener formato válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true)
    private String email;
    // Constructor vacío
    public Usuario() { }
    // Constructor con parámetros
    public Usuario(Long id, String nombre, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }
   
    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getEmail() {
        return email;
    }   
    public void setEmail(String email) {
        this.email = email;
    }
}

