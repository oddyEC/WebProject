package modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
@Entity
@NamedQuery(name = "listarTurnos", query = "SELECT t FROM Turno t WHERE t.docente = :docente")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTurno;
	@Column(name = "dia")
	private String dia;
	@Column(name = "horaInicio")
	private String horaInicio;
	
	@ManyToOne
	@JoinColumn(name="cedula")
	private Docente docente;
	

	public Turno() {
		
	}

	public Turno(String dia, String horaInicio, Docente docente) {
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.docente = docente;
	}

	public Integer getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((docente == null) ? 0 : docente.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + idTurno;
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
		Turno other = (Turno) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (docente == null) {
			if (other.docente != null)
				return false;
		} else if (!docente.equals(other.docente))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (idTurno != other.idTurno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", dia=" + dia + ", horaInicio=" + horaInicio + ", docente=" + docente
				+ "]";
	}
	
	

}
