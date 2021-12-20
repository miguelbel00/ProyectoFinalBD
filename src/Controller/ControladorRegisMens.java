/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.*;
import Model.*;
import View.GuiMain;
import View.GuiRegisMens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class ControladorRegisMens implements ActionListener {

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    Mensajero mensajero = new Mensajero();
    Horario horario = new Horario();
    MensajeroDAO mensajerodao = new MensajeroDAO();
    HorarioDAO horariodao = new HorarioDAO();
    GuiRegisMens guiRegisMens = new GuiRegisMens();
    Ciudad ciudad = new Ciudad();
    CiudadDAO ciudadDAO = new CiudadDAO();

    public ControladorRegisMens(GuiRegisMens guiRegisMens) {
        this.guiRegisMens = guiRegisMens;
        this.guiRegisMens.btnAgregarHorario.addActionListener(this);
        this.guiRegisMens.btnRegistrarse.addActionListener(this);
        this.guiRegisMens.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(guiRegisMens.btnRegistrarse)) {
            try {
                Adicionar();
                mensajerodao.AdicionarMensajero(mensajero);
                JOptionPane.showMessageDialog(null, "Mensajero Registrado");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Parametros incorrectos, Reviselos\nError: " + ex);
            }
        } else if (e.getSource().equals(guiRegisMens.btnAgregarHorario)) {
            try {
                AdicionarHorario();
                horariodao.AdicionarHorario(horario);
                JOptionPane.showMessageDialog(null, "Horario agregado");
                this.guiRegisMens.cbxHFinal.setSelectedIndex(0);
                this.guiRegisMens.cbxHInicial.setSelectedIndex(0);
                this.guiRegisMens.cbxDIA.setSelectedIndex(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: )" + ex.getMessage());
            }
        } else if (e.getSource().equals(guiRegisMens.btnVolver)) {
            GuiMain guiMain = new GuiMain();
            ControladorMain controlMain = new ControladorMain(guiMain);
            guiMain.setVisible(true);
            guiRegisMens.dispose();
        }
    }

    public void Adicionar() {

        mensajero.setIdTipoM(this.guiRegisMens.cbxTipoIdenti.getSelectedItem().toString().charAt(2));
        mensajero.setIdNumeroM(Integer.parseInt(this.guiRegisMens.txtDocumento.getText()));
        mensajero.setPrimerNombre(this.guiRegisMens.txtPrimerNombre.getText());
        mensajero.setSegundoNombre(this.guiRegisMens.txtSegundoNombre.getText());
        mensajero.setPrimerApellido(this.guiRegisMens.txtPrimerApellido.getText());
        mensajero.setSegundoApellido(this.guiRegisMens.txtSegundoApellido.getText());
        mensajero.setFechaNacimiento(formato.format(this.guiRegisMens.dFechaNacimiento.getDate()));
        mensajero.setSexo(this.guiRegisMens.cbxSexo.getSelectedItem().toString().charAt(0));
        mensajero.setCelular(this.guiRegisMens.txtCelular.getText());
        mensajero.setTelefono(this.guiRegisMens.txtTelefono.getText());
        mensajero.setCorreo(this.guiRegisMens.txtCorreo.getText());
        mensajero.setNacionalidad(this.guiRegisMens.txtNacionalidad.getText());
        mensajero.setSeguridadSocial(this.guiRegisMens.cbxSeguSocial.getSelectedItem().toString().charAt(0));
        mensajero.setMedioTransporte(this.guiRegisMens.cbxVehiculo.getSelectedItem().toString().charAt(0));
        mensajero.setTipoTransporte(this.guiRegisMens.cbxTipoTransporte.getSelectedItem().toString().charAt(0));
        mensajero.setTipoCiudad(this.guiRegisMens.cbxCiudad.getSelectedItem().toString().charAt(0));

    }

    public void AdicionarHorario() {
        horario.setHoraInicial(this.guiRegisMens.cbxHInicial.getSelectedItem().toString());
        horario.setHoraFinal(this.guiRegisMens.cbxHFinal.getSelectedItem().toString());
        horario.setDia(this.guiRegisMens.cbxDIA.getSelectedItem().toString().charAt(0));
        horario.setIdTipo(this.guiRegisMens.cbxTipoIdenti.getSelectedItem().toString().charAt(0));
        horario.setIdNumero(Integer.parseInt(this.guiRegisMens.txtDocumento.getText()));
    }

}
