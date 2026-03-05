package com.pedro.inventario.service;
import com.pedro.inventario.entity.Producto;
import com.pedro.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
public class ProductoService {
    private final ProductoRepository productoRepository;


    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    // Guardar producto con validaciones
    public String guardarProducto(Producto producto) {
        // Validar que el nombre no sea nulo ni esté vacío
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            return "Error: El nombre del producto es obligatorio";
        }
       
        // Validar que el precio sea mayor a 0
        if (producto.getPrecio() <= 0) {
            return "Error: El precio debe ser mayor a 0";
        }
       
        // Si todas las validaciones pasan, guardar el producto
        productoRepository.save(producto);
        return "Producto guardado exitosamente";
    }


    // Listar productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }


    // Buscar por categoría
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }


    // Eliminar producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
   
    public List<Producto> buscarPorRangoPrecio(double min, double max) {
        return productoRepository.buscarPorRangoPrecio(min, max);
    }
   
    public Page<Producto> listarPaginado(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
}

public Producto aplicarDescuento(Producto producto) {


    if (producto.getPrecio() <= 0) {
        throw new RuntimeException("Error: El precio debe ser mayor a 0");
    }


    if (producto.getPrecio() > 100000) {
        double descuento = producto.getPrecio() * 0.10;
        producto.setPrecio(producto.getPrecio() - descuento);
    }


    return producto;
}
