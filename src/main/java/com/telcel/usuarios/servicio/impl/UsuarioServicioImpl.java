/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.servicio.impl;

import com.telcel.usuarios.entidad.Propiedades;
import com.telcel.usuarios.entidad.Rol;
import com.telcel.usuarios.entidad.Usuario;
import com.telcel.usuarios.entidad.UsuarioDTO;
import com.telcel.usuarios.repositorio.RolRepositorio;
import com.telcel.usuarios.repositorio.UsuarioRepositorio;
import com.telcel.usuarios.servicio.UsuariosServicio;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AsTecI Naucalpan
 */
@Service
public class UsuarioServicioImpl implements UsuariosServicio {

    @Autowired
    Propiedades propiedades;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    RolRepositorio rolRepositorio;

    @Override
    public Collection<Usuario> obtener_usuarios() {
        return (Collection<Usuario>) usuarioRepositorio.findAll();
    }

    @Override
    public String crear_usuario(Usuario usuario) {
        String msg = "";
        if (usuario.getId() > 0 && usuario.getId() < Integer.MAX_VALUE) {
            if (!usuarioRepositorio.existsById(usuario.getId())) {
                if (usuario.getNombre().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                    if (usuario.getA_paterno().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                        if (usuario.getA_materno().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                            if (Integer.toString(usuario.getId_role()).matches("[0-9]")) {
                                Usuario creado = usuarioRepositorio.save(usuario);
                                if (creado != null) {
                                    msg = propiedades.getOk();
                                } else {
                                    msg = propiedades.getErrcrearusuario();
                                }
                            } else {
                                msg = propiedades.getErrpininvalido();
                            }
                        } else {
                            msg = propiedades.getErrapellidomat();
                        }
                    } else {
                        msg = propiedades.getErrapellidopat();
                    }
                } else {
                    msg = propiedades.getErrnombre();
                }
            } else {
                msg = propiedades.getIdexiste();
            }
        } else {
            msg = propiedades.getErrid();
        }
        return msg;
    }

    @Override
    public Usuario leer_usuario(int id) {
        Usuario buscado = null;
        if (id < Integer.MAX_VALUE) {
            buscado = usuarioRepositorio.getOne(id);
        }
        return buscado;
    }

    @Override
    public String actualizar_usuario(Usuario usuario) {
        String msg = "";
        if (usuario.getId() > 0 && usuario.getId() < Integer.MAX_VALUE) {
            if (usuarioRepositorio.existsById(usuario.getId())) {
                if (usuario.getNombre().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                    if (usuario.getA_paterno().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                        if (usuario.getA_materno().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                            if (Integer.toString(usuario.getId_role()).matches("[0-9]{2}")) {
                                Usuario actualizado = usuarioRepositorio.save(usuario);
                                if (actualizado != null) {
                                    msg = propiedades.getOk();
                                } else {
                                    msg = propiedades.getErractusuario();
                                }
                            } else {
                                msg = propiedades.getErrpininvalido();
                            }
                        } else {
                            msg = propiedades.getErrapellidomat();
                        }
                    } else {
                        msg = propiedades.getErrapellidopat();
                    }
                } else {
                    msg = propiedades.getErrnombre();
                }
            } else {
                msg = propiedades.getNoexisteusuario();
            }
        } else {
            msg = propiedades.getErrid();
        }
        return msg;
    }

    @Override
    public String eliminar_usuario(int id) {
        String msg = "";
        if (usuarioRepositorio.existsById(id)) {
            int respuesta = 1;
            usuarioRepositorio.deleteById(id);
            if (respuesta > 0) {
                msg = propiedades.getOk();
            } else {
                msg = propiedades.getErrelimusuario();
            }
        } else {
            msg = propiedades.getNoexisteusuario();
        }
        return msg;
    }

    @Override
    public Collection<Rol> obtener_roles() {
        return rolRepositorio.findAll();
    }

    @Override
    public String crear_rol(Rol rol) {
        String msg = "";
        if (rol.getId() > 0 && rol.getId() < Integer.MAX_VALUE) {
            if (!rolRepositorio.existsById(rol.getId())) {
                if (rol.getNombre().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                    Rol creado = rolRepositorio.save(rol);
                    if (creado != null) {
                        msg = propiedades.getOk();
                    } else {
                        msg = propiedades.getErrcrearrol();
                    }

                } else {
                    msg = propiedades.getErrnombre();
                }
            } else {
                msg = propiedades.getIdexiste();
            }
        } else {
            msg = propiedades.getErrid();
        }
        return msg;
    }

    @Override
    public Rol leer_rol(int id) {
        Rol buscado = null;
        if (id < 2147483647) {
            buscado = rolRepositorio.getOne(id);
        }
        return buscado;
    }

    @Override
    public String actualizar_rol(Rol rol) {
        String msg = "";
        if (rol.getId() > 0 && rol.getId() < Integer.MAX_VALUE) {
            if (rolRepositorio.existsById(rol.getId())) {
                if (rol.getNombre().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
                    Rol actualizado = rolRepositorio.save(rol);
                    if (actualizado != null) {
                        msg = propiedades.getOk();
                    } else {
                        msg = propiedades.getErractrol();
                    }

                } else {
                    msg = propiedades.getErrnombre();
                }
            } else {
                msg = propiedades.getNoexisterol();
            }
        } else {
            msg = propiedades.getErrid();
        }
        return msg;
    }

    @Override
    public String eliminar_rol(int id) {
        String msg = "";
        if (rolRepositorio.existsById(id)) {
            int respuesta = 1;
            rolRepositorio.deleteById(id);
            if (respuesta > 0) {
                msg = propiedades.getOk();
            } else {
                msg = propiedades.getErrelimrol();
            }
        } else {
            msg = propiedades.getNoexisterol();
        }
        return msg;
    }

}
