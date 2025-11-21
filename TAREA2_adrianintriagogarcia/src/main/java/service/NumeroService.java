package service;

import java.util.List;

import dao.NumeroDAO;
import model.Numero;

public class NumeroService {

		private NumeroDAO dao= new NumeroDAO();
		
		public Numero getById(int id) {
			return dao.findById(id);
		}
		public List<Numero> getAll(){
			return dao.findAll();
		}
		
		public void save(Numero n ) {
			if(dao.findById(n.getId())==null)
				dao.insert(n);
			else
				dao.update(n);
		}
		public void delete(int id) {
			dao.delete(id);
		}
}
