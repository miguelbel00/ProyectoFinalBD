/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.Cliente;
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
public class ClienteDAO {


    public void AdicionarCliente(Cliente cliente) {
        try {

            String sql = "INSERT INTO cliente(k_idtipoc,k_idnumeroc,n_primernombre,"
                    + "n_segundonombre,n_primerapellido,n_segundoapellido,o_direccion,"
                    + "i_sexo,o_celular,o_telefono,o_correo)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setString(1, String.valueOf(cliente.getIdTipoC()));
            statement.setInt(2, cliente.getIdNumeroC());
            statement.setString(3, cliente.getPrimerNombre());
            statement.setString(4, cliente.getSegundoNombre());
            statement.setString(5, cliente.getPrimerApellido());
            statement.setString(6, cliente.getSegundoApellido());
            statement.setString(7, cliente.getDireccion());
            statement.setString(8, String.valueOf(cliente.getSexo()));
            statement.setString(9, cliente.getCelular());
            statement.setString(10, cliente.getTelefono());
            statement.setString(11, cliente.getCorreo());

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


    public void EliminarCliente(int idNumeroC) {
        try {

            String sql = "DELETE FROM cliente WHERE k_idnumeroc=? ";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idNumeroC);
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


    public DefaultTableModel TablaCliente() {
        String titulos[] = {"Tipo de Documento", "Numero de Documento", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Direccion", "Sexo", "Correo", "Celular", "Telefono"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "SELECT * FROM cliente;";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Object[] data = {result.getString(1).charAt(0), result.getInt(2), result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),
                    result.getString(8).charAt(0),result.getString(9),result.getString(10),result.getString(11)};
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

    public void ModificarCliente(Cliente cliente) {

        try {


            String sql = "UPDATE cliente SET n_primernombre=?,"
                    + "n_segundonombre=?,n_primerapellido=?,n_segundoapellido=?,o_direccion=?,"
                    + "i_sexo=?,o_celular=?,o_telefono=?,o_correo=? WHERE k_idnumeroc=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(10, cliente.getIdNumeroC());
            statement.setString(1, cliente.getPrimerNombre());
            statement.setString(2, cliente.getSegundoNombre());
            statement.setString(3, cliente.getPrimerApellido());
            statement.setString(4, cliente.getSegundoApellido());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, String.valueOf(cliente.getSexo()));
            statement.setString(7, cliente.getCelular());
            statement.setString(8, cliente.getTelefono());
            statement.setString(9, cliente.getCorreo());

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


    public Cliente BuscarCliente(int idNumero, char idTipo) {

        boolean existe = false;
        Cliente cliente = new Cliente();

        try {


            String sql = "SELECT * FROM cliente WHERE k_idnumeroc=? AND k_idtipoc=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idNumero);
            statement.setString(2, String.valueOf(idTipo));

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                cliente.setIdTipoC(result.getString(1).charAt(0));
                cliente.setIdNumeroC(result.getInt(2));
                cliente.setPrimerNombre(result.getString(3));
                cliente.setSegundoNombre(result.getString(4));
                cliente.setPrimerApellido(result.getString(5));
                cliente.setSegundoApellido(result.getString(6));
                cliente.setDireccion(result.getString(7));
                cliente.setSexo(result.getString(8).charAt(0));
                cliente.setCorreo(result.getString(9));
                cliente.setCelular(result.getString(10));
                cliente.setTelefono(result.getString(11));
                existe = true;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "\nError :" + e.getMessage());
        } finally {
            ConnectionBD.getInstance().liberarConexion();
            if (existe) {
                return cliente;
            } else {
                return null;

            }
        }
    }

    public boolean comprobarCliente(int numero, char id_tipo) {
        boolean existe = false;
        Cliente cliente = new Cliente();
        try {


            String sql = "SELECT * FROM cliente WHERE k_idnumeroc=? AND k_idtipoc=?";
            Connection conexion = ConnectionBD.getInstance().tomarConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, numero);
            statement.setString(2, String.valueOf(id_tipo));
            ResultSet result = statement.executeQuery();


            while (result.next()) {
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
                JOptionPane.showMessageDialog(null, "No existe el cliente");
            }
        }
        return existe;
    }
}
