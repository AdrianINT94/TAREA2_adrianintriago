package model;

public class Persona {
	
	protected int id;
	protected String nombre;
	protected String email;
	protected String nacionalidad;
	
	public Persona() {}
	
	public Persona (int id,String nombre,String email,String nacionalidad) {
		this.id=id;
		this.nombre=nombre;
		this.email=email;
		this.nacionalidad=nacionalidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
		
}
