/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.ClienteDAO;
import Access.MensajeroDAO;
import Model.Cliente;
import Model.Mensajero;
import View.GuiAdmin;
import View.GuiCliente;
import View.GuiMain;
import View.GuiMensajero;
import View.GuiRegisClien;
import View.GuiRegisMens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class ControladorMain implements ActionListener{
    
    Long[] Admins ={20182020070L,20192020105L,20192020091L,20192020083L};
    Mensajero mensajero = new Mensajero();
    Cliente cliente = new Cliente();
    GuiMain guiMain = new GuiMain();
    MensajeroDAO mensajerodao= new MensajeroDAO();
    ClienteDAO clientedao= new ClienteDAO();
    public ControladorMain(GuiMain guiMain){
        this.guiMain = guiMain;
        this.guiMain.btnRegistrarse.addActionListener(this);
        this.guiMain.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == guiMain.btnRegistrarse){
             String opciones[] = {"Cliente","Mensajero"};
            if("Cliente".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                GuiRegisClien guiRegisClien = new GuiRegisClien();
                ControladorRegisClien controlRegisClien = new ControladorRegisClien(guiRegisClien); 
                guiRegisClien.setVisible(true);
                guiMain.dispose();
            }else if ("Mensajero".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                GuiRegisMens guiRegisMens = new GuiRegisMens();
                ControladorRegisMens controlRegisMens = new ControladorRegisMens(guiRegisMens);
                guiRegisMens.setVisible(true);
                guiMain.dispose();
            }else if("Seleccione".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(null,"Seleccione un tipo de usuario");
            } 
        }
        else if(e.getSource() == guiMain.btnIngresar){
            
            String opciones[] = {"Cliente","Mensajero","Admin"};
            if("Cliente".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                try{
                if(clientedao.comprobarCliente(Integer.parseInt(guiMain.txtIdUsuario.getText()), this.guiMain.cbxTipoIdentificacion.getSelectedItem().toString().charAt(2))){
                    GuiCliente guicliente = new GuiCliente();
                    
                    cliente = clientedao.BuscarCliente(Integer.parseInt(guiMain.txtIdUsuario.getText()),guiMain.cbxTipoIdentificacion.getSelectedItem().toString().charAt(2));
                    ControladorCliente controlcliente = new ControladorCliente(guicliente, cliente); 
                    guicliente.setVisible(true);
                    guiMain.dispose();
                }}catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Cliente no encontrado");
                }
            }else if ("Mensajero".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                try{
                if(mensajerodao.comprobarMensajero(Integer.parseInt(guiMain.txtIdUsuario.getText()), this.guiMain.cbxTipoIdentificacion.getSelectedItem().toString().charAt(2))){
                    GuiMensajero guimensajero = new GuiMensajero();
                    mensajero=mensajerodao.BuscarMensajero(Integer.parseInt(guiMain.txtIdUsuario.getText()),guiMain.cbxTipoIdentificacion.getSelectedItem().toString().charAt(2));
                    ControladorMensajero controlmensajero = new ControladorMensajero(guimensajero,mensajero); 
                    guimensajero.setVisible(true);
                    guiMain.dispose();
                }}catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Mensajero no encontrado");
                }
            }else if("Admin".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                for(int i=0;i<=4;i++){
                    
                   if(Admins[i]== Long.parseLong(this.guiMain.txtIdUsuario.getText().toString())){
                       GuiAdmin guiAdmin = new GuiAdmin();
                       ControladorAdmin controlAdmin = new ControladorAdmin(guiAdmin);
                       guiAdmin.setVisible(true);
                       guiMain.dispose();
                       break;
                   }
                    
                }
                
               
            }else if("Seleccione".equals(this.guiMain.cbxTipoUsuario.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(null,"Seleccione un tipo de usuario");
            }
            
        }
    }
}
