/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Calificacion;
import Model.Horario;
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
public class CalificacionDAO {




    public void AdicionarCalificacion(Calificacion calificacion) {
        try {


            String sql = "INSERT INTO calificacion (k_idservicio,q_valor)"
                    + "VALUES (?,?);";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            
            statement.setInt(1, calificacion.getIdServicio());
            statement.setInt(2, calificacion.getValor());

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


    public void EliminarCalificacion(int IdCalificacion) {
        try {

            String sql = "DELETE FROM calificacion WHERE k_idcalificacion=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, IdCalificacion);
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
    public DefaultTableModel TablaCalificacion() {
        String titulos[] = {"Calificacion", "Servicio", "Valor"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM calificacion;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1),result.getInt(2),result.getInt(3)};
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


    public void ModificarCalificacion(Calificacion calificacion) {

        try {


            String sql = "UPDATE calificacion SET q_valor=? WHERE k_idcalificacion=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(2, calificacion.getIdCalificacion());
            statement.setInt(1, calificacion.getValor());

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

    public Calificacion BuscarCalificacion(int IdCalificacion) {

        boolean existe = false;
        Calificacion calificacion = new Calificacion();

        try {


            String sql = "SELECT * FROM calificacion WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, IdCalificacion);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                calificacion.setIdCalificacion(result.getInt(1));
                calificacion.setIdServicio(result.getInt(2));
                if(result.getObject(3)!=null){
                    calificacion.setValor(result.getInt(3));
                }else{
                    calificacion.setValor(0);
                }
                

                existe = true;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return calificacion;

            } else {
                return null;
            }

        }

    }

}
