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
public class Horario {

    private int idHorario;
    private String horaInicial;
    private String horaFinal;
    private char dia;
    private char idTipo;
    private int idNumero;

    public Horario() {
    }

    public Horario(int idHorario, String horaInicial, String horaFinal, char dia, char idTipo, int idNumero) {
        this.idHorario = idHorario;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.dia = dia;
        this.idTipo = idTipo;
        this.idNumero = idNumero;
    }

    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * @return the horaInicial
     */
    public String getHoraInicial() {
        return horaInicial;
    }

    /**
     * @param horaInicial the horaInicial to set
     */
    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * @return the horaFinal
     */
    public String getHoraFinal() {
        return horaFinal;
    }

    /**
     * @param horaFinal the horaFinal to set
     */
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @return the dia
     */
    public char getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(char dia) {
        this.dia = dia;
    }

    /**
     * @return the idTipo
     */
    public char getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(char idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the idNumero
     */
    public int getIdNumero() {
        return idNumero;
    }

    /**
     * @param idNumero the idNumero to set
     */
    public void setIdNumero(int idNumero) {
        this.idNumero = idNumero;
    }

}
