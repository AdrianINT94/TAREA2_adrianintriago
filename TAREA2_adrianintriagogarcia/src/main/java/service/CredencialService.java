package service;

import java.util.List;

import dao.CredencialDAO;
import model.Credencial;

public class CredencialService {
	
	private CredencialDAO dao = new CredencialDAO();
	
	public Credencial getByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	public List<Credencial> getAll(){
		return dao.findAll();
	}
	public void save(Credencial c) {
		Credencial existingCredencial =dao.findByUsername(c.getUsername());
		if(existingCredencial == null) {
			dao.insert(c);
		}else {
			dao.update(c);
		}
	}
	public void delete(String username) {
		dao.delete(username);
	}
	
	
	
	
}
