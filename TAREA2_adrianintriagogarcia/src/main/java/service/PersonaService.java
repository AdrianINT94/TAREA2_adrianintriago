package service;

import java.util.List;
import dao.PersonaDAO;
import model.Persona;


public class PersonaService {
	
		private PersonaDAO dao = new PersonaDAO();
		
		public List<Persona> getAll(){ return dao.findAll();}
		
		public Persona getById(int id) {return dao.findById(id);}
		
		public void save (Persona p) {
			if (dao.findById(p.getId()) == null)
				dao.insert(p);
			else
				dao.update(p);
		}
		
		public void delete(int id) { dao.delete(id);}
}
