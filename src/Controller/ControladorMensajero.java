/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.PagoMenDao;
import Access.ServicioDAO;
import Model.Mensajero;
import Model.PagoMensajero;
import View.GuiEditarMensajero;
import View.GuiMain;
import View.GuiMensajero;
import View.GuiServicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class ControladorMensajero implements ActionListener {
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DefaultTableModel tablaModel = new DefaultTableModel();
    GuiMensajero guiMensajero = new GuiMensajero();
    Mensajero mensajero = new Mensajero();
    ServicioDAO servicioDAO = new ServicioDAO();

    public ControladorMensajero(GuiMensajero guiMensajero, Mensajero mensajero) {
        
        this.guiMensajero = guiMensajero;
        this.guiMensajero.btnBuscarServicio.addActionListener(this);
        this.guiMensajero.btnCalificacion.addActionListener(this);
        this.guiMensajero.btnCambiarPerfil.addActionListener(this);
        this.guiMensajero.btnDetalleServicio.addActionListener(this);
        this.guiMensajero.btnHistoricoServicios.addActionListener(this);
        this.guiMensajero.btnCerrarsesion.addActionListener(this);
        this.guiMensajero.btnCobros.addActionListener(this);
        this.mensajero = mensajero;
        this.guiMensajero.txtIdMensajero.setText("C."+this.mensajero.getIdTipoM()+". "+this.mensajero.getIdNumeroM());
        this.guiMensajero.btnCobrar.addActionListener(this);
        Historico(this.guiMensajero.tbServicio);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.guiMensajero.btnHistoricoServicios)) {
            try{
            Historico(this.guiMensajero.tbServicio);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        } else if (ae.getSource().equals(this.guiMensajero.btnBuscarServicio)) {
            try{
                Historico2(this.guiMensajero.tbServicio);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
            
        }
        else if(ae.getSource().equals(this.guiMensajero.btnCerrarsesion)){
            GuiMain volver = new GuiMain();
            ControladorMain controlmain = new ControladorMain(volver);
            volver.setVisible(true);
            guiMensajero.dispose();
        }else if(ae.getSource().equals(this.guiMensajero.btnDetalleServicio)){
            try{
                GuiServicio guiServicio = new GuiServicio();
                ControladorServicio controlServicio = new ControladorServicio(guiServicio, (int) this.guiMensajero.tbServicio.getValueAt(this.guiMensajero.tbServicio.getSelectedRow(), 0));
                guiServicio.btnPagar.setVisible(false);
                guiServicio.btnCalificacion.setVisible(false);
                guiServicio.cbxCambioestado.setVisible(false);
                guiServicio.btncambioestado.setVisible(false);
                guiServicio.txtcomentario.setVisible(false);
                guiServicio.txtponerCalificacion.setVisible(false);
                guiServicio.cbxPagar.setVisible(false);
                guiServicio.btnCalificarAceptar.setVisible(false);
                guiServicio.btnPagarAceptar.setVisible(false);
                guiServicio.setVisible(true);
                guiMensajero.dispose();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }else if(ae.getSource().equals(this.guiMensajero.btnCambiarPerfil)){
            GuiEditarMensajero  guiEdiMen = new GuiEditarMensajero();
            ControladorEdiMens controlEdiCli = new ControladorEdiMens(guiEdiMen,mensajero);
            guiEdiMen.setVisible(true);
        }else if(ae.getSource().equals(this.guiMensajero.btnCobros)){
            try{
            cobro(this.guiMensajero.tbServicio);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "error: "+e.getMessage());
            }
        }else if(ae.getSource().equals(this.guiMensajero.btnCobrar)){
         try{
            PagoMenDao pagoMenDao= new PagoMenDao();
            PagoMensajero pagoMensajero =new PagoMensajero();
            pagoMensajero = pagoMenDao.BuscarPagoMensajero((int)this.guiMensajero.tbServicio.getValueAt(this.guiMensajero.tbServicio.getSelectedRow(), 0));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pagoMensajero.setFechaCobro(formato.format(timestamp));
            pagoMensajero.setEstado('S');
            pagoMenDao.ModificarPagoMensajero(pagoMensajero);
            cobro(this.guiMensajero.tbServicio);
            JOptionPane.showMessageDialog(null, "Cobrado con exito");
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "error: "+e.getMessage()+" "+e.getCause());
         }
        
        }
    }


    public void Historico(JTable tabla) {
        this.tablaModel = (DefaultTableModel) tabla.getModel();
        this.tablaModel = this.servicioDAO.HistoricoServicioM(mensajero.getIdNumeroM(), mensajero.getIdTipoM());
        guiMensajero.tbServicio.setModel(tablaModel);
    }

    public void Historico2(JTable tabla) {
        this.tablaModel = (DefaultTableModel) tabla.getModel();
        if (this.guiMensajero.Fecha.getDate()==null && !this.guiMensajero.txtBuscarServicioId.getText().equals("")) {
            this.tablaModel = this.servicioDAO.HistoricoServicioM(Integer.parseInt(this.guiMensajero.txtBuscarServicioId.getText()),mensajero.getIdNumeroM(), mensajero.getIdTipoM());
        } else if (this.guiMensajero.txtBuscarServicioId.getText().equals("") && this.guiMensajero.Fecha.getDate()!=null) {
            this.tablaModel = this.servicioDAO.HistoricoServicioM(formato.format(this.guiMensajero.Fecha.getDate()),mensajero.getIdNumeroM(), mensajero.getIdTipoM());
        } else if (this.guiMensajero.Fecha.getDate()!=null && !this.guiMensajero.txtBuscarServicioId.getText().equals("")) {
            this.tablaModel = this.servicioDAO.HistoricoServicioM(formato.format(this.guiMensajero.Fecha.getDate()),Integer.parseInt(this.guiMensajero.txtBuscarServicioId.getText()),mensajero.getIdNumeroM(), mensajero.getIdTipoM());
        }
        guiMensajero.tbServicio.setModel(tablaModel);
    }

    public void cobro(JTable tabla){
        this.tablaModel = (DefaultTableModel) tabla.getModel();
        PagoMenDao pagoMenDao= new PagoMenDao();
        PagoMensajero pagoMensajero =new PagoMensajero();
        this.tablaModel = pagoMenDao.TotalPagos(mensajero.getIdNumeroM(), mensajero.getIdTipoM());
        guiMensajero.tbServicio.setModel(tablaModel);
        this.guiMensajero.btnCobrar.setEnabled(true);
        
    }
        

}
