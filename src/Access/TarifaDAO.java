/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Tarifa;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class TarifaDAO {

    public void ModificarTarifa(Tarifa tarifa) {

        try {

            String sql = "UPDATE tarifa SET "
                    + "v_tarifapaquete=?,i_tipopaquete=? WHERE k_idtarifa=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(3, tarifa.getIdTarifa());
            statement.setDouble(1, tarifa.getTarifaPaquete());
            statement.setString(2, String.valueOf(tarifa.getTipoPaquete()));

            
            
            statement.executeUpdate();
            statement.close();
            ConnectionBD.getInstance().commit();
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
        }
    }

    public DefaultTableModel TablaTarifa() {
        String titulos[] = {"Tarifa", "Valor Tarifa Paquete", "Tipo Paquete"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM tarifa;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getDouble(2), result.getString(3).charAt(0)};
                tablaModelo.addRow(data);
            }
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return tablaModelo;
        }
    }
    public Tarifa BuscarTarifa(int idTarifa) {
        Tarifa tarifa = new Tarifa();
        try {

            String sql = "SELECT * FROM tarifa WHERE k_idtarifa=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idTarifa);

            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "No se encontro ese cliente");
                
            } else {
                tarifa.setIdTarifa(result.getInt(1));
                tarifa.setTarifaPaquete(result.getDouble(2));
                tarifa.setTipoPaquete(result.getString(3).charAt(0));

            }

        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return tarifa;
        }
    }
    public Tarifa EncontrarTarifa(char Tarifa) {
        Tarifa tarifa = new Tarifa();
        try {


            String sql = "SELECT k_idtarifa, v_tarifapaquete, i_tipopaquete FROM tarifa WHERE i_tipopaquete=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, String.valueOf(Tarifa));
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "No se encontro ese cliente");
                
            } else {
                tarifa.setIdTarifa(result.getInt(1));
                tarifa.setTarifaPaquete(result.getDouble(2));
                tarifa.setTipoPaquete(result.getString(3).charAt(0));
            }

        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return tarifa;
        }
    }


    public void EliminarTarifa(int idNumero) {
        try {
            
            

            String sql = "DELETE FROM tarifa WHERE k_idtarifa=? ";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idNumero);
            
            statement.executeUpdate();
            statement.close();
            ConnectionBD.getInstance().commit();
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
        }

    }    
    
    public void AdicionarTarifa(Tarifa tarifa){
        
       try {


            String sql = "INSERT INTO tarifa (v_tarifapaquete, i_tipopaquete)VALUES (?,?);";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setDouble(1, tarifa.getTarifaPaquete());
            statement.setString(2, String.valueOf(tarifa.getTipoPaquete()));
            

            statement.executeUpdate();
            statement.close();
            ConnectionBD.getInstance().commit();
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
        }
        
        
    }
    
    
}

