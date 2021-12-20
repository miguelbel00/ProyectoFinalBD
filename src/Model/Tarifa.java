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
public class Tarifa {

    private int idTarifa;
    private Double tarifaPaquete;
    private char tipoPaquete;

    public Tarifa() {
    }

    public Tarifa(int idTarifa, double tarifaPaquete, char tipoPaquete) {
        this.idTarifa = idTarifa;
        this.tarifaPaquete = tarifaPaquete;
        this.tipoPaquete = tipoPaquete;
    }


    /**
     * @return the idTarifa
     */
    public int getIdTarifa() {
        return idTarifa;
    }

    /**
     * @param idTarifa the idTarifa to set
     */
    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    /**
     * @return the tarifaPaquete
     */
    public Double getTarifaPaquete() {
        return tarifaPaquete;
    }

    /**
     * @param tarifaPaquete the tarifaPaquete to set
     */
    public void setTarifaPaquete(Double tarifaPaquete) {
        this.tarifaPaquete = tarifaPaquete;
    }

    /**
     * @return the tipoPaquete
     */
    public char getTipoPaquete() {
        return tipoPaquete;
    }

    /**
     * @param tipoPaquete the tipoPaquete to set
     */
    public void setTipoPaquete(char tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }


}