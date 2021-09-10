package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.DepartamentoDAO;
import modelo.entidad.Departamento;

public class JPADepartamento extends JPAGenericDAO<Departamento, Integer> implements DepartamentoDAO {
	public JPADepartamento() {
		super(Departamento.class);
		
	}

	@Override
	public List<Departamento> listar() {
		Query query= em.createNamedQuery("listarDepartamentos",Departamento.class);
		List<Departamento> departamentos =(List<Departamento>) query.getResultList();
		if (departamentos.size() != 0) {
			return departamentos;
		}
		return null;
	}

	@Override
	public Departamento leerByNombre(String name) {
		Query query = em.createNamedQuery("buscarDpto",Departamento.class);
		query.setParameter("name", name);
		Departamento buscado;
		try {
			buscado= (Departamento) query.getSingleResult();
		}
		catch (Exception e) {
			buscado = null;
		}
		if (buscado != null) {
			return buscado;
		}
		return null;
	}
	
}
