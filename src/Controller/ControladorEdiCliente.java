/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Access.ClienteDAO;
import Model.Cliente;
import View.GuiEditarCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class ControladorEdiCliente  implements ActionListener{
    
    GuiEditarCliente guiEditarCliente = new GuiEditarCliente();
    Cliente cliente=new Cliente();
    ClienteDAO consulta = new ClienteDAO();
    
    
    
    
    public ControladorEdiCliente(GuiEditarCliente guiEditarCliente, Cliente cliente){
        
        this.guiEditarCliente = guiEditarCliente;
        this.cliente= cliente;
        this.guiEditarCliente.btnActualizar.addActionListener(this);
        MostrarDatos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == guiEditarCliente.btnActualizar){
            try {
                Adicionar();
                consulta.AdicionarCliente(cliente);
                JOptionPane.showMessageDialog(null,"Cliente Actualizado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Parametros incorrectos, reviselos\nError: " + ex);
            }
            
        }
        
    }
    public void MostrarDatos(){
            this.guiEditarCliente.txtNumDoc.setText(String.valueOf(this.cliente.getIdNumeroC()));
            this.guiEditarCliente.txtTipoDoc.setText(String.valueOf(this.cliente.getIdTipoC()));
            this.cliente = consulta.BuscarCliente(this.cliente.getIdNumeroC(), this.cliente.getIdTipoC());
            this.guiEditarCliente.txtNumDoc.setText(String.valueOf(this.cliente.getIdNumeroC()));
            this.guiEditarCliente.txtTipoDoc.setText(String.valueOf(this.cliente.getIdTipoC()));
            this.guiEditarCliente.txtPrimerNombre.setText(this.cliente.getPrimerNombre());
            this.guiEditarCliente.txtSegundoNombre.setText(this.cliente.getSegundoNombre());
            this.guiEditarCliente.txtPrimerApellido.setText(this.cliente.getPrimerApellido());
            this.guiEditarCliente.txtSegundoApellido.setText(this.cliente.getSegundoApellido());
            this.guiEditarCliente.txtCorreo.setText(this.cliente.getCorreo());
            this.guiEditarCliente.txtConfCorreo.setText(this.cliente.getCorreo());
            this.guiEditarCliente.txtTelefono.setText(this.cliente.getTelefono());
            this.guiEditarCliente.txtNumCel.setText(this.cliente.getCelular());
            this.guiEditarCliente.txtDireccion.setText(this.cliente.getDireccion());
            if (this.cliente.getSexo()=='M'){
                this.guiEditarCliente.cbxGenero.setSelectedItem("Masculino");
            }
            else if (this.cliente.getSexo()=='F'){
                this.guiEditarCliente.cbxGenero.setSelectedItem("Femenino");
            }
    }
    public void Adicionar(){
        
        this.cliente.setIdTipoC(this.guiEditarCliente.txtTipoDoc.getText().toString().charAt(0));
        this.cliente.setIdNumeroC(Integer.parseInt(this.guiEditarCliente.txtNumDoc.getText()));
        this.cliente.setPrimerNombre(this.guiEditarCliente.txtPrimerNombre.getText());
        this.cliente.setSegundoNombre(this.guiEditarCliente.txtSegundoNombre.getText());
        this.cliente.setPrimerApellido(this.guiEditarCliente.txtPrimerApellido.getText());
        this.cliente.setSegundoApellido(this.guiEditarCliente.txtSegundoApellido.getText());
        this.cliente.setCorreo(this.guiEditarCliente.txtCorreo.getText());
        this.cliente.setCelular(this.guiEditarCliente.txtNumCel.getText());
        this.cliente.setTelefono(this.guiEditarCliente.txtTelefono.getText());
        this.cliente.setDireccion(this.guiEditarCliente.txtDireccion.getText());
        this.cliente.setSexo(this.guiEditarCliente.cbxGenero.getSelectedItem().toString().charAt(0));
        
        
    }
}
