package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Espectaculo {
	
	private int id;
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Coordinador coordinador;
	private List<Numero> numeros;
	
	public Espectaculo() {
		this.numeros = new ArrayList<>();
	}
	public Espectaculo(int id,String nombre,
			LocalDate fechaInicio, LocalDate fechaFin,
			Coordinador coordinador,List<Numero>numeros) {
		this.id=id;
		this.nombre=nombre;
		this.fechaInicio=fechaInicio;
		this.fechaFin=fechaFin;
		this.coordinador=coordinador;
		this.numeros=numeros;
		
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
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Coordinador getCoordinador() {
		return coordinador;
	}
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	public List<Numero> getNumeros() {
		return numeros;
	}
	public void setNumeros(List<Numero> numeros) {
		this.numeros = numeros;
	}
	
	
}
