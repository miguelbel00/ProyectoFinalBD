/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Calificacion;
import Model.Servicio;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class ServicioDAO {

    public void AdicionarServicio(Servicio servicio) {
        try {

            String sql = "INSERT INTO servicio (f_inicial,i_tdiligencia,i_trecorrido,i_estado,v_costo,k_codpostal,k_idtipo,k_idnumero,k_idtipoc,k_idnumeroc)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setTimestamp(1, Timestamp.valueOf((servicio.getFechaInicial())));
            statement.setString(2, String.valueOf(servicio.getTipoDiligencia()));
            statement.setString(3, String.valueOf(servicio.getTipoRecorrido()));
            statement.setString(4, String.valueOf(servicio.getEstado()));
            statement.setDouble(5, servicio.getCosto());
            statement.setInt(6, servicio.getCodPostal());
            statement.setString(7, String.valueOf(servicio.getIdTipoM()));
            statement.setInt(8, servicio.getIdNumeroM());
            statement.setString(9, String.valueOf(servicio.getIdTipoC()));
            statement.setInt(10, servicio.getIdNumeroC());

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

    public Servicio ObtenerServicio(String fechaini, char tdili, char trecor, char iesta, double costo, int postal, char idmensaje, int idmen, char idcliente, int idcli) {
        Servicio se = new Servicio();
        try {

            String sql = "SELECT * FROM servicio WHERE servicio.f_inicial=? AND servicio.i_tdiligencia=? AND servicio.i_trecorrido=? AND servicio.i_estado=? AND servicio.v_costo=? AND servicio.k_codpostal=? AND servicio.k_idtipo=?  AND servicio.k_idnumero=? AND servicio.k_idtipoc=? AND servicio.k_idnumeroc=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setTimestamp(1, Timestamp.valueOf((fechaini)));
            statement.setString(2, String.valueOf(tdili));
            statement.setString(3, String.valueOf(trecor));
            statement.setString(4, String.valueOf(iesta));
            statement.setDouble(5, costo);
            statement.setInt(6, postal);
            statement.setString(7, String.valueOf(idmensaje));
            statement.setInt(8, idmen);
            statement.setString(9, String.valueOf(idcliente));
            statement.setInt(10, idcli);

            ResultSet result = statement.executeQuery();
            while (result.next()) {

                se.setIdServicio(result.getInt(1));
                se.setFechaInicial(fechaini);
                se.setTipoDiligencia(tdili);
                se.setTipoRecorrido(trecor);
                se.setEstado(iesta);
                se.setCosto(costo);
                se.setCodPostal(postal);
                se.setIdTipoM(idmensaje);
                se.setIdNumeroM(idmen);
                se.setIdTipoC(idcliente);
                se.setIdNumeroM(idcli);
                if (result.getObject(3) != null) {
                    se.setFechaFinal(result.getTimestamp(3).toString());
                } else {
                    se.setFechaFinal("");
                }
            }

        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return se;
        }
    }

    public void EliminarServicio(int IdServicio) {
        try {
            String sql = "DELETE FROM servicio WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, IdServicio);
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


    public void ModificarServicio(Servicio servicio) {
        try {

            String sql = "UPDATE servicio SET f_final=?,i_tdiligencia=?,i_trecorrido=?,i_estado=?,v_costo=? WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setTimestamp(1, Timestamp.valueOf(servicio.getFechaFinal()));
            statement.setString(2, String.valueOf(servicio.getTipoDiligencia()));
            statement.setString(3, String.valueOf(servicio.getTipoRecorrido()));
            statement.setString(4, String.valueOf(servicio.getEstado()));
            statement.setDouble(5, servicio.getCosto());
            statement.setInt(6, servicio.getIdServicio());

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
    public double Pagarmensajeroservicio(String fecha, char tp, int num){
        double valor=0;
        try {

            String sql = "SELECT SUM(v_costo) FROM servicio WHERE f_final>? AND k_idnumero=? AND k_idtipo=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setTimestamp(1, Timestamp.valueOf(fecha));
            statement.setInt(2, num);
            statement.setString(3, String.valueOf(tp));

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                valor=result.getDouble(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            return valor;
        }
    }
    public void actualizarmonto(int IdServicio, double monto) {
        try {

            String sql = "UPDATE servicio SET v_costo=? WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setDouble(1, monto);
            statement.setInt(2, IdServicio);
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

    public Servicio BuscarServicio(int IdServicio) {

        boolean existe = false;
        Servicio servicio = new Servicio();
        try {

            String sql = "SELECT * FROM servicio WHERE k_idservicio=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, IdServicio);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                servicio.setIdServicio(IdServicio);
                servicio.setFechaInicial(result.getTimestamp(2).toString());
                if (result.getObject(3) != null) {
                    servicio.setFechaFinal(result.getTimestamp(3).toString());
                } else {
                    servicio.setFechaFinal("");
                }
                servicio.setTipoDiligencia(result.getString(4).charAt(0));
                servicio.setTipoRecorrido(result.getString(5).charAt(0));
                servicio.setEstado(result.getString(6).charAt(0));
                servicio.setCosto(result.getDouble(7));
                servicio.setCodPostal(result.getInt(8));
                servicio.setIdTipoM(result.getString(9).charAt(0));
                servicio.setIdNumeroM(result.getInt(10));
                servicio.setIdTipoC(result.getString(11).charAt(0));
                servicio.setIdNumeroC(result.getInt(12));

                existe = true;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return servicio;

            } else {
                return null;
            }

        }

    }

    public DefaultTableModel TablaServicio() {
        String titulos[] = {"Servicio", "Fecha Inicio", "Fecha Final","Diligencia","Recorrido","Estado","Costo","Codigo Postal","TipoMensajeo","NumeroMensajero","TipoCliente","NumeroCliente"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM servicio;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2).toString(), result.getTimestamp(3).toString(), result.getString(4).charAt(0), result.getString(5).charAt(0),
                    result.getString(6).charAt(0),result.getDouble(7), result.getInt(8), result.getString(9).charAt(0), result.getInt(10), result.getString(11).charAt(0), result.getInt(12)};
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

    public DefaultTableModel HistoricoServicioM(int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,mensajero WHERE mensajero.k_idnumero=? AND mensajero.k_idtipo=? AND mensajero.k_idnumero=servicio.k_idnumero AND mensajero.k_idtipo=servicio.k_idtipo;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioM(String fecha, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,mensajero WHERE mensajero.k_idnumero=? AND mensajero.k_idtipo=? AND servicio.f_inicial>? AND f_inicial<? AND mensajero.k_idnumero=servicio.k_idnumero AND mensajero.k_idtipo=servicio.k_idtipo";
            String FechaInicial = fecha + " 00:00:00";
            String FechaFinal = fecha + " 23:59:59";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setTimestamp(3, Timestamp.valueOf(FechaInicial));
            statement.setTimestamp(4, Timestamp.valueOf(FechaFinal));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioM(int id_servicio, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,mensajero WHERE mensajero.k_idnumero=? AND mensajero.k_idtipo=? AND servicio.k_idservicio=? AND mensajero.k_idnumero=servicio.k_idnumero AND mensajero.k_idtipo=servicio.k_idtipo;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setInt(3, id_servicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioM(String fecha, int id_servicio, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,mensajero WHERE mensajero.k_idnumero=? AND mensajero.k_idtipo=? AND servicio.f_inicial>? AND f_inicial<? AND servicio.k_idservicio=? AND mensajero.k_idnumero=servicio.k_idnumero AND mensajero.k_idtipo=servicio.k_idtipo;";
            String FechaInicial = fecha + " 00:00:00";
            String FechaFinal = fecha + " 23:59:59";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setTimestamp(3, Timestamp.valueOf(FechaInicial));
            statement.setTimestamp(4, Timestamp.valueOf(FechaFinal));
            statement.setInt(5, id_servicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioC(int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,cliente WHERE cliente.k_idnumeroc=? AND cliente.k_idtipoc=? AND cliente.k_idnumeroc=servicio.k_idnumeroc AND cliente.k_idtipoc=servicio.k_idtipoc;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioC(String fecha, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,cliente WHERE cliente.k_idnumeroc=? AND cliente.k_idtipoc=? AND servicio.f_inicial>? AND f_inicial<? AND cliente.k_idnumeroc=servicio.k_idnumeroc AND cliente.k_idtipoc=servicio.k_idtipoc";
            String FechaInicial = fecha + " 00:00:00";
            String FechaFinal = fecha + " 23:59:59";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setTimestamp(3, Timestamp.valueOf(FechaInicial));
            statement.setTimestamp(4, Timestamp.valueOf(FechaFinal));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioC(int id_servicio, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,cliente WHERE cliente.k_idnumeroc=? AND cliente.k_idtipoc=? AND servicio.k_idservicio=? AND cliente.k_idnumeroc=servicio.k_idnumeroc AND cliente.k_idtipoc=servicio.k_idtipoc;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setInt(3, id_servicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public DefaultTableModel HistoricoServicioC(String fecha, int id_servicio, int idNumero, char idtipo) {
        String titulos[] = {"Servicio", "Fecha Inicial", "Fecha Final", "Costo", "estado"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT servicio.k_idservicio, servicio.f_inicial, servicio.f_final, servicio.v_costo,servicio.i_estado FROM servicio,cliente WHERE cliente.k_idnumeroc=? AND cliente.k_idtipoc=? AND servicio.f_inicial>? AND f_inicial<? AND servicio.k_idservicio=? AND cliente.k_idnumeroc=servicio.k_idnumeroc AND cliente.k_idtipoc=servicio.k_idtipoc;";
            String FechaInicial = fecha + " 00:00:00";
            String FechaFinal = fecha + " 23:59:59";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idtipo));
            statement.setTimestamp(3, Timestamp.valueOf(FechaInicial));
            statement.setTimestamp(4, Timestamp.valueOf(FechaFinal));
            statement.setInt(5, id_servicio);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTimestamp(2), result.getTimestamp(3),
                    result.getDouble(4), result.getString(5).charAt(0)};
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

    public void CambiarEstadoServicio(int k_idservicio, String fecha, char estado) {
        try {

            String sql = "UPDATE servicio SET f_final=?,i_estado=? WHERE k_idservicio =?;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setTimestamp(1, Timestamp.valueOf(fecha));
            statement.setString(2, String.valueOf(estado));
            statement.setInt(3, k_idservicio);
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
