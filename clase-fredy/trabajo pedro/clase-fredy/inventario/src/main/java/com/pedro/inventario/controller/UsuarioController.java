package com.pedro.inventario.controller;

import com.pedro.inventario.entity.Usuario;
import com.pedro.inventario.repository.UsuarioRepository;
import com.pedro.inventario.exception.UsuarioNoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
   
    @Autowired
    private UsuarioRepository usuarioRepository;
   
    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
   
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
   
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException(
                "Usuario con ID " + id + " no encontrado"
            ));
    }
   
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioActualizado.getNombre());
                usuario.setEdad(usuarioActualizado.getEdad());
                usuario.setEmail(usuarioActualizado.getEmail());
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new UsuarioNoEncontradoException(
                "Usuario con ID " + id + " no encontrado"
            ));
    }
   
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException(
                "Usuario con ID " + id + " no encontrado"
            ));
        usuarioRepository.deleteById(id);
        return "Usuario con ID " + id + " eliminado correctamente";
    }
}

