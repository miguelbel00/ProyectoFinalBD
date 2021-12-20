/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Ciudad;
import Model.CiudadTarifa;
import Model.Tarifa;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Hanna Valentina
 */
public class CiudadTarifaDAO {


    public void AdicionarTarifaACiudad(CiudadTarifa ciudadTarifa) {
        try {

            String sql = "INSERT INTO ciudad_tarifa (k_idtarifa, k_codpostal) VALUES (?,?);";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, ciudadTarifa.getIdTarifa());
            statement.setInt(2, ciudadTarifa.getCodPostal());

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

    public CiudadTarifa BuscarTarifaCiudad(int idCodPostal) {
        CiudadTarifa ciudadTarifa= new CiudadTarifa();
        try {


            String sql = "SELECT * FROM ciudad_tarifa ct, tarifa t, ciudad c "
                    + "WHERE ct.k_idtarifa= t.k_idtarifa AND ct.k_codpostal=c.k_codpostal WHERE c.k_codpostal=?";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCodPostal);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ciudadTarifa.setCodPostal(rs.getInt(1));
                ciudadTarifa.setIdTarifa(rs.getInt(2));

                
            }
        }catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            System.out.println("Error al consultar detalles de una factura: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return ciudadTarifa;
        }
    }
}
