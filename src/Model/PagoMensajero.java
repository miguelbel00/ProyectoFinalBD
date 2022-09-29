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
public class PagoMensajero {

    private int idCobro;
    private String fechaCobro;
    private char estado;
    private Double monto;
    private String fechaCobroGenerado;
    private char idTipoM;
    private int idNumeroM;

    public PagoMensajero() {
    }

    public PagoMensajero(int idCobro, String fechaCobro, char estado, Double monto, String fechaCobroGenerado, char idTipoM, int idNumeroM) {
        this.idCobro = idCobro;
        this.fechaCobro = fechaCobro;
        this.estado = estado;
        this.monto = monto;
        this.fechaCobroGenerado = fechaCobroGenerado;
        this.idTipoM = idTipoM;
        this.idNumeroM = idNumeroM;
    }

    /**
     * @return the idCobro
     */
    public int getIdCobro() {
        return idCobro;
    }

    /**
     * @param idCobro the idCobro to set
     */
    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }

    /**
     * @return the fechaCobro
     */
    public String getFechaCobro() {
        return fechaCobro;
    }

    /**
     * @param fechaCobro the fechaCobro to set
     */
    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    /**
     * @return the estado
     */
    public char getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado) {
        this.estado = estado;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the fechaCobroGenerado
     */
    public String getFechaCobroGenerado() {
        return fechaCobroGenerado;
    }

    /**
     * @param fechaCobroGenerado the fechaCobroGenerado to set
     */
    public void setFechaCobroGenerado(String fechaCobroGenerado) {
        this.fechaCobroGenerado = fechaCobroGenerado;
    }

    /**
     * @return the idTipoM
     */
    public char getIdTipoM() {
        return idTipoM;
    }

    /**
     * @param idTipoM the idTipoM to set
     */
    public void setIdTipoM(char idTipoM) {
        this.idTipoM = idTipoM;
    }

    /**
     * @return the idNumeroM
     */
    public int getIdNumeroM() {
        return idNumeroM;
    }

    /**
     * @param idNumeroM the idNumeroM to set
     */
    public void setIdNumeroM(int idNumeroM) {
        this.idNumeroM = idNumeroM;
    }

}
