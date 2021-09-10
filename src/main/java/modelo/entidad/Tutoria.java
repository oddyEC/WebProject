package modelo.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@NamedQueries({
@NamedQuery(name = "obtenerTutoriasDocente",query = "SELECT t FROM Tutoria t WHERE t.docente = :docente"),
@NamedQuery(name = "obtenerTutoriasEstudiante",query = "SELECT t FROM Tutoria t WHERE t.estudiante = :estudiante")})
public class Tutoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTutoria;
	@Column(name = "horaInicio")
	private String horaInicio;
	
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="docente_id")
	private Docente docente;
	
	@ManyToOne
	@JoinColumn(name="estudiante_id")
	private Estudiante estudiante;
	
	
	public Tutoria() {
		
	}
	public Tutoria(String horaInicio, Date fecha, Docente docente, Estudiante estudiante) {
		this.horaInicio = horaInicio;
		this.fecha = fecha;
		this.docente = docente;
		this.estudiante = estudiante;
	}

	public Integer getIdTutoria() {
		return idTutoria;
	}

	public void setIdTutoria(int idTutoria) {
		this.idTutoria = idTutoria;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docente == null) ? 0 : docente.hashCode());
		result = prime * result + ((estudiante == null) ? 0 : estudiante.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + idTutoria;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutoria other = (Tutoria) obj;
		if (docente == null) {
			if (other.docente != null)
				return false;
		} else if (!docente.equals(other.docente))
			return false;
		if (estudiante == null) {
			if (other.estudiante != null)
				return false;
		} else if (!estudiante.equals(other.estudiante))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (idTutoria != other.idTutoria)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tutoria [idTutoria=" + idTutoria + ", horaInicio=" + horaInicio + ", fecha=" + fecha + ", docente="
				+ docente + ", estudiante=" + estudiante + "]";
	}
	
	

}
