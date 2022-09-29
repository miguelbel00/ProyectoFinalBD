/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.CalificacionDAO;
import Access.CambiEstadoDAO;
import Access.IndicacionDAO;
import Access.PagoDAO;
import Access.ServicioDAO;
import Model.Calificacion;
import Model.CambioEstado;
import Model.Pago;
import Model.Servicio;
import View.GuiMain;
import View.GuiServicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Crist
 */
public final class ControladorServicio implements ActionListener {

    int idServicio;
    GuiServicio guiServicio = new GuiServicio();
    ServicioDAO serviciodao = new ServicioDAO();
    Servicio servicio = new Servicio();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calificacion calificacion = new Calificacion();
    Calificacion calificacion2 = new Calificacion();
    CalificacionDAO calificaciondao = new CalificacionDAO();
    IndicacionDAO indicaciondao = new IndicacionDAO();
    DefaultTableModel tablaModelservi = new DefaultTableModel();
    CambiEstadoDAO cambioestadodao = new CambiEstadoDAO();
    CambioEstado cambioestado = new CambioEstado();
    Pago pago=new Pago();
    Pago pago1=new Pago();
    PagoDAO pagodao= new PagoDAO();

    public ControladorServicio(GuiServicio guiServicio, int idServicio) {
        this.guiServicio = guiServicio;
        this.idServicio = idServicio;
        this.guiServicio.btnCalificacion.addActionListener(this);
        this.guiServicio.btnCamEstServ.addActionListener(this);
        this.guiServicio.btnPagar.addActionListener(this);
        this.guiServicio.btncambioestado.addActionListener(this);
        this.guiServicio.btnVolver.addActionListener(this);
        this.guiServicio.btnCalificarAceptar.addActionListener(this);
        this.guiServicio.btnPagarAceptar.addActionListener(this);
        mostrar();
        TablaIndicaciones(this.guiServicio.tbDetalles);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals((this.guiServicio.btnCamEstServ))) {
            if (guiServicio.txtEstadoServicio.getText().equals("S")) {
                guiServicio.btncambioestado.setVisible(true);
                guiServicio.cbxCambioestado.setVisible(true);
                guiServicio.txtcomentario.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "No puede cambiar el estado");
            }

        } else if (ae.getSource().equals(this.guiServicio.btnVolver)) {
            GuiMain volver = new GuiMain();
            ControladorMain controlmain = new ControladorMain(volver);
            volver.setVisible(true);
            guiServicio.dispose();
        } else if (ae.getSource().equals(this.guiServicio.btncambioestado)) {
            cambioEstado();
        } else if (ae.getSource().equals(this.guiServicio.btnCalificacion)) {
            
            if(guiServicio.txtCalificacion.getText().equals("")){
                guiServicio.txtponerCalificacion.setVisible(true);
                guiServicio.btnCalificarAceptar.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Ya esta calificado");
            }
        } else if(ae.getSource().equals(this.guiServicio.btnPagar)){
            System.out.println(guiServicio.txtMetPago.getText());
            pago=pagodao.BuscarPagos(idServicio);
            String p=""+pago.getMetodoPago();
            if(p.equals("E") || p.equals("P")){
                JOptionPane.showMessageDialog(null, "Ya esta pago");
            }else{
                guiServicio.cbxPagar.setVisible(true);
                guiServicio.btnPagarAceptar.setVisible(true);
            }
        } else if(ae.getSource().equals(this.guiServicio.btnCalificarAceptar)){
            ponercalificacionServicio();
        } else if(ae.getSource().equals(this.guiServicio.btnPagarAceptar)){
            ponerpago();
        }
            
    }
    public void ponerpago(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String Fecha = formato.format(timestamp);
        char metodo = guiServicio.cbxPagar.getSelectedItem().toString().charAt(0);
        agregarpago(Fecha, metodo);
        pagodao.AdicionarPago(pago);
        guiServicio.txtMetPago.setText(""+metodo);
        guiServicio.cbxPagar.setVisible(false);
        guiServicio.btnPagarAceptar.setVisible(false);
    }
    public void agregarpago(String Fecha, char metodo){
        pago.setFechaPago(Fecha);
        pago.setMetodoPago(metodo);
        pago.setIdServicio(idServicio);
    }
    public void ponercalificacionServicio(){
        int valor=Integer.parseInt(guiServicio.txtponerCalificacion.getText());
        agregarcalificacion(valor);
        calificaciondao.AdicionarCalificacion(calificacion2);
        guiServicio.txtCalificacion.setText(""+valor);
        guiServicio.txtponerCalificacion.setVisible(false);
        guiServicio.btnCalificarAceptar.setVisible(false);
    }
    public void agregarcalificacion(int valor){
        calificacion2.setValor(valor);
        calificacion2.setIdServicio(servicio.getIdServicio());
    }
    public void TablaIndicaciones(JTable tabla) {
        this.tablaModelservi = (DefaultTableModel) tabla.getModel();
        this.tablaModelservi = this.indicaciondao.IndicacionServicio(servicio.getIdServicio());
        guiServicio.tbDetalles.setModel(tablaModelservi);
    }

    public void cambioEstado() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String Fecha = formato.format(timestamp);
        char estado = guiServicio.cbxCambioestado.getSelectedItem().toString().charAt(0);
        String comentario = guiServicio.txtcomentario.getText();
        agregarcambio(Fecha, estado, comentario);
        serviciodao.CambiarEstadoServicio(idServicio, Fecha, estado);
        cambioestadodao.AdicionarCambioEstado(cambioestado);
        guiServicio.txtEstadoServicio.setText("" + estado);
        guiServicio.txtFFinal.setText(Fecha);
        guiServicio.btncambioestado.setVisible(false);
        guiServicio.cbxCambioestado.setVisible(false);
        guiServicio.txtcomentario.setText("");
        guiServicio.txtcomentario.setVisible(false);
    }

    public void agregarcambio(String Fecha, char estado, String comentario) {
        cambioestado.setI_estado(estado);
        cambioestado.setF_cambio(Fecha);
        cambioestado.setO_comentario(comentario);
        cambioestado.setK_idServicio(idServicio);
    }

    public void mostrar() {

        servicio = serviciodao.BuscarServicio(idServicio);
        calificacion = calificaciondao.BuscarCalificacion(idServicio);
        pago1=pagodao.BuscarPagos(idServicio);
        this.guiServicio.txtIdServicio.setText("" + idServicio);
        this.guiServicio.txtIdCliente.setText("C" + servicio.getIdTipoC() + ".-" + servicio.getIdNumeroC());
        this.guiServicio.txtIdMensajero.setText("C" + servicio.getIdTipoM() + ".-" + servicio.getIdNumeroM());
        this.guiServicio.txtCiudad.setText("" + servicio.getCodPostal());
        if (calificacion != null) {
            this.guiServicio.txtCalificacion.setText("" + calificacion.getValor());
        } else {
            this.guiServicio.txtCalificacion.setText("");
        }
        this.guiServicio.txtValorServicio.setText("" + servicio.getCosto());
        if(pago1 != null){
            this.guiServicio.txtMetPago.setText(""+pago1.getMetodoPago());
        }else{
            this.guiServicio.txtMetPago.setText("");
        }
        
        this.guiServicio.txtFInicio.setText("" + servicio.getFechaInicial());
        this.guiServicio.txtFFinal.setText("" + servicio.getFechaFinal());
        this.guiServicio.txtEstadoServicio.setText("" + servicio.getEstado());
        this.guiServicio.txtTDiligencia.setText("" + servicio.getTipoDiligencia());
        this.guiServicio.txtTRecorrido.setText("" + servicio.getTipoRecorrido());
        
    }

}
