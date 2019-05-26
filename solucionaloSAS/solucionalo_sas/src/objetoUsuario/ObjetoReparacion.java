package objetoUsuario;

public class ObjetoReparacion {

	int idreparaciones;
	int idtipoarreglo;
	int valor;
	String garantia;
	String observacion;
	public ObjetoReparacion(int idreparaciones, int idtipoarreglo,
			int valor, String garantia, String observacion) {
		super();
		this.idreparaciones = idreparaciones;
		this.idtipoarreglo = idtipoarreglo;
		this.valor = valor;
		this.garantia = garantia;
		this.observacion = observacion;
	}
	public int getIdreparaciones() {
		return idreparaciones;
	}
	public void setIdreparaciones(int idreparaciones) {
		this.idreparaciones = idreparaciones;
	}
	public int getIdtipoarreglo() {
		return idtipoarreglo;
	}
	public void setIdtipoarreglo(int idtipoarreglo) {
		this.idtipoarreglo = idtipoarreglo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	@Override
	public String toString(){
		return this.observacion;
	}
	
}
