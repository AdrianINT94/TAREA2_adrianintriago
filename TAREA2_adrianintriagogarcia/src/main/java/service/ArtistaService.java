package service;

import java.util.List;

import dao.ArtistaDAO;
import model.Artista;

public class ArtistaService {

		private ArtistaDAO dao = new ArtistaDAO();
		
		public Artista getById(int id) {
			return dao.findById(id);
		}
		
		public List<Artista> getAll(){
			return dao.findAll();
		}
		
		public void save (Artista a) {
			if(dao.findById(a.getId()) == null)
				dao.insert(a);
			else
				dao.update(a);
		}
		
		public void delete (int id) {
			dao.delete(id);
		}
}
