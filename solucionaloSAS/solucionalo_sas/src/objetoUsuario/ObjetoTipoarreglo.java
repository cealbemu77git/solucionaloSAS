package objetoUsuario;

public class ObjetoTipoarreglo {

	int idtipoarreglo;
	String codigo;
	String descripcion;
	public ObjetoTipoarreglo(int idtipoarreglo, String codigo,
			String descripcion) {
		super();
		this.idtipoarreglo = idtipoarreglo;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public int getIdtipoarreglo() {
		return idtipoarreglo;
	}
	public void setIdtipoarreglo(int idtipoarreglo) {
		this.idtipoarreglo = idtipoarreglo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString(){
		return this.descripcion;
	}
	
}
