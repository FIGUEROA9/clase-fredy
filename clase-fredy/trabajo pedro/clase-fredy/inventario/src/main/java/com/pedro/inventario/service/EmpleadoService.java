package com.pedro.inventario.service;

import com.pedro.inventario.entity.Producto;
import com.pedro.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
public class EmpleadoService {

    public void validarSalario(Double salario){
        if(salario < 0){
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
    }

    public Double calcularBono(Double salario){
        if(salario > 3000000){
            return salario + 500000;
        }
        return salario;
    }
}