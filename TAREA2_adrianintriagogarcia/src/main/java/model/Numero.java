package model;

import java.util.List;

public class Numero {
	private int id;
	private String nombre;
	private String duracion; 
	
	
	
	public Numero(int id, String nombre, String duracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		
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


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	
	}
	
	

