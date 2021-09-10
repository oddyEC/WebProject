package modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.annotations.CascadeOnDelete;



@Entity
@NamedQuery(name = "listarDocentes",query = "SELECT dc FROM Docente dc")
public class Docente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cedula")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String cedula;
	
	@Column(name = "constrasena")
	private String contraseña;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private List<Turno> turnos;
	
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private List<Tutoria> tutorias;
	
	public Docente() {}

	public Docente(String cedula, String nombre, String apellido) {
		this.cedula = cedula;
		this.contraseña = "docente123";
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Docente(String cedula, String contraseña, String nombre, String apellido) {
		this.cedula = cedula;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Docente(String cedula, String nombre, String apellido, Departamento dpto){
		this.cedula = cedula;
		this.contraseña = "docente123";
		this.nombre = nombre;
		this.apellido = apellido;
		this.departamento = dpto;
	}
	


	public Docente(String cedula, String contraseña, String nombre, String apellido, Departamento departamento) {
		
		this.cedula = cedula;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.apellido = apellido;
		this.departamento = departamento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public List<Tutoria> getTutorias() {
		return tutorias;
	}

	public void setTutorias(List<Tutoria> tutorias) {
		this.tutorias = tutorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((contraseña == null) ? 0 : contraseña.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Docente) {
			Docente docente = (Docente) obj;
			return docente.getCedula().equals(this.cedula);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Docente [cedula=" + cedula + ", nombre=" + nombre + " apellido="
				+ apellido + "]";
	}
}
