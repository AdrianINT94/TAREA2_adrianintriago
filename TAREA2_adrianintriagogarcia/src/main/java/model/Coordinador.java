package model;

import java.time.LocalDate;

public class Coordinador extends Persona{
	
	private  boolean senior;
	private LocalDate fechaSenior;
	
	public Coordinador() {}
	
	public Coordinador(int id,String nombre,String email,String nacionalidad,
			boolean senior,LocalDate fechaSenior) {
		super(id,nombre,email,nacionalidad);
		this.senior=senior;
		this.fechaSenior=fechaSenior;
	}
	

	public boolean isSenior() {
		return senior;
	}

	public void setSenior(boolean senior) {
		this.senior = senior;
	}

	public LocalDate getFechaSenior() {
		return fechaSenior;
	}

	public void setFechaSenior(LocalDate fechaSenior) {
		this.fechaSenior = fechaSenior;
	}
	
	}
