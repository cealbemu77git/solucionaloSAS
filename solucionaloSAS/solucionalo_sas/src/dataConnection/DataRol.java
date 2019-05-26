package dataConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetoUsuario.ObjetoRol;
import objetoUsuario.ObjetoTipoarreglo;

public class DataRol {

private Connection con;
	
	public DataRol(){
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
	
	
	//este es un arreglo para consultar los datos de la tabla rol de DB
		public ArrayList<ObjetoRol> retornaRol()throws SQLException{
			con  = getConnection();
			ArrayList<ObjetoRol> ls = new ArrayList<ObjetoRol>();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM rol");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ObjetoRol a = new ObjetoRol(rs.getInt("idrol") ,rs.getString("codigo"),rs.getString("descripcion"));
				ls.add(a);
			}
			rs.close();
			return ls;
		}
		
		//lesta es la instruccion para eliminar 
		public void eliminarRol(int idrol) throws SQLException{
			con  = getConnection();
			String selection = "DELETE FROM rol WHERE idrol = ?";
			PreparedStatement ps = con.prepareStatement(selection);
			ps.setInt(1, idrol);
			ps.executeUpdate();
			con.close();
		}
		//esta es la instruccion para insertar los datos de la tabla usuario en la DB
		public void insertarRol(String codigo,String descripcion) throws SQLException{
			con  = getConnection();
			//tener pendiente que los campos sean los mismos de la DB
			String selection = "INSERT INTO rol(codigo,descripcion)"+"VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(selection);
			//aqui los campos son los del objeto
			ps.setString(1, codigo);
			ps.setString(2, descripcion);
			ps.executeUpdate();
			
			con.close();
			
		}
		
		//esta es la intruccino para editar los datos de la tabla usuario DB
		public void editarRol(int idrol,String codigo,String descripcion)throws SQLException{
			con  = getConnection();
			String selection = "UPDATE rol SET codigo =?,descripcion =? WHERE idrol =?";
			PreparedStatement ps = con.prepareStatement(selection);
			ps.setString(1, codigo);
			ps.setString(2, descripcion);
			ps.setInt(3, idrol);
			ps.executeUpdate();
			
			con.close();
		}
		
}
