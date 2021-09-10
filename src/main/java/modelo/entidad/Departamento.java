package modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.annotations.CascadeOnDelete;


@Entity
@NamedQueries({
@NamedQuery(name = "listarDepartamentos",query = "SELECT d FROM Departamento d") , 
@NamedQuery(name = "buscarDpto", query = "SELECT d FROM Departamento d WHERE d.nombre LIKE :name")})

public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private List<Docente> docentes;
	
	public Departamento() {
	}

	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Departamento) {
			Departamento dpto = (Departamento) obj;
			return dpto.getId().equals(this.id) && dpto.getNombre().equals(this.nombre);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
