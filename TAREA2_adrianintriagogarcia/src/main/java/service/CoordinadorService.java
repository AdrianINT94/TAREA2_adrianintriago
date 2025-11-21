package service;

import java.util.List;

import dao.CoordinadorDAO;
import model.Coordinador;

public class CoordinadorService {
	
	private CoordinadorDAO dao= new CoordinadorDAO();
	
	public Coordinador getById(int id) {
		return dao.findById(id);
	}
	
	public List<Coordinador> getAll() {
		return dao.findAll();
		
	}
	
	public void save (Coordinador c) {
		if(dao.findById(c.getId())==null)
			dao.insert(c);
		else
			dao.update(c);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}
}
