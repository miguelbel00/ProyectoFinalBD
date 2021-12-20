/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Mensajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.ConnectionBD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class MensajeroDAO {


    
    public void AdicionarMensajero(Mensajero mensajero) {
        try {

            String sql = "INSERT INTO mensajero(k_idtipo,k_idnumero,n_primernombre,"
                    + "n_segundonombre,n_primerapellido,n_segundoapellido,f_nacimiento,"
                    + "i_sexo,o_celular,o_telefono,o_correo,n_nacionalidad,i_seguridadsocial,i_mediotransporte,i_tipotransporte,i_tipociudad)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setString(1, String.valueOf(mensajero.getIdTipoM()));
            statement.setInt(2, mensajero.getIdNumeroM());
            statement.setString(3, mensajero.getPrimerNombre());
            statement.setString(4, mensajero.getSegundoNombre());
            statement.setString(5, mensajero.getPrimerApellido());
            statement.setString(6, mensajero.getSegundoApellido());
            statement.setDate(7, Date.valueOf(mensajero.getFechaNacimiento()));
            statement.setString(8, String.valueOf(mensajero.getSexo()));
            statement.setString(9, mensajero.getCelular());
            statement.setString(10, mensajero.getTelefono());
            statement.setString(11, mensajero.getCorreo());
            statement.setString(12, mensajero.getNacionalidad());
            statement.setString(13, String.valueOf(mensajero.getSeguridadSocial()));
            statement.setString(14, String.valueOf(mensajero.getMedioTransporte()));
            statement.setString(15, String.valueOf(mensajero.getTipoTransporte()));
            statement.setString(16, String.valueOf(mensajero.getTipoCiudad()));
            
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


    public void EliminarMensajero(int idNumero) {
        try {

            String sql = "DELETE FROM mensajero WHERE k_idnumero=? ";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.executeUpdate();
        } catch (SQLException e) {
            ConnectionBD.getInstance().rollback();
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
        }

    }


    public void ModificarMensajero(Mensajero mensajero) {
        try {

            String sql = "UPDATE mensajero SET n_primernombre=?,"
                    + "n_segundonombre=?,n_primerapellido=?,n_segundoapellido=?,f_nacimiento=?,"
                    + "i_sexo=?,o_celular=?,o_telefono=?,o_correo=?,n_nacionalidad=?,i_seguridadsocial=?,i_mediotransporte=?,i_tipotransporte=?,i_tipociudad=? WHERE k_idNumero=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setInt(15, mensajero.getIdNumeroM());
            statement.setString(1, mensajero.getPrimerNombre());
            statement.setString(2, mensajero.getSegundoNombre());
            statement.setString(3, mensajero.getPrimerApellido());
            statement.setString(4, mensajero.getSegundoApellido());
            statement.setDate(5, Date.valueOf(mensajero.getFechaNacimiento()));
            statement.setString(6, String.valueOf(mensajero.getSexo()));
            statement.setString(7, mensajero.getCelular());
            statement.setString(8, mensajero.getTelefono());
            statement.setString(9, mensajero.getCorreo());
            statement.setString(10, mensajero.getNacionalidad());
            statement.setString(11, String.valueOf(mensajero.getSeguridadSocial()));
            statement.setString(12, String.valueOf(mensajero.getMedioTransporte()));
            statement.setString(13, String.valueOf(mensajero.getTipoTransporte()));
            statement.setString(14, String.valueOf(mensajero.getTipoCiudad()));

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


    public Mensajero BuscarMensajero(int idNumero, char idTipo) {
        boolean existe = false;
        Mensajero mensajero = new Mensajero();
        
        try {
            

            
            String sql = "SELECT * FROM mensajero WHERE k_idnumero=? AND k_idtipo=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idTipo));

            ResultSet result = statement.executeQuery();

            
            while(result.next()){
                mensajero.setIdTipoM(result.getString(1).charAt(0));
                mensajero.setIdNumeroM(result.getInt(2));
                mensajero.setPrimerNombre(result.getString(3));
                mensajero.setSegundoNombre(result.getString(4));
                mensajero.setPrimerApellido(result.getString(5));
                mensajero.setSegundoApellido(result.getString(6));
                mensajero.setFechaNacimiento(result.getDate(7).toString());
                mensajero.setSexo(result.getString(8).charAt(0));
                mensajero.setCelular(result.getString(9));
                mensajero.setTelefono(result.getString(10));
                mensajero.setCorreo(result.getString(11));
                mensajero.setNacionalidad(result.getString(12));
                mensajero.setSeguridadSocial(result.getString(13).charAt(0));
                mensajero.setMedioTransporte(result.getString(14).charAt(0));
                mensajero.setTipoTransporte(result.getString(15).charAt(0));
                mensajero.setTipoCiudad(result.getString(16).charAt(0));
                existe = true;
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return mensajero;
                
            } else {
                System.out.println("no existe");
                return null;
            }
        }
    }
    public boolean comprobarMensajero(int numero, char id_tipo){
        boolean existe = false;
        Mensajero mensajero = new Mensajero();
        try {
            

            
            String sql = "SELECT * FROM mensajero WHERE k_idnumero=? AND k_idtipo=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, numero);
            statement.setString(2, String.valueOf(id_tipo));
            ResultSet result = statement.executeQuery();

            
            while(result.next()){
                existe = true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                JOptionPane.showMessageDialog(null, "Entrada exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "No existe el mensajero");
            }
        }
        return existe;
    }

    public ArrayList<Mensajero> ObtenerMensajeros(String horaInicial, char dia,char ciudad, char tipo){
        
        ArrayList<Mensajero> mensajeros= new ArrayList<>();
        boolean existe= false;
        
            try {
            
            String sql = "SELECT mensajero.k_idnumero,mensajero.k_idtipo,i_tipociudad FROM mensajero,horario  WHERE horario.k_idnumero=mensajero.k_idnumero AND horario.k_idtipo=mensajero.k_idtipo AND horario.v_horafinal>=? AND horario.i_dia=? AND mensajero.i_tipociudad=? AND mensajero.i_tipotransporte=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setTime(1, Time.valueOf(horaInicial));
            statement.setString(2, String.valueOf(dia));
            statement.setString(3, String.valueOf(ciudad));
            statement.setString(4, String.valueOf(tipo));
            ResultSet result = statement.executeQuery();

            
            while(result.next()){
                Mensajero m = new Mensajero();
                m.setIdNumeroM(result.getInt(1));
                m.setIdTipoM(result.getString(2).charAt(0));
                m.setTipoCiudad(result.getString(3).charAt(0));
                mensajeros.add(m);
                existe=true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return mensajeros;
                
            } else {
                return null;
            }
               
        }
        
            
            
            
            
    }
    public DefaultTableModel TablaMensajero() {
        String titulos[] = {"Tipo de Documento", "Numero de Documento", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido","Fecha Nacimiento", "Sexo","Celular","Telefono","Correo","Nacionalidad","Seguridad Social","medio transporte","tipotransporte","ciudad"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM mensajero;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Object[] data = {result.getString(1).charAt(0), result.getInt(2), result.getString(3),result.getString(4),result.getString(5),result.getString(6),
                    result.getDate(7).toString(),result.getString(8).charAt(0),result.getString(9),result.getString(10),result.getString(11),result.getString(12),result.getString(13).charAt(0),result.getString(14).charAt(0),result.getString(15).charAt(0),result.getString(16).charAt(0)};
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
    public DefaultTableModel TablaNombre() {
        String titulos[] = {"Tipo de Documento", "Numero de Documento"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM mensajero;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Object[] data = {result.getString(1).charAt(0), result.getInt(2)};
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
