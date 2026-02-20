package com.pedro.inventario.entity;
import jakarta.persistence.*;

@Entity
public class Producto {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 private String categoria;
 private double precio;
 private int stock;

 // Constructor vacío
 public Producto() { }

 // Constructor con parámetros
 public Producto(Long id, String nombre, String categoria, double precio, int stock) {
 this.id = id;
 this.nombre = nombre;
 this.categoria = categoria;
 this.precio = precio;
 this.stock = stock;
 }

 // Getters y Setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 public String getNombre() { return nombre; }
 public void setNombre(String nombre) { this.nombre = nombre; }
 public String getCategoria() { return categoria; }
 public void setCategoria(String categoria) { this.categoria = categoria; }
 public double getPrecio() { return precio; }
 public void setPrecio(double precio) { this.precio = precio; }
 public int getStock() { return stock; }
 public void setStock(int stock) { this.stock = stock; }
}