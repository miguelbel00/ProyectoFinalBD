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
public class CiudadTarifa {

    private int idTarifa;
    private int codPostal;

    public CiudadTarifa() {
    }

    public CiudadTarifa(int idTarifa, int codPostal) {
        this.idTarifa = idTarifa;
        this.codPostal = codPostal;
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
     * @return the codPostal
     */
    public int getCodPostal() {
        return codPostal;
    }

    /**
     * @param codPostal the codPostal to set
     */
    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }
 

}
