package dataConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetoUsuario.ObjetoCliente;


public class DataCliente {
private Connection con;
	
	public DataCliente(){
		performConnection();
	}

	private void performConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.print("Hay coneccion");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error en la coneccion"); 
		}
		
	}
	/**
	 * Vamos a obtener la conexion siempre que utilicemos a con
	 * @throws SQLException 
	 *
	 */
	
	private Connection getConnection() throws SQLException{
		String host="localhost:3306";
		String user="root";
		String pass="12345678";
		String dtbs="solucionalosas";
		String newConectionURL="jdbc:mysql://"+host+"/"+dtbs;
		return DriverManager.getConnection(newConectionURL,user,pass);
	}
	
	/*
	 * metodo para cerrar la conexion 
	 */
	public void closeConexion(){
		try {
			con.close();
			System.out.print("Se a cerrado coneccion");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error al cerrar la coneccion");
		}
		
	}
	//este es un arreglo para consultar los datos de la tabla cliente de DB
			public ArrayList<ObjetoCliente> retornaCliente()throws SQLException{
				con  = getConnection();
				ArrayList<ObjetoCliente> ls = new ArrayList<ObjetoCliente>();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					ObjetoCliente a = new ObjetoCliente(rs.getInt("Idcliente") ,rs.getString("Idtipodocumento"),rs.getInt("Identificacion") ,rs.getString("Primernombre") ,rs.getString("Segundonombre") ,rs.getString("Primerapellido") ,rs.getString("Segundoapellido") ,rs.getString("Fechanacimiento") , rs.getString("Direccion"), rs.getInt("Telefono"));
					ls.add(a);
				}
				rs.close();
				return ls;
			}
			
			//esta es la instruccion para insertar los datos de la tabla Cliente en la DB
			public void insertarCliente(String idTipodocumento,int identificacion,String primernombre,String segundonombre,String primerapellido,String segundoapellido,String fechanacimiento,String direccion,int telefono) throws SQLException{
				con  = getConnection();
				
				//tener pendiente que los campos sean los mismos de la DB
				String selection = "INSERT INTO cliente(Idtipodocumento,Identificacion,Primernombre,Segundonombre,Primerapellido,Segundoapellido,Fechanacimiento,Direccion,Telefono)"+"VALUES(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(selection);
				//aqui los campos son los del objeto
				//ps.setInt(1, idcliente);
				ps.setString(1, idTipodocumento);
				ps.setInt(2, identificacion);
				ps.setString(3, primernombre);
				ps.setString(4, segundonombre);
				ps.setString(5, primerapellido);
				ps.setString(6, segundoapellido);
				ps.setString(7, fechanacimiento);
				ps.setString(8, direccion);
				ps.setInt(9, telefono);
				ps.executeUpdate();
				
				
				con.close();
				
			}
			
			//esta es la intruccino para editar los datos de la tabla Cliente DB
			public void editarCliente(int idcliente,String idTipodocumento,int identificacion,String primernombre,String segundonombre,String primerapellido,String segundoapellido,String fechanacimiento,String direccion,int telefono)throws SQLException{
				con  = getConnection();
				String selection = "UPDATE cliente SET Idtipodocumento =?,Identificacion =?,Primernombre =?,Segundonombre =?,Primerapellido =?,Segundoapellido =?,Fechanacimiento =?,Direccion =?,Telefono =? WHERE Idcliente =?";
				PreparedStatement ps = con.prepareStatement(selection);
				
				ps.setString(1, idTipodocumento);
				ps.setInt(2, identificacion);
				ps.setString(3, primernombre);
				ps.setString(4, segundonombre);
				ps.setString(5, primerapellido);
				ps.setString(6, segundoapellido);
				ps.setString(7, fechanacimiento);
				ps.setString(8, direccion);
				ps.setInt(9, telefono);
				ps.setInt(10, idcliente);
				ps.executeUpdate();
				
				con.close();
			}
			
			//lesta es la instruccion para eliminar Cliente
			public void eliminarCliente(int idcliente) throws SQLException{
				con  = getConnection();
				String selection = "DELETE FROM cliente WHERE Idcliente = ?";
				PreparedStatement ps = con.prepareStatement(selection);
				ps.setInt(1, idcliente);
				ps.executeUpdate();
				con.close();
			}
	
	
}
