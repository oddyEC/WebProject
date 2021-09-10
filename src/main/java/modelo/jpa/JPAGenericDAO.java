package modelo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.dao.GenericDAO;

public abstract class JPAGenericDAO <T,ID> implements GenericDAO<T, ID> {
	protected EntityManager em;
	private Class<T> persistenceClass;
	
	public JPAGenericDAO(Class<T> persistenceCls) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoTutorias");
		em = emf.createEntityManager();
		this.persistenceClass=persistenceCls;
	}

	@Override
	public void crear(T entity) {
		em.getTransaction().begin();
		try {
			em.persist(entity);		
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en Creación JPAGenericDAO");
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

	}

	@Override
	public T leer(ID id) {
		return em.find(persistenceClass, id);
	}

	@Override
	public void actualizar(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);		
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en Actualización JPAGenericDAO");
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}		
	}

	@Override
	public void eliminar(T entity) {
		
		em.getTransaction().begin();
		try {
			T aeliminar = em.merge(entity);
			em.remove(aeliminar);		
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en Eliminación JPAGenericDAO");
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}		
		
	}

	@Override
	public void eliminarPorId(ID id) {
		T entity=this.leer(id);
		if(entity != null) {
			this.eliminar(entity);
		}
		
	}

	@Override
	public abstract List<T> listar();
	
	

}
