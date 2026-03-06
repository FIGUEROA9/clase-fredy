package com.pedro.inventario.controller;

import com.pedro.inventario.entity.Producto;
import com.pedro.inventario.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Producto producto) {
        String resultado = productoService.guardarProducto(producto);

        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo eliminar el producto con ID: " + id);
        }
    }

    @GetMapping("/precio")
    public ResponseEntity<List<Producto>> buscarPorRangoPrecio(
            @RequestParam double min,
            @RequestParam double max) {

        if (min > max) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Producto> productos = productoService.buscarPorRangoPrecio(min, max);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Producto>> listarPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(pagina, size);
        Page<Producto> productos = productoService.listarPaginado(pageable);
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/descuento")
    public ResponseEntity<Producto> aplicarDescuento(@RequestBody Producto producto) {
        try {
            Producto productoConDescuento = productoService.aplicarDescuento(producto);
            return ResponseEntity.ok(productoConDescuento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}