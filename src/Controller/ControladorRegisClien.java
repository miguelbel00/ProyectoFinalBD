/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.ClienteDAO;
import Model.Cliente;
import View.GuiMain;
import View.GuiRegisClien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class ControladorRegisClien  implements ActionListener{
    
    GuiRegisClien guiRegisClien = new GuiRegisClien();
    Cliente cliente=new Cliente();
    ClienteDAO clientedao = new ClienteDAO();
    
    
    
    
    public ControladorRegisClien(GuiRegisClien guiRegisClien){
        this.guiRegisClien = guiRegisClien;
        this.guiRegisClien.btnRegistrarse.addActionListener(this);
        this.guiRegisClien.btnVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == guiRegisClien.btnRegistrarse){
            try {
                Adicionar();
                clientedao.AdicionarCliente(cliente);
                JOptionPane.showMessageDialog(null,"Cliente Registrado");
                       
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Parametros incorrectos, Reviselos\nError: " + ex);
            }
            
        }else if(e.getSource().equals(guiRegisClien.btnVolver)){
            GuiMain guiMain = new GuiMain();
            ControladorMain controlMain = new ControladorMain(guiMain);
            guiMain.setVisible(true);
            guiRegisClien.dispose();
        }
        
    }
    
    public void Adicionar(){
        
        cliente.setIdTipoC(this.guiRegisClien.cbxTipoIdenti.getSelectedItem().toString().charAt(2));
        cliente.setIdNumeroC(Integer.parseInt(this.guiRegisClien.txtDocumento.getText()));
        cliente.setPrimerNombre(this.guiRegisClien.txtNombre.getText());
        cliente.setSegundoNombre(this.guiRegisClien.txtNombre2.getText());
        cliente.setPrimerApellido(this.guiRegisClien.txtApellido.getText());
        cliente.setSegundoApellido(this.guiRegisClien.txtApellido2.getText());
        cliente.setCorreo(this.guiRegisClien.txtCorreo.getText());
        cliente.setCelular(this.guiRegisClien.txtCelular.getText());
        cliente.setTelefono(this.guiRegisClien.txtTelefonoFijo.getText());
        cliente.setDireccion(this.guiRegisClien.txtDireccion.getText());
        cliente.setSexo(this.guiRegisClien.cbxGenero.getSelectedItem().toString().charAt(0));
        
        
    }
}
