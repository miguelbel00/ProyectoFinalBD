/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.HorarioDAO;
import Access.MensajeroDAO;
import Model.Horario;
import Model.Mensajero;
import View.GuiEditarMensajero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hanna Valentina
 */
public class ControladorEdiMens implements ActionListener{
    
    GuiEditarMensajero guiEditarMensajero = new GuiEditarMensajero();
    Horario horario = new Horario ();
    Mensajero mensajero = new Mensajero();
    MensajeroDAO consulta = new MensajeroDAO();
    HorarioDAO consultaH = new HorarioDAO();
    DefaultTableModel tablaModel = new DefaultTableModel(); 
        
    public ControladorEdiMens (GuiEditarMensajero guiEditarMensajero, Mensajero mensajero){
        this.guiEditarMensajero = guiEditarMensajero;
        this.mensajero=mensajero;
        this.guiEditarMensajero.btnActualizar.addActionListener(this);
        this.guiEditarMensajero.btnAgregarHorario.addActionListener(this);
        this.guiEditarMensajero.btnEliminarHorario.addActionListener(this);
        MostrarDatos();
        Historico(guiEditarMensajero.tbHorario);
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == guiEditarMensajero.btnActualizar){
            if(guiEditarMensajero.txtPrimerNombre.getText().equals("") || guiEditarMensajero.txtSegundoNombre.getText().equals("")
                    || guiEditarMensajero.lblNacio.getText().equals("")|| guiEditarMensajero.txtNumCel.getText().equals("")
                    || guiEditarMensajero.txtTelefono.getText().equals("")|| guiEditarMensajero.txtCorreo.getText().equals("")
                    || String.valueOf(guiEditarMensajero.cbxGenero.getSelectedItem()).equals("Género") 
                    || String.valueOf(guiEditarMensajero.cbxTipoDili.getSelectedItem()).equals("Tipo de diligencia")
                    || String.valueOf(guiEditarMensajero.cbxSeguSocial.getSelectedItem()).equals("Seguridad social")
                    || String.valueOf(guiEditarMensajero.cbxVehiculo.getSelectedItem()).equals("Tipo de vehiculo")
                    || String.valueOf(guiEditarMensajero.cbxCiudad.getSelectedItem()).equals("Ciudad"))
                    {
                JOptionPane.showMessageDialog(null, "Faltan campos por diligenciar");
            
            }else if (!guiEditarMensajero.txtCorreo.getText().equals(guiEditarMensajero.txtConfCorreo.getText())){
                JOptionPane.showMessageDialog(null, "No coinciden los correos");
            }else{
                Adicionar();
                consulta.ModificarMensajero(mensajero);
                JOptionPane.showMessageDialog(null,"Cliente Actualizado");
            }   
        }else if(e.getSource() == guiEditarMensajero.btnAgregarHorario){
            try{
            AdicionarH();
            JOptionPane.showMessageDialog(null,"Horario Agregado");
            Historico(guiEditarMensajero.tbHorario);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }

        }else if(e.getSource() == guiEditarMensajero.btnEliminarHorario){
            try{
            EliminarHorario();
            JOptionPane.showMessageDialog(null,"Horario Eliminado");
            Historico(guiEditarMensajero.tbHorario);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
    }
    }
    public void Historico(JTable tabla) {
     
        this.tablaModel = (DefaultTableModel) tabla.getModel();

        this.tablaModel = this.consultaH.HistoricoHorarioM(mensajero.getIdNumeroM(),mensajero.getIdTipoM());
        //this.tablaModel = this.servicioDAO.HistoricoServicioM();
        guiEditarMensajero.tbHorario.setModel(tablaModel);
    }
    public void Adicionar() {

        mensajero.setIdTipoM(this.guiEditarMensajero.txtTipoDoc.toString().charAt(0));
        mensajero.setIdNumeroM(Integer.parseInt(this.guiEditarMensajero.txtNumDoc.getText()));
        mensajero.setPrimerNombre(this.guiEditarMensajero.txtPrimerNombre.getText());
        mensajero.setSegundoNombre(this.guiEditarMensajero.txtSegundoNombre.getText());
        mensajero.setPrimerApellido(this.guiEditarMensajero.txtPrimerApellido.getText());
        mensajero.setSegundoApellido(this.guiEditarMensajero.txtSegundoApellido.getText());
        mensajero.setFechaNacimiento(this.guiEditarMensajero.lblFecha.getText());
        mensajero.setSexo(this.guiEditarMensajero.cbxGenero.getSelectedItem().toString().charAt(0));
        mensajero.setCelular(this.guiEditarMensajero.txtNumCel.getText());
        mensajero.setTelefono(this.guiEditarMensajero.txtTelefono.getText());
        mensajero.setCorreo(this.guiEditarMensajero.txtCorreo.getText());
        mensajero.setNacionalidad(this.guiEditarMensajero.lblNacio.getText());
        mensajero.setSeguridadSocial(this.guiEditarMensajero.cbxSeguSocial.getSelectedItem().toString().charAt(0));
        mensajero.setMedioTransporte(this.guiEditarMensajero.cbxVehiculo.getSelectedItem().toString().charAt(0));
        mensajero.setTipoTransporte(this.guiEditarMensajero.cbxTipoDili.getSelectedItem().toString().charAt(0));
        consulta.ModificarMensajero(mensajero);
    }
    public void MostrarDatos(){
            this.mensajero = consulta.BuscarMensajero(this.mensajero.getIdNumeroM(), this.mensajero.getIdTipoM());
            this.guiEditarMensajero.txtNumDoc.setText(String.valueOf(this.mensajero.getIdNumeroM()));
            this.guiEditarMensajero.txtTipoDoc.setText(String.valueOf(this.mensajero.getIdTipoM()));
            this.guiEditarMensajero.txtPrimerNombre.setText(this.mensajero.getPrimerNombre());
            this.guiEditarMensajero.txtSegundoNombre.setText(this.mensajero.getSegundoNombre());
            this.guiEditarMensajero.txtPrimerApellido.setText(this.mensajero.getPrimerApellido());
            this.guiEditarMensajero.txtSegundoApellido.setText(this.mensajero.getSegundoApellido());
            this.guiEditarMensajero.lblFecha.setText(this.mensajero.getFechaNacimiento());
            this.guiEditarMensajero.txtCorreo.setText(this.mensajero.getCorreo());
            this.guiEditarMensajero.txtConfCorreo.setText(this.mensajero.getCorreo());
            this.guiEditarMensajero.txtTelefono.setText(this.mensajero.getTelefono());
            this.guiEditarMensajero.txtNumCel.setText(this.mensajero.getCelular());
            this.guiEditarMensajero.lblNacio.setText(this.mensajero.getNacionalidad());
            if (this.mensajero.getSexo()=='M'){
                this.guiEditarMensajero.cbxGenero.setSelectedItem("Masculino");
            }
            else if (this.mensajero.getSexo()=='F'){
                this.guiEditarMensajero.cbxGenero.setSelectedItem("Femenino");
            }
            if (this.mensajero.getMedioTransporte()=='B'){
                this.guiEditarMensajero.cbxVehiculo.setSelectedItem("Bicicleta");
            }
            else if (this.mensajero.getMedioTransporte()=='M'){
                this.guiEditarMensajero.cbxVehiculo.setSelectedItem("Moto");
            }
            if (this.mensajero.getSeguridadSocial()=='S'){
                this.guiEditarMensajero.cbxSeguSocial.setSelectedItem("Si");
            }
            else if (this.mensajero.getSeguridadSocial()=='N'){
                this.guiEditarMensajero.cbxSeguSocial.setSelectedItem("No");
            }
            if (this.mensajero.getTipoCiudad()=='B'){
                this.guiEditarMensajero.cbxCiudad.setSelectedItem("Bogota DC");
            }
            else if (this.mensajero.getTipoCiudad()=='T'){
                this.guiEditarMensajero.cbxCiudad.setSelectedItem("Tunja");
            }
            if (this.mensajero.getTipoTransporte()=='D'){
                this.guiEditarMensajero.cbxTipoDili.setSelectedItem("Documento");
            }
            else if (this.mensajero.getTipoTransporte()=='P'){
                this.guiEditarMensajero.cbxTipoDili.setSelectedItem("Pequeño");
            }else if (this.mensajero.getTipoTransporte()=='M'){
                this.guiEditarMensajero.cbxTipoDili.setSelectedItem("Mediano");
            }else if (this.mensajero.getTipoTransporte()=='G'){
                this.guiEditarMensajero.cbxTipoDili.setSelectedItem("Grande");
            }
    }
    public void AdicionarH(){    
            horario.setDia(this.guiEditarMensajero.cbxDIA.getSelectedItem().toString().charAt(0));
            horario.setHoraInicial(String.valueOf(guiEditarMensajero.cbxHInicial.getSelectedItem()));
            horario.setHoraFinal(String.valueOf(guiEditarMensajero.cbxHFinal.getSelectedItem()));
            horario.setIdNumero(Integer.parseInt(guiEditarMensajero.txtNumDoc.getText()));
            horario.setIdTipo(mensajero.getIdTipoM());
            consultaH.AdicionarHorario(horario);
        }
    public void ModificarH(){
            horario.setDia(this.guiEditarMensajero.cbxDIA.getSelectedItem().toString().charAt(0));
            horario.setHoraInicial(String.valueOf(guiEditarMensajero.cbxHInicial.getSelectedItem()));
            horario.setHoraFinal(String.valueOf(guiEditarMensajero.cbxHFinal.getSelectedItem()));
            horario.setIdNumero(Integer.parseInt(guiEditarMensajero.txtNumDoc.getText()));
            horario.setIdTipo(guiEditarMensajero.txtTipoDoc.toString().charAt(0));
            consultaH.ModificarHorario(horario);
        }
    public void EliminarHorario(){
            consultaH.EliminarHorario((int)this.guiEditarMensajero.tbHorario.getValueAt(this.guiEditarMensajero.tbHorario.getSelectedRow(), 0));
            
    }
    
    }

    
    
