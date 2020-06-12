/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.usuarios.entidad;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author AsTecI Naucalpan
 */
@Data
@ConfigurationProperties(prefix = "crud")
public class Propiedades {

    private String ok;
    private String erractrol;
    private String noexisterol;
    private String errrolinvalida;
    private String noexisteusuario;
    private String errid;
    private String errelimrol;
    private String erractusuario;
    private String errpininvalido;
    private String errapellidomat;
    private String errapellidopat;
    private String errnombre;
    private String idexiste;
    private String errelimusuario;
    private String errpin;
    private String errcrearrol;
    private String errcrearusuario;
    private String rolexiste;
    private String mensaje;
    private String usuario;
    private String error;
    private String rol;
}
