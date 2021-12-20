/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sebas
 */
public class CambioEstado {
    private int k_id_cambioestado;
    private char i_estado;
    private String f_cambio;
    private String o_comentario;
    private int k_idServicio;

    public CambioEstado() {
    }
    
    
    public CambioEstado(int k_id_cambioestado, char i_estado, String f_cambio, String o_comentario, int k_idServicio) {
        this.k_id_cambioestado = k_id_cambioestado;
        this.i_estado = i_estado;
        this.f_cambio = f_cambio;
        this.o_comentario = o_comentario;
        this.k_idServicio = k_idServicio;
    }

    public int getK_id_cambioestado() {
        return k_id_cambioestado;
    }

    public void setK_id_cambioestado(int k_id_cambioestado) {
        this.k_id_cambioestado = k_id_cambioestado;
    }

    public char getI_estado() {
        return i_estado;
    }

    public void setI_estado(char i_estado) {
        this.i_estado = i_estado;
    }

    public String getF_cambio() {
        return f_cambio;
    }

    public void setF_cambio(String f_cambio) {
        this.f_cambio = f_cambio;
    }

    public String getO_comentario() {
        return o_comentario;
    }

    public void setO_comentario(String o_comentario) {
        this.o_comentario = o_comentario;
    }

    public int getK_idServicio() {
        return k_idServicio;
    }

    public void setK_idServicio(int k_idServicio) {
        this.k_idServicio = k_idServicio;
    }

    
}
