/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.MensajeroDAO;
import Access.PagoMenDao;
import Access.ServicioDAO;
import Model.Mensajero;
import Model.PagoMensajero;
import View.Cobros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class ControladorCobros implements ActionListener{
    DefaultTableModel tablaModel = new DefaultTableModel();
    Mensajero mensajero = new Mensajero();
    MensajeroDAO mensajerodao= new MensajeroDAO();
    PagoMensajero pagomensajero= new PagoMensajero();
    PagoMenDao pagomensajerodao= new PagoMenDao();
    ServicioDAO serviciodao = new ServicioDAO();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Cobros co= new Cobros();
    public ControladorCobros(Cobros co){
        this.co=co;
        this.co.btnActualizar.addActionListener(this);
        this.co.btnPagar.addActionListener(this);
        DibujarCobro(this.co.tbCobros);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.co.btnActualizar)){
            try{
            DibujarCobro(this.co.tbCobros);
            
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }else if(ae.getSource().equals(this.co.btnPagar)){
            
            try{
                Pagar();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage() +"sss"+ ex.getCause());
            }
        }
    }
    public void DibujarCobro(JTable tabla){
        this.tablaModel=(DefaultTableModel) tabla.getModel();
        this.tablaModel=this.mensajerodao.TablaNombre();
        co.tbCobros.setModel(tablaModel);
    }
    public void Pagar(){
        char tipomensajero=(char)this.co.tbCobros.getValueAt(this.co.tbCobros.getSelectedRow(), 0);;
        int numeromensajero=(int)this.co.tbCobros.getValueAt(this.co.tbCobros.getSelectedRow(), 1);
        String fecha=pagomensajerodao.ObtenerFecha(tipomensajero, numeromensajero);
        double montopago=serviciodao.Pagarmensajeroservicio(fecha,tipomensajero,numeromensajero);
        if(montopago == 0){
            JOptionPane.showMessageDialog(null, "NO hay cobros por generar");
        }
        else{
           adicionarPago(montopago,tipomensajero,numeromensajero);
            pagomensajerodao.AdicionarPagoMens(pagomensajero); 
        }
        adicionarPago(montopago,tipomensajero,numeromensajero);
        pagomensajerodao.AdicionarPagoMens(pagomensajero);
        
    }
    public void adicionarPago(Double monto, char tp, int num){
        pagomensajero.setEstado('N');
        pagomensajero.setMonto(monto);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String Fecha = formato.format(timestamp);
        pagomensajero.setFechaCobroGenerado(Fecha);
        pagomensajero.setIdTipoM(tp);
        pagomensajero.setIdNumeroM(num);
    }
}
