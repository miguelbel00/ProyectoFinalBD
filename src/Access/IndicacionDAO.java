package Access;


import Model.Cliente;
import Model.Indicacion;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class IndicacionDAO {

    public void AdicionarIndicacion(Indicacion indicacion) {
        try {

            String sql = "INSERT INTO indicaciones(o_direccion,n_detalle,k_idservicio)VALUES(?,?,?);";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
          
            statement.setString(1, indicacion.getDireccion());
            statement.setString(2, indicacion.getDetalle());
            statement.setInt(3, indicacion.getIdServicio());
            

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


    public void EliminarIndicacion(int idDireccion) {
        try {

            String sql = "DELETE FROM indicaciones WHERE k_iddireccion=? ";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idDireccion);
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


    public void ModificarIndicacion(Indicacion indicacion) {

        try {


            String sql = "UPDATE indicaciones SET o_direccion=?,n_detalle=? WHERE k_iddireccion=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, indicacion.getDireccion());
            statement.setString(2, indicacion.getDetalle());
            statement.setInt(3, indicacion.getIdDireccion());
            
            
            
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
    public DefaultTableModel IndicacionServicio(int id_servicio){
        String titulos[]= {"IdDireccion","Direccion","Detalle"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT indicaciones.k_iddireccion,indicaciones.o_direccion,indicaciones.n_detalle FROM indicaciones, servicio WHERE servicio.k_idservicio=? AND indicaciones.k_idservicio=servicio.k_idservicio;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id_servicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                
                Object[] data = {result.getInt(1), result.getString(2), result.getString(3)};
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
    public DefaultTableModel TablaIndicacion() {
        String titulos[] = {"Indicaciones", "Direccion", "Detalle","Servicio"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM indicaciones;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1),result.getString(2), result.getString(3),result.getInt(4)};
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

    public Indicacion BuscarIndicacion(int idDireccion) {

        boolean existe = false;
        Indicacion indicacion = new Indicacion();

        try {


            String sql = "SELECT * FROM indicaciones WHERE k_iddireccion=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idDireccion);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                
                indicacion.setIdDireccion(idDireccion);
                indicacion.setDireccion(result.getString(2));
                indicacion.setDetalle(result.getString(3));
                indicacion.setIdServicio(result.getInt(4));
                
                
                existe = true;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return indicacion;
            } else {
                return null;

            }
        }
    }
    public int cantidadIndicaciones(int k_idservicio){

        int cuantos=0;
        try {


            String sql = "SELECT COUNT(*) FROM indicaciones WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, k_idservicio);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                cuantos=result.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return cuantos;
        }
    }
}