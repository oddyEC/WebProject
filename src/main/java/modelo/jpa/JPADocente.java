package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.UsuarioDAO;
import modelo.entidad.Docente;



public class JPADocente extends JPAGenericDAO<Docente, String> implements UsuarioDAO<Docente, String> {
	public JPADocente() {
		super(Docente.class);
		
	}
	@Override
	public Docente autorizar(String cedula, String contraseña) {
        Docente docenteEntidad = this.leer(cedula);
		if(docenteEntidad != null){
			if(docenteEntidad.getContraseña().equals(contraseña)){
				return docenteEntidad;
			}
		}
        return null;

	}

	@Override
	public List<Docente> listar() {
		Query query= em.createNamedQuery("listarDocentes",Docente.class);
		List<Docente> docentes =(List<Docente>) query.getResultList();
		if (docentes.size() != 0) {
			return docentes;
		}
		return null;
	}
	

	
	
}
