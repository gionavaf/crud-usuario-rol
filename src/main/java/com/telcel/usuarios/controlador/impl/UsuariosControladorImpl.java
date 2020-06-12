/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.controlador.impl;

import com.telcel.usuarios.controlador.UsuariosControlador;
import com.telcel.usuarios.entidad.Propiedades;
import com.telcel.usuarios.entidad.Rol;
import com.telcel.usuarios.entidad.Usuario;
import com.telcel.usuarios.entidad.UsuarioDTO;
import com.telcel.usuarios.servicio.impl.UsuarioServicioImpl;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AsTecI Naucalpan
 */
@RestController
@RequestMapping("/api")
public class UsuariosControladorImpl implements UsuariosControlador{
    
    @Autowired
    private Propiedades propiedades;
    @Autowired
    private UsuarioServicioImpl usuariosServicio;

    @Override
    @GetMapping("/usuarios")
    public ResponseEntity<Collection<UsuarioDTO>> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuariosServicio.obtener_usuarios();
        ArrayList<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for(Usuario usuario : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setA_paterno(usuario.getA_paterno());
            usuarioDTO.setA_materno(usuario.getA_materno());
            Rol rol = usuariosServicio.leer_rol(usuario.getId_role());
            Rol rolUsuario = new Rol(rol.getId(),rol.getNombre());
            usuarioDTO.getRoles().add(rolUsuario);
            usuariosDTO.add(usuarioDTO);
        }
        if (usuarios != null && usuarios.size() > 0) {
            return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", "No hay usuarios");
            return new ResponseEntity<>(null, cabecera, HttpStatus.NO_CONTENT);
        }
    }

    @Override
    @PostMapping("/usuarios")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        String respuesta = usuariosServicio.crear_usuario(usuario);
        if (respuesta.contains(propiedades.getOk())) {
            return new ResponseEntity<>(propiedades.getOk(), HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErrcrearusuario());
            return new ResponseEntity<>(respuesta, cabecera, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> leerUsuario(@PathVariable int id) {
        Usuario usuario = usuariosServicio.leer_usuario(id);
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setA_paterno(usuario.getA_paterno());
            usuarioDTO.setA_materno(usuario.getA_materno());
            Rol rol = usuariosServicio.leer_rol(usuario.getId_role());
            Rol rolUsuario = new Rol(rol.getId(),rol.getNombre());
            usuarioDTO.getRoles().add(rolUsuario);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getNoexisteusuario());
            return new ResponseEntity<>(null, cabecera, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/usuarios")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
        String respuesta = usuariosServicio.actualizar_usuario(usuario);
        if (respuesta.contains(propiedades.getOk())) {
            return new ResponseEntity<>(propiedades.getOk(), HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErractusuario());
            return new ResponseEntity<>(respuesta, cabecera, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        String respuesta = usuariosServicio.eliminar_usuario(id);
        if (respuesta.contains(propiedades.getOk())) {
            return new ResponseEntity<>(propiedades.getOk(), HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErrelimusuario());
            return new ResponseEntity<>(respuesta, cabecera, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/roles")
    public ResponseEntity<Collection<Rol>> obtenerRoles() {
        ArrayList<Rol> roles = (ArrayList<Rol>) usuariosServicio.obtener_roles();
        if (roles != null && roles.size() > 0) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", "No hay roles");
            return new ResponseEntity<>(null, cabecera, HttpStatus.NO_CONTENT);
        }
    }

    @Override
    @PostMapping("/roles")
    public ResponseEntity<String> crearRol(@RequestBody Rol rol) {
        String respuesta = usuariosServicio.crear_rol(rol);
        if(respuesta.contains(propiedades.getOk())){
            return new ResponseEntity<>(propiedades.getOk(),HttpStatus.OK);
        }else{
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErrcrearrol());
            return new ResponseEntity<>(respuesta,cabecera,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> leerRol(@PathVariable int id) {
        Rol rol = usuariosServicio.leer_rol(id);
        if(rol!=null){
            return new ResponseEntity<>(rol,HttpStatus.OK);
        }else{
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getNoexisterol());
            return new ResponseEntity<>(null,cabecera,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/roles")
    public ResponseEntity<String> actualizarRol(@RequestBody Rol rol) {
        String respuesta = usuariosServicio.actualizar_rol(rol);
        if(respuesta.contains(propiedades.getOk())){
            return new ResponseEntity<>(propiedades.getOk(),HttpStatus.OK);
        }else{
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErractrol());
            return new ResponseEntity<>(respuesta,cabecera,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable int id) {
        String respuesta = usuariosServicio.eliminar_rol(id);
        if(respuesta.contains(propiedades.getOk())){
            return new ResponseEntity<>(propiedades.getOk(),HttpStatus.OK);
        }else{
            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add("Respuesta", propiedades.getErrelimrol());
            return new ResponseEntity<>(respuesta,cabecera,HttpStatus.BAD_REQUEST);
        }
    }
    
}
