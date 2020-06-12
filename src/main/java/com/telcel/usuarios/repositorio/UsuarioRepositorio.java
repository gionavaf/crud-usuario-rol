/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.repositorio;

import com.telcel.usuarios.entidad.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AsTecI Naucalpan
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    @Query("select u from Usuario u where u.nombre = :nombre")
    List<Usuario> findByNumCuenta(@Param("nombre") String nombre);
}
