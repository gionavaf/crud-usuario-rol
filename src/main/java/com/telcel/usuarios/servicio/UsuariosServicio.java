/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.servicio;

import com.telcel.usuarios.entidad.Rol;
import com.telcel.usuarios.entidad.Usuario;
import com.telcel.usuarios.entidad.UsuarioDTO;
import java.util.Collection;

/**
 *
 * @author AsTecI Naucalpan
 */
public interface UsuariosServicio {

    public Collection<Usuario> obtener_usuarios();

    public String crear_usuario(Usuario usuario);

    public Usuario leer_usuario(int id);

    public String actualizar_usuario(Usuario usuario);

    public String eliminar_usuario(int id);

    public Collection<Rol> obtener_roles();

    public String crear_rol(Rol rol);

    public Rol leer_rol(int id);

    public String actualizar_rol(Rol rol);

    public String eliminar_rol(int id);
}
