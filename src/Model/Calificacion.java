/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author migue
 */
public class Calificacion {

    private int idCalificacion;
    private int valor;
    private int idServicio;

    public Calificacion(){
    }

    public Calificacion(int idCalificacion, int valor, int idServicio) {
        this.idCalificacion = idCalificacion;
        this.valor = valor;
        this.idServicio = idServicio;
    }

    /**
     * @return the idCalificacion
     */
    public int getIdCalificacion() {
        return idCalificacion;
    }

    /**
     * @param idCalificacion the idCalificacion to set
     */
    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the idServicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    


}
