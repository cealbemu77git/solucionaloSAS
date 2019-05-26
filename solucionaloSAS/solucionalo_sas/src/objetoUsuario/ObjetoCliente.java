package objetoUsuario;

import java.sql.Date;

public class ObjetoCliente {
private int idcliente;
private String idTipodocumento;
private int identificacion;
private String primernombre;
private String segundonombre;
private String primerapellido;
private String segundoapellido;
private String fechanacimiento;
private String direccion;
private int telefono;
public ObjetoCliente(int idcliente, String idTipodocumento, int identificacion,
		String primernombre, String segundonombre, String primerapellido,
		String segundoapellido, String fechanacimiento, String direccion,
		int telefono) {
	super();
	this.idcliente = idcliente;
	this.idTipodocumento = idTipodocumento;
	this.identificacion = identificacion;
	this.primernombre = primernombre;
	this.segundonombre = segundonombre;
	this.primerapellido = primerapellido;
	this.segundoapellido = segundoapellido;
	this.fechanacimiento = fechanacimiento;
	this.direccion = direccion;
	this.telefono = telefono;
}
public int getIdcliente() {
	return idcliente;
}
public void setIdcliente(int idcliente) {
	this.idcliente = idcliente;
}
public String getIdTipodocumento() {
	return idTipodocumento;
}
public void setIdTipodocumento(String idTipodocumento) {
	this.idTipodocumento = idTipodocumento;
}
public int getIdentificacion() {
	return identificacion;
}
public void setIdentificacion(int identificacion) {
	this.identificacion = identificacion;
}
public String getPrimernombre() {
	return primernombre;
}
public void setPrimernombre(String primernombre) {
	this.primernombre = primernombre;
}
public String getSegundonombre() {
	return segundonombre;
}
public void setSegundonombre(String segundonombre) {
	this.segundonombre = segundonombre;
}
public String getPrimerapellido() {
	return primerapellido;
}
public void setPrimerapellido(String primerapellido) {
	this.primerapellido = primerapellido;
}
public String getSegundoapellido() {
	return segundoapellido;
}
public void setSegundoapellido(String segundoapellido) {
	this.segundoapellido = segundoapellido;
}
public String getFechanacimiento() {
	return fechanacimiento;
}
public void setFechanacimiento(String fechanacimiento) {
	this.fechanacimiento = fechanacimiento;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public int getTelefono() {
	return telefono;
}
public void setTelefono(int telefono) {
	this.telefono = telefono;
}



}
