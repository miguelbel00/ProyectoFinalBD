/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.*;
import Model.*;
import View.Cobros;
import View.GuiAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.Ventana;

/**
 *
 * @author migue
 */
public class ControladorAdmin implements ActionListener{
    Cliente cliente = new Cliente();
    ClienteDAO clientedao = new ClienteDAO();
    Mensajero mensajero = new Mensajero();
    MensajeroDAO mensajerodao = new MensajeroDAO();
    Servicio servicio=new Servicio();
    ServicioDAO serviciodao=new ServicioDAO();
    Pago pago= new Pago();
    PagoDAO pagodao= new PagoDAO();
    Horario horario= new Horario();
    HorarioDAO horariodao= new HorarioDAO();
    CambioEstado cambioestado= new CambioEstado();
    CambiEstadoDAO cambioestadodao= new CambiEstadoDAO();
    Calificacion calificacion= new Calificacion();
    CalificacionDAO calificaciondao= new CalificacionDAO();
    Ciudad ciudad=new Ciudad();
    CiudadDAO ciudaddao=new CiudadDAO();
    PagoMensajero pagomensajero=new PagoMensajero();
    PagoMenDao pagomendao=new PagoMenDao();
    Indicacion indicacion= new Indicacion();
    IndicacionDAO indicaciondao=new IndicacionDAO();
    Tarifa tarifa=new Tarifa();
    TarifaDAO tarifadao=new TarifaDAO();
    GuiAdmin guiAdmin=new GuiAdmin();
    Ventana ventana= new Ventana();
    DefaultTableModel tablaModel = new DefaultTableModel();
    
    //this.jTable1.setModel(modelo);
    public ControladorAdmin(GuiAdmin guiAdmin) {
        this.guiAdmin = guiAdmin;
        this.guiAdmin.btnMostrarClientes.addActionListener(this);
        this.guiAdmin.btnMostrarMensajeros.addActionListener(this);
        this.guiAdmin.btnMostrarServicios.addActionListener(this);
        this.guiAdmin.btnMostrarPagos.addActionListener(this);
        this.guiAdmin.btnHorarioMensajeros.addActionListener(this);
        this.guiAdmin.btncambioestado.addActionListener(this);
        this.guiAdmin.btnCalificacion.addActionListener(this);
        this.guiAdmin.btnCiudad.addActionListener(this);
        this.guiAdmin.btnPagosMensajero.addActionListener(this);
        this.guiAdmin.btnIndicaciones.addActionListener(this);
        this.guiAdmin.btnTarifa.addActionListener(this);
        this.guiAdmin.btnGenerarPago.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.guiAdmin.btnMostrarClientes)){
            
            try{
                DibujarClientes(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }else if(ae.getSource().equals(this.guiAdmin.btnMostrarMensajeros)){
            
            try{
                DibujarMensajeros(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnMostrarServicios)){
            try{
                DibujarServicios(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnMostrarPagos)){
            try{
                DibujarPagos(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnHorarioMensajeros)){
            try{
                
                DibujarHorario(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btncambioestado)){
            try{
                DibujarCambioEstado(this.ventana.jTable1);
               
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnCalificacion)){
            try{
                DibujarCalificacion(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnCiudad)){
            try{
                DibujarCiudad(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnPagosMensajero)){
            try{
                DibujarPagoMensajero(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnIndicaciones)){
            try{
                DibujarIndicacion(this.ventana.jTable1);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnTarifa)){
            try{
                DibujarTarifa(this.ventana.jTable1);
                ;
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
        else if(ae.getSource().equals(this.guiAdmin.btnGenerarPago)){
            
            try{
                Cobros co= new Cobros();
                ControladorCobros controlco=new ControladorCobros(co);
                co.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
    }
    public void DibujarClientes(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.clientedao.TablaCliente();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarMensajeros(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.mensajerodao.TablaMensajero();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarServicios(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.serviciodao.TablaServicio();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarPagos(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.pagodao.TablaPago();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarHorario(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.horariodao.TablaHorario();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarCambioEstado(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.cambioestadodao.TablaCambioEstado();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarCalificacion(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.calificaciondao.TablaCalificacion();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarCiudad(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.ciudaddao.TablaCiudad();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarPagoMensajero(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.pagomendao.TablaPagoMensajero();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarIndicacion(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.indicaciondao.TablaIndicacion();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
    public void DibujarTarifa(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.tarifadao.TablaTarifa();
        ventana.jTable1.setModel(tablaModel);
        ventana.setVisible(true);
    }
}
