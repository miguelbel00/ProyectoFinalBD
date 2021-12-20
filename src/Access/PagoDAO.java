package Access;

import Model.Pago;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PagoDAO {
    
    public void AdicionarPago(Pago pago){
        
        try {
            String sql = "INSERT INTO pago (f_pago,i_metodopago,k_idservicio) values (?,?,?);";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setTimestamp(1, Timestamp.valueOf(pago.getFechaPago()));
            statement.setString(2,String.valueOf(pago.getMetodoPago()));
            statement.setInt(3,pago.getIdServicio());
            
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
    
    public void EliminarPago(int idPago){
        try {

            String sql = "DELETE FROM pago WHERE k_idpago=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idPago);
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
    
    public void ModificarPago(Pago pago){
        
        try {
            
            String sql = "UPDATE pago SET f_pago=?,i_metodopago=? WHERE k_idpago=?";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(3,pago.getIdPago());
            statement.setTimestamp(1, Timestamp.valueOf(pago.getFechaPago()));
            statement.setString(2,String.valueOf(pago.getMetodoPago()));

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
    
    
    public Pago BuscarPago(int idpago){
        
        
        boolean existe = false;
        Pago pago = new Pago();
        try {

            String sql = "SELECT * FROM pago WHERE k_idpago=?"; 
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1,idpago);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                pago.setFechaPago(result.getTimestamp(2).toString());
                pago.setMetodoPago(result.getString(3).charAt(0));
                pago.setIdServicio(result.getInt(4));
                
                existe = true;

            }

        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return pago;
        }
        
        
    }
    public DefaultTableModel TablaPago() {
        String titulos[] = {"Pago", "Fecha Pago", "Metodo Pago","Servicio"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM pago;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2).toString(), result.getString(3).charAt(0), result.getInt(4)};
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
    public Pago BuscarPagos(int idservicio){
        
        
        boolean existe = false;
        Pago pago = new Pago();
        try {

            String sql = "SELECT * FROM pago WHERE k_idservicio=?"; 
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1,idservicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                pago.setIdPago(result.getInt(1));
                pago.setFechaPago(result.getTimestamp(2).toString());
                pago.setMetodoPago(result.getString(3).charAt(0));
                pago.setIdServicio(result.getInt(4));
                
                existe = true;

            }

        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return pago;
        }
        
        
    }
    
    
    
    
    
 
}
