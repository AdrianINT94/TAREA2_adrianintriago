package service;

import java.util.List;

import dao.EspectaculoDAO;
import model.Espectaculo;

public class EspectaculoService {

		private EspectaculoDAO dao =new EspectaculoDAO();
		
		public Espectaculo getById(int id) {
			return dao.findById(id);
		}
		public List<Espectaculo> getAll(){
			return dao.findAll();
		}
		
		
		public void save (Espectaculo e) {
			if(dao.findById(e.getId())==null)
				dao.insert(e);
			else
				dao.update(e);
		}
		public void delete(int id) {
			dao.delete(id);
		}
}
