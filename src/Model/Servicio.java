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
public class Servicio {

    //las fechas son Strign debido a la conversion que se hace en el modelo de la fecha(controlador)
    private int idServicio;
    private String fechaInicial;
    private String fechaFinal;
    private char tipoDiligencia;
    private char tipoRecorrido;
    private char estado;
    private Double costo;
    private int codPostal;
    private char idTipoC;
    private char idTipoM;
    private int idNumeroC;
    private int idNumeroM;

    public Servicio() {

    }

    public Servicio(int idServicio, String fechaInicial, String fechaFinal, char tipoDiligencia, char tipoRecorrido, char estado, Double costo, int codPostal, char idTipoC, char idTipoM, int idNumeroC, int idNumeroM) {
        this.idServicio = idServicio;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipoDiligencia = tipoDiligencia;
        this.tipoRecorrido = tipoRecorrido;
        this.estado = estado;
        this.costo = costo;
        this.codPostal = codPostal;
        this.idTipoC = idTipoC;
        this.idTipoM = idTipoM;
        this.idNumeroC = idNumeroC;
        this.idNumeroM = idNumeroM;
    }

    /**
     * @return the idServicios
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

    /**
     * @return the fechaInicial
     */
    public String getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the tipoDiligencia
     */
    public char getTipoDiligencia() {
        return tipoDiligencia;
    }

    /**
     * @param tipoDiligencia the tipoDiligencia to set
     */
    public void setTipoDiligencia(char tipoDiligencia) {
        this.tipoDiligencia = tipoDiligencia;
    }

    /**
     * @return the tipoRecorrido
     */
    public char getTipoRecorrido() {
        return tipoRecorrido;
    }

    /**
     * @param tipoRecorrido the tipoRecorrido to set
     */
    public void setTipoRecorrido(char tipoRecorrido) {
        this.tipoRecorrido = tipoRecorrido;
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
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
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

    /**
     * @return the idTipoC
     */
    public char getIdTipoC() {
        return idTipoC;
    }

    /**
     * @param idTipoC the idTipoC to set
     */
    public void setIdTipoC(char idTipoC) {
        this.idTipoC = idTipoC;
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
     * @return the idNumeroC
     */
    public int getIdNumeroC() {
        return idNumeroC;
    }

    /**
     * @param idNumeroC the idNumeroC to set
     */
    public void setIdNumeroC(int idNumeroC) {
        this.idNumeroC = idNumeroC;
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
