/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.CiudadDAO;
import Access.IndicacionDAO;
import Access.MensajeroDAO;
import Access.ServicioDAO;
import Access.TarifaDAO;
import Model.Ciudad;
import Model.Cliente;
import Model.Indicacion;
import Model.Mensajero;
import Model.Servicio;
import Model.Tarifa;
import View.GuiCliente;
import View.GuiEditarCliente;
import View.GuiMain;
import View.GuiServicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class ControladorCliente  implements ActionListener{
    
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel tablaModel = new DefaultTableModel();
    Cliente cliente = new Cliente();
    Indicacion indicacion = new Indicacion();
    IndicacionDAO indicacionDAO = new IndicacionDAO();
    Servicio servicio = new Servicio();
    ServicioDAO servicioDAO = new ServicioDAO();
    GuiCliente guiCliente = new GuiCliente();
    CiudadDAO ciudadDAO = new CiudadDAO();
    TarifaDAO tarifadao = new TarifaDAO();
    Mensajero mensajero = new Mensajero();
    MensajeroDAO mensajeroDAO =  new MensajeroDAO();
    ArrayList<Mensajero> mensajeros = new ArrayList<>();
    
    public ControladorCliente(GuiCliente guiCliente,Cliente cliente){
        this.guiCliente = guiCliente;
        this.cliente = cliente;
        this.guiCliente.btnAdiccionarDireccion.addActionListener(this);
        this.guiCliente.btnBuscarServicio.addActionListener(this);
        this.guiCliente.btnCambiarPerfil.addActionListener(this);
        this.guiCliente.btnDetallesServicio.addActionListener(this);
        this.guiCliente.btnHistorico.addActionListener(this);
        this.guiCliente.btnSolicitarServicio.addActionListener(this);
        this.guiCliente.btnCerrarSesion.addActionListener(this);
        this.guiCliente.txtIdCliente.setText("C."+this.cliente.getIdTipoC()+". "+this.cliente.getIdNumeroC());
        this.guiCliente.btnProgramarServicio.addActionListener(this);
        Historico(this.guiCliente.tbServicio);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.guiCliente.btnAdiccionarDireccion)){
            try{
                Indicacion();
                JOptionPane.showMessageDialog(null,"Indicacion Añadida");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
            
        }else if(ae.getSource().equals(this.guiCliente.btnBuscarServicio)){
            try{
                Historico2(this.guiCliente.tbServicio);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
                    
        }else if(ae.getSource().equals(this.guiCliente.btnCambiarPerfil)){
            GuiEditarCliente guiEdiCli = new GuiEditarCliente();
            ControladorEdiCliente controlEdiCli = new ControladorEdiCliente(guiEdiCli, cliente);
        }else if(ae.getSource().equals(this.guiCliente.btnDetallesServicio)){
                GuiServicio guiServicio = new GuiServicio();
                ControladorServicio controlServicio = new ControladorServicio(guiServicio, (int) this.guiCliente.tbServicio.getValueAt(this.guiCliente.tbServicio.getSelectedRow(), 0));
                guiServicio.cbxCambioestado.setVisible(false);
                guiServicio.btncambioestado.setVisible(false);
                guiServicio.txtcomentario.setVisible(false);
                guiServicio.txtponerCalificacion.setVisible(false);
                guiServicio.cbxPagar.setVisible(false);
                guiServicio.btnCalificarAceptar.setVisible(false);
                guiServicio.btnPagarAceptar.setVisible(false);
                guiServicio.setVisible(true);
                guiCliente.dispose();
        }else if(ae.getSource().equals(this.guiCliente.btnHistorico)){
           
           try{
               Historico(this.guiCliente.tbServicio);
               
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
           }
        }else if(ae.getSource().equals(this.guiCliente.btnSolicitarServicio)){
            try{
            this.guiCliente.btnAdiccionarDireccion.setEnabled(true);
            Servicio();
            servicioDAO.AdicionarServicio(servicio);
            int i=ObtenerMensajero();
            servicio=servicioDAO.ObtenerServicio(formato.format(Date.valueOf(LocalDateTime.now().toLocalDate()))+" "+this.guiCliente.cbxHInicial.getSelectedItem().toString(), this.guiCliente.cbxTipoTransporte.getSelectedItem().toString().charAt(0), this.guiCliente.cbxTipoRecorrido.getSelectedItem().toString().charAt(0), 'S', 2.2, ciudadDAO.BuscarCodPostal(this.guiCliente.cbxCiudad.getSelectedItem().toString()),mensajeros.get(i).getIdTipoM(), mensajeros.get(i).getIdNumeroM(), this.cliente.getIdTipoC(), this.cliente.getIdNumeroC());
            JOptionPane.showMessageDialog(null,"Solicitando servicio\nIntroduzca las indicaciones");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }else if(ae.getSource().equals(this.guiCliente.btnCerrarSesion)){
            GuiMain volver = new GuiMain();
            ControladorMain controlmain = new ControladorMain(volver);
            volver.setVisible(true);
            guiCliente.dispose();
        }else if(ae.getSource().equals(this.guiCliente.btnProgramarServicio)){
            
            try{
                servicioDAO.actualizarmonto(servicio.getIdServicio(), valormonto());
                JOptionPane.showMessageDialog(null,"Servicio creado con exito");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
    }
    public void Servicio(){
        System.out.println(this.servicio.getIdServicio());
        this.servicio.setFechaInicial(formato.format(Date.valueOf(LocalDateTime.now().toLocalDate()))+" "+this.guiCliente.cbxHInicial.getSelectedItem().toString());
        this.servicio.setTipoDiligencia(this.guiCliente.cbxTipoTransporte.getSelectedItem().toString().charAt(0));
        this.servicio.setTipoRecorrido(this.guiCliente.cbxTipoRecorrido.getSelectedItem().toString().charAt(0));
        this.servicio.setCodPostal(ciudadDAO.BuscarCodPostal(this.guiCliente.cbxCiudad.getSelectedItem().toString()));
        this.servicio.setEstado('S');
        this.servicio.setCosto(2.2);
        this.servicio.setIdNumeroC(this.cliente.getIdNumeroC());
        this.servicio.setIdTipoC(this.cliente.getIdTipoC());
        int i=ObtenerMensajero();
        this.servicio.setIdNumeroM(mensajeros.get(i).getIdNumeroM());
        this.servicio.setIdTipoM(mensajeros.get(i).getIdTipoM());
    }
    
    
    
    
    public void Historico(JTable tabla) {
        this.tablaModel = (DefaultTableModel) tabla.getModel();
        this.tablaModel = this.servicioDAO.HistoricoServicioC(this.cliente.getIdNumeroC(), this.cliente.getIdTipoC());
        guiCliente.tbServicio.setModel(tablaModel);
        
    }
    
    public void Historico2(JTable tabla) {
        this.tablaModel = (DefaultTableModel) tabla.getModel();
        if (this.guiCliente.Fecha.getDate()==null && !this.guiCliente.txtBuscarServicioId.getText().equals("")) {
            this.tablaModel = this.servicioDAO.HistoricoServicioC(Integer.parseInt(this.guiCliente.txtBuscarServicioId.getText()),cliente.getIdNumeroC(), cliente.getIdTipoC());
        } else if (this.guiCliente.txtBuscarServicioId.getText().equals("") && this.guiCliente.Fecha.getDate()!=null) {
            this.tablaModel = this.servicioDAO.HistoricoServicioC(formato.format(this.guiCliente.Fecha.getDate()),cliente.getIdNumeroC(), cliente.getIdTipoC());
        } else if (this.guiCliente.Fecha.getDate()!=null && !this.guiCliente.txtBuscarServicioId.getText().equals("")) {
            this.tablaModel = this.servicioDAO.HistoricoServicioC(formato.format(this.guiCliente.Fecha.getDate()),Integer.parseInt(this.guiCliente.txtBuscarServicioId.getText()),cliente.getIdNumeroC(), cliente.getIdTipoC());
        }
        guiCliente.tbServicio.setModel(tablaModel);
    }
    
    public double valormonto(){
        Ciudad ciudad;
        ciudad=ciudadDAO.encontrarciudad(this.guiCliente.cbxCiudad.getSelectedItem().toString());
        double tasa=ciudad.getComision();
        tasa=tasa+1;
        Tarifa tarifa;
        tarifa=tarifadao.EncontrarTarifa(this.guiCliente.cbxTipoTransporte.getSelectedItem().toString().charAt(0));
        double valorpaquete=tarifa.getTarifaPaquete();
        double tv=tasa*valorpaquete;
        int indica=indicacionDAO.cantidadIndicaciones(servicio.getIdServicio());
        double vsi=tv*indica;
        double tipoviaje=0;
        if(this.guiCliente.cbxTipoRecorrido.getSelectedItem().toString().charAt(0)=='I'){
            tipoviaje=1;
        }else if(this.guiCliente.cbxTipoRecorrido.getSelectedItem().toString().charAt(0)=='V'){
            tipoviaje=1.5;
        }
        double resultado=vsi*tipoviaje;
        return resultado;
    }
    
    public void Indicacion(){
        
        this.indicacion.setDetalle(this.guiCliente.txtDetalle.getText());
        this.indicacion.setDireccion(this.guiCliente.txtDireccion.getText());
        this.indicacion.setIdServicio(this.servicio.getIdServicio());
        indicacionDAO.AdicionarIndicacion(indicacion);
    }
    
    public int ObtenerMensajero(){
        this.mensajeros=mensajeroDAO.ObtenerMensajeros(this.guiCliente.cbxHInicial.getSelectedItem().toString(), this.guiCliente.cbxDIA.getSelectedItem().toString().charAt(0),this.guiCliente.cbxCiudad.getSelectedItem().toString().charAt(0),this.guiCliente.cbxTipoTransporte.getSelectedItem().toString().charAt(0));
        return (int) (Math.random()*((mensajeros.size()-1)-0)) + 0;
       
    }

}
