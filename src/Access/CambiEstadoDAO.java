/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.CambioEstado;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class CambiEstadoDAO {

    private Connection conn = null;


    
    public void AdicionarCambioEstado(CambioEstado cambioestado) {
        try {
            

            String sql = "INSERT INTO cambio_estado(i_estado,f_cambio,o_comentario,k_idservicio)"
                    + "VALUES(?,?,?,?);";
            
            
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setString(1, String.valueOf(cambioestado.getI_estado()));
            statement.setTimestamp(2, Timestamp.valueOf(cambioestado.getF_cambio()));
            statement.setString(3,cambioestado.getO_comentario());
            statement.setInt(4,cambioestado.getK_idServicio());
                  

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
    public DefaultTableModel TablaCambioEstado() {
        String titulos[] = {"Proceso", "Estado", "Fecha de Cambio","Comentario","Servicio"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM cambio_estado;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getString(2).charAt(0), result.getTimestamp(3).toString(),
                    result.getString(4), result.getInt(5)};
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
}
