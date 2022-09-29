/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Horario;
import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class HorarioDAO{
    

    private Connection conn = null;


    public void AdicionarHorario(Horario horario) {
        try {
           
            String sql = "INSERT INTO horario(v_horainicial,v_horafinal,"
                    + "i_dia,k_idtipo,k_idnumero)"
                    + "VALUES(?,?,?,?,?);";
            
            
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            
            statement.setTime(1,Time.valueOf(horario.getHoraInicial()));
            statement.setTime(2, Time.valueOf(horario.getHoraFinal()));
            statement.setString(3, String.valueOf(horario.getDia()));
            statement.setString(4, String.valueOf(horario.getIdTipo()));
            statement.setInt(5, horario.getIdNumero());
           
                  

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


    public void EliminarHorario(int idNumero) {
        try {

            String sql = "DELETE FROM horario WHERE k_idhorario=? ";
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
    public DefaultTableModel TablaHorario() {
        String titulos[] = {"Horario", "Hora Inicial", "Hora Final","Dia","tipoMensajero","NumeroMensajero"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM horario;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Object[] data = {result.getInt(1), result.getTime(2).toString(), result.getTime(3).toString(), result.getString(4).charAt(0), 
                    result.getString(5).charAt(0), result.getInt(6)};
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

    public void ModificarHorario(Horario horario) {

        try {


            String sql = "UPDATE horario SET v_horainicial=?,v_horafinal=?,"
                    + "i_dia=?,k_idtipo=?,k_idnumero=? WHERE k_idhorario=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, horario.getIdHorario());
            statement.setTime(2,Time.valueOf(horario.getHoraInicial()));
            statement.setTime(3, Time.valueOf(horario.getHoraFinal()));
            statement.setString(4, String.valueOf(horario.getDia()));
            statement.setString(5, String.valueOf(horario.getIdTipo()));
            statement.setInt(6, horario.getIdNumero());
            
            

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

    
    public Horario BuscarHorario(int idNumero){
        
        boolean existe = false;
        Horario horario = new Horario();

        try {

            
            String sql = "SELECT * FROM horario WHERE k_idhorario=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
               horario.setIdHorario(idNumero);
               horario.setHoraInicial(result.getTime(2).toString());
               horario.setHoraFinal(result.getTime(3).toString());
               horario.setDia(result.getString(4).charAt(0));
               horario.setIdTipo(result.getString(5).charAt(0));
               horario.setIdNumero(result.getInt(6));
                
                existe = true;
                
                
                
            }
                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return horario;
                
            } else {
                return null;
            }
            
        }
  
        
    }
    public DefaultTableModel HistoricoHorarioM(int idNumero, char idTipo){
        String titulos[] = {"Id Horario","Fecha Inicio", "Fecha Final", "Dia"};
         DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
         try{
          String sql = "SELECT k_idhorario, v_horainicial,v_horafinal, i_dia FROM horario WHERE k_idtipo=? AND k_idnumero=?;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, String.valueOf(idTipo));            
            statement.setInt(2, idNumero);

            ResultSet result = statement.executeQuery();
            
           while(result.next()){

                Object[] data = {result.getInt(1),result.getTime(2),result.getTime(3),result.getString(4).charAt(0)};
                tablaModelo.addRow(data);            }           
         }catch(SQLException e){
             ConnectionBD.getInstance().rollback();
             JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
         } finally{
             ConnectionBD.getInstance().liberarConexion();
             return tablaModelo;
        }
 
        
        
    }
    
    
 

}
