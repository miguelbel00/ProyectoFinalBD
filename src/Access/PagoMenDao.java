/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.PagoMensajero;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class PagoMenDao {
    
    public void AdicionarPagoMens(PagoMensajero pagoMensajero) {
        try {
            
            String sql = "INSERT INTO pagomensajero(i_estado,v_monto,f_cobrogenerado,k_idtipo,k_idnumero)"
                    + "VALUES(?,?,?,?,?);";
            
            
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
           
            statement.setString(1, String.valueOf(pagoMensajero.getEstado()));
            statement.setDouble(2, pagoMensajero.getMonto());
            statement.setTimestamp(3, Timestamp.valueOf(pagoMensajero.getFechaCobroGenerado()));
            statement.setString(4, String.valueOf(pagoMensajero.getIdTipoM()));
            statement.setInt(5, pagoMensajero.getIdNumeroM());
           
                  

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
    public DefaultTableModel TablaPagoMensajero() {
        String titulos[] = {"Cobro", "Fecha Cobro", "Estado","Monto","Fecha Generado","TipoMensajeo","NumeroMensajero"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM pagomensajero;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2),result.getString(3).charAt(0), result.getInt(4), result.getTimestamp(5),
                    result.getString(6).charAt(0), result.getInt(7)};
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
    
    public void EliminarPagoMensajero(int idNumero) {
        try {
            String sql = "DELETE FROM pagomensajero WHERE k_idcobro=? ";
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

    public void ModificarPagoMensajero(PagoMensajero pagoMensajero) {
        try {
            String sql = "UPDATE pagomensajero SET f_cobro=?,i_estado=?,v_monto=?,f_cobrogenerado=?,k_idtipo=?,k_idnumero=? WHERE k_idcobro=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(7, pagoMensajero.getIdCobro());
            statement.setTimestamp(1, Timestamp.valueOf(pagoMensajero.getFechaCobro()));
            statement.setString(2, String.valueOf(pagoMensajero.getEstado()));
            statement.setDouble(3, pagoMensajero.getMonto());
            statement.setTimestamp(4, Timestamp.valueOf(pagoMensajero.getFechaCobroGenerado()));
            statement.setString(5, String.valueOf(pagoMensajero.getIdTipoM()));
            statement.setInt(6, pagoMensajero.getIdNumeroM());
            
            

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

    
    public PagoMensajero BuscarPagoMensajero(int idCobro){
        
        boolean existe = false;
        PagoMensajero pagoMensajero = new PagoMensajero();
        try {

            String sql = "SELECT * FROM pagomensajero WHERE k_idcobro=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCobro);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
               pagoMensajero.setIdCobro(idCobro);
               if (result.getObject(2) != null) {
                    pagoMensajero.setFechaCobro(result.getTimestamp(2).toString());
                } else {
                    pagoMensajero.setFechaCobro("");
                }
               pagoMensajero.setEstado(result.getString(3).charAt(0));
               pagoMensajero.setMonto(result.getDouble(4));
               pagoMensajero.setFechaCobroGenerado(String.valueOf(result.getTimestamp(5)));
               pagoMensajero.setIdTipoM(result.getString(6).charAt(0));
               pagoMensajero.setIdNumeroM(result.getInt(7));
                
                existe = true;

            }
                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return pagoMensajero;
                
            } else {
                return null;
            }
        }
        
        
        
        
    }
    public DefaultTableModel TotalPagos(int idNumero,char idTipo) {
        String titulos[] = {"IdCobro", "Fecha Cobro", "Cobro Generado", "Monto", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT k_idcobro,f_cobro,f_cobrogenerado,v_monto,i_estado FROM pagomensajero WHERE k_idnumero=? AND k_idtipo=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idTipo));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1),result.getTimestamp(2),result.getTimestamp(3),
                        result.getInt(4),result.getString(5).charAt(0)};
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
    public String ObtenerFecha(char tp, int num){
        String fecha="";
        try {

            String sql = "SELECT f_cobrogenerado FROM pagomensajero WHERE k_idtipo=? AND k_idnumero=? ORDER BY f_cobrogenerado DESC limit 1";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, String.valueOf(tp));
            statement.setInt(2, num);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                fecha=result.getTimestamp(1).toString();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return fecha;
        }
    }
}
