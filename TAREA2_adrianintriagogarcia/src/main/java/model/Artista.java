package model;

import java.util.ArrayList;
import java.util.List;

public class Artista extends Persona{
	
	private String apodo;
	private List<Especialidad> especialidades;
	
	public Artista() {
		this.especialidades=new ArrayList<>();
	}
	
	public Artista (int id, String nombre, String email, String nacionalidad,String apodo,List<Especialidad> especialidades) {
		super(id,nombre,email,nacionalidad);
		this.apodo=apodo;
		this.especialidades = especialidades;
		
		
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	

}
