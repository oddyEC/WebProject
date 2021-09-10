package controlador.estudiante;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.dao.DAOFactory;
import modelo.entidad.Departamento;
import modelo.entidad.Docente;
import modelo.entidad.Estudiante;
import modelo.entidad.Turno;
import modelo.entidad.Tutoria;
import modelo.jpa.JPAFactory;import modelo.jpa.JPAGenericDAO;


@WebServlet("/solicitarTutoriaController")
public class solicitarTutoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public solicitarTutoriaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate fechaHoy = java.time.LocalDate.now(); 
		String dateHoy= fechaHoy.toString(); 
		request.setAttribute("dateHoy", dateHoy);
		listarDepartamentos(request, response);
		String busqueda = request.getParameter("id");
		String buscarDocente = request.getParameter("cedula");
		if(busqueda!=null) {
			response.getWriter().print(listarDocentes(busqueda));
			response.getWriter().close();
		}else if(buscarDocente != null){
			response.getWriter().print(listarTurnos(buscarDocente));
			response.getWriter().close();
		}
		else {
		getServletContext().getRequestDispatcher("/solicitarTutoria.jsp").forward(request, response);}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      procesarSolicitud(request, response);
	}
	
	private void listarDepartamentos(HttpServletRequest req, HttpServletResponse resp){
		DAOFactory fabrica = new JPAFactory();
		List<Departamento> dptos = fabrica.crearDepartamentoDAO().listar();
		req.setAttribute("listaDptos", dptos);
	}

	private String listarTurnos(String cedula){
		DAOFactory fabrica = new JPAFactory();
		Docente d = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		List<Turno> turnosCompletos = fabrica.crearTurnoDAO().listarAsociados(d);
		List<Turno> turnosAParsear = new ArrayList<Turno>();
		GsonBuilder parseador = new GsonBuilder();
		Gson gson = parseador.create();
		if(turnosCompletos ==null) {
			return gson.toJson(turnosAParsear);
		}
		for(Turno t:turnosCompletos) {
			t.setDocente(null);
			turnosAParsear.add(t);
		}
		
		String json = gson.toJson(turnosAParsear);
		return json;
	}
	
	private String listarDocentes(String id){
		DAOFactory fabrica = new JPAFactory();
		Departamento dpto = fabrica.crearDepartamentoDAO().leer(Integer.parseInt(id));
		List<Docente> d1 = (List<Docente>)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).listar();
		List<Docente> listFinal = new ArrayList<Docente>();
		for (Docente d: d1) {
			if(d.getDepartamento().equals(dpto)) {
				d.setTurnos(null);
				d.setTutorias(null);
				d.setDepartamento(null);
				d.setContraseña(null);
				listFinal.add(d);
			}
		}
		
		GsonBuilder parseador = new GsonBuilder();
		Gson gson = parseador.create();
		String json = gson.toJson(listFinal);
		return json;
		
	}
	
	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String estudianteId = (String)req.getSession().getAttribute("usuario");
		String docenteId = req.getParameter("docente");
		String idTurno = req.getParameter("turno");
		String fecha = req.getParameter("fechaTuto");
		try {
			Date fechaEscogida = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			//System.out.println(fechaEscogida);

			DAOFactory fabrica = new JPAFactory();
			Docente tutor = (Docente)fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(docenteId);
			Estudiante alumno = (Estudiante) fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).leer(estudianteId);
			List<Tutoria> tutoriasDocente = fabrica.crearTutoriaDAO().listarPorDocente(tutor);
			List<Tutoria> tutoriasEstudiante = fabrica.crearTutoriaDAO().listarPorEstudiante(alumno);
			Turno turnoEscogido = fabrica.crearTurnoDAO().leer(Integer.parseInt(idTurno));
			boolean estaOcupado =false; 
			if(tutoriasDocente != null) {
				for(Tutoria t:tutoriasDocente){
					if(t.getFecha().equals(fechaEscogida) && t.getHoraInicio().equals(turnoEscogido.getHoraInicio())) {
						estaOcupado = true;
						break;
					}
				}
				
			}
			if(tutoriasEstudiante != null) {
				for(Tutoria t:tutoriasEstudiante){
					if(t.getFecha().equals(fechaEscogida) && t.getHoraInicio().equals(turnoEscogido.getHoraInicio())) {
						estaOcupado = true;
						break;
					}
				}
				
			}
			if(!estaOcupado) {
				Tutoria t = new Tutoria(turnoEscogido.getHoraInicio(),fechaEscogida,tutor,alumno);
				fabrica.crearTutoriaDAO().crear(t);
			}
			req.setAttribute("resultado", estaOcupado);
			this.doGet(req, resp);
		}catch ( ParseException e) {
			this.doGet(req, resp);
		}
	}

}
