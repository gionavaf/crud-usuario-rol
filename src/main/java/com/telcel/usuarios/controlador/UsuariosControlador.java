/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.controlador;

import com.telcel.usuarios.entidad.Rol;
import com.telcel.usuarios.entidad.Usuario;
import com.telcel.usuarios.entidad.UsuarioDTO;
import java.util.Collection;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author AsTecI Naucalpan
 */
public interface UsuariosControlador {
    public ResponseEntity<Collection<UsuarioDTO>> obtenerUsuarios();

    public ResponseEntity<String> crearUsuario(Usuario usuario);

    public ResponseEntity<UsuarioDTO> leerUsuario(int id);

    public ResponseEntity<String> actualizarUsuario(Usuario usuario);

    public ResponseEntity<String> eliminarUsuario(int id);
    
    public ResponseEntity<Collection<Rol>> obtenerRoles();

    public ResponseEntity<String> crearRol(Rol rol);

    public ResponseEntity<Rol> leerRol(int id);

    public ResponseEntity<String> actualizarRol(Rol rol);

    public ResponseEntity<String> eliminarRol(int id);
}
