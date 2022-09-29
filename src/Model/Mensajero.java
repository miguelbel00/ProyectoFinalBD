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
public class Mensajero {

    private char idTipoM;
    private int idNumeroM;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private char sexo;
    private String celular;
    private String telefono;
    private String correo;
    private String nacionalidad;
    private char seguridadSocial;
    private char medioTransporte;
    private char tipoTransporte;
    private char tipoCiudad;

    public Mensajero() {
    }

    public Mensajero(char idTipoM, int idNumeroM, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String fechaNacimiento, char sexo, String celular, String telefono, String correo, String nacionalidad, char seguridadSocial, char medioTransporte, char tipoTransporte, char tipoCiudad) {
        this.idTipoM = idTipoM;
        this.idNumeroM = idNumeroM;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.celular = celular;
        this.telefono = telefono;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.seguridadSocial = seguridadSocial;
        this.medioTransporte = medioTransporte;
        this.tipoTransporte = tipoTransporte;
        this.tipoCiudad = tipoCiudad;
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

    /**
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the seguridadSocial
     */
    public char getSeguridadSocial() {
        return seguridadSocial;
    }

    /**
     * @param seguridadSocial the seguridadSocial to set
     */
    public void setSeguridadSocial(char seguridadSocial) {
        this.seguridadSocial = seguridadSocial;
    }

    /**
     * @return the medioTransporte
     */
    public char getMedioTransporte() {
        return medioTransporte;
    }

    /**
     * @param medioTransporte the medioTransporte to set
     */
    public void setMedioTransporte(char medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    /**
     * @return the tipoTransporte
     */
    public char getTipoTransporte() {
        return tipoTransporte;
    }

    /**
     * @param tipoTransporte the tipoTransporte to set
     */
    public void setTipoTransporte(char tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    /**
     * @return the tipoCiudad
     */
    public char getTipoCiudad() {
        return tipoCiudad;
    }

    /**
     * @param tipoCiudad the tipoCiudad to set
     */
    public void setTipoCiudad(char tipoCiudad) {
        this.tipoCiudad = tipoCiudad;
    }

   
    
    

}
