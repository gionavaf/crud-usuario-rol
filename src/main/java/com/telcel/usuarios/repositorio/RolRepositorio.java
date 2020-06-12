/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.repositorio;

import com.telcel.usuarios.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AsTecI Naucalpan
 */
@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer>{
    
}
