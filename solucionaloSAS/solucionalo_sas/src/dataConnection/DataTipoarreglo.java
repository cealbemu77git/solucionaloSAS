package dataConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetoUsuario.ObjetoTipoarreglo;

public class DataTipoarreglo {

private Connection con;
	
	public DataTipoarreglo(){
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
	
	
	//este es un arreglo para consultar los datos de la tabla reparaciones de DB
		public ArrayList<ObjetoTipoarreglo> retornaTipoarreglo()throws SQLException{
			con  = getConnection();
			ArrayList<ObjetoTipoarreglo> ls = new ArrayList<ObjetoTipoarreglo>();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM tipoarreglo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ObjetoTipoarreglo a = new ObjetoTipoarreglo(rs.getInt("idtipoarreglo") ,rs.getString("codigo"),rs.getString("descripcion"));
				ls.add(a);
			}
			rs.close();
			return ls;
		}
		//lesta es la instruccion para eliminar 
		public void eliminarTipoarreglo(int idtipoarreglo) throws SQLException{
			con  = getConnection();
			String selection = "DELETE FROM tipoarreglo WHERE idtipoarreglo = ?";
			PreparedStatement ps = con.prepareStatement(selection);
			ps.setInt(1, idtipoarreglo);
			ps.executeUpdate();
			con.close();
		}
		//esta es la instruccion para insertar los datos de la tabla usuario en la DB
		public void insertarTipoarreglo(String codigo,String descripcion) throws SQLException{
			con  = getConnection();
			//tener pendiente que los campos sean los mismos de la DB
			String selection = "INSERT INTO tipoarreglo(codigo,descripcion)"+"VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(selection);
			//aqui los campos son los del objeto
			ps.setString(1, codigo);
			ps.setString(2, descripcion);
			ps.executeUpdate();
			
			con.close();
			
		}
		
		//esta es la intruccino para editar los datos de la tabla usuario DB
		public void editarTipoarreglo(int idtipoarreglo,String codigo,String descripcion)throws SQLException{
			con  = getConnection();
			String selection = "UPDATE tipoarreglo SET codigo =?,descripcion =? WHERE idtipoarreglo =?";
			PreparedStatement ps = con.prepareStatement(selection);
			ps.setString(1, codigo);
			ps.setString(2, descripcion);
			ps.setInt(3, idtipoarreglo);
			ps.executeUpdate();
			
			con.close();
		}
}
