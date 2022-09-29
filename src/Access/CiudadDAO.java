/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Ciudad;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class CiudadDAO {


    public Ciudad BuscarCiudad(int codPostal) {
        Ciudad ciudad = new Ciudad();

        try {


            String sql = "SELECT * FROM ciudad WHERE k_codpostal=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, codPostal);

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                JOptionPane.showMessageDialog(null, "No se encontro ese cliente");

            } else {

                ciudad.setCodPostal(result.getInt(1));
                ciudad.setNombre(result.getString(2));
                ciudad.setComision(result.getDouble(3));

            }
            
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return ciudad;
        }
    }
    public DefaultTableModel TablaCiudad() {
        String titulos[] = {"Codigo Postal", "Nombre Ciudad", "tasa Comision"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM ciudad;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getString(2), result.getDouble(3)};
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

    public void ModificarCiudad(Ciudad ciudad) {
        try {


            String sql = "UPDATE ciudad SET n_nombre=?, t_comision=? WHERE k_codpostal=?;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            
            statement.setString(1, ciudad.getNombre());
            statement.setDouble(2, ciudad.getComision());
            statement.setInt(3, ciudad.getCodPostal());

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
    public void AdicionarCiudad(Ciudad ciudad) {

        try {


            String sql = "INSERT INTO ciudad(k_codpostal,n_nombre,t_comision) VALUES (?,?,?)";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, ciudad.getCodPostal());
            statement.setString(2, ciudad.getNombre());
            statement.setDouble(3, ciudad.getComision());
            
            System.out.println(ciudad.getCodPostal()+" "+ciudad.getComision()+" "+ciudad.getNombre());
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
    public int BuscarCodPostal(String ciudad){
        
        int codpostal = -1 ;

        try {


            String sql = "SELECT k_codpostal FROM ciudad WHERE n_nombre=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, ciudad);

            ResultSet result = statement.executeQuery();
 
            if (result.next()) {
                codpostal = result.getInt(1);
                

            } else {

                JOptionPane.showMessageDialog(null, "No se encontro ese ciudad");

            }
            
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return codpostal;
        }
    }
    public Ciudad encontrarciudad(String ciudad){
        Ciudad cd= new Ciudad();
        int codpostal = -1 ;

        try {


            String sql = "SELECT k_codpostal,n_nombre,t_comision FROM ciudad WHERE n_nombre=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, ciudad);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                cd.setCodPostal(result.getInt(1));
                cd.setNombre(result.getString(2));
                cd.setComision(result.getDouble(3));
            } else {

                JOptionPane.showMessageDialog(null, "No se encontro ese ciudad");
            }
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return cd;
        }
        
    }
}
