package controlador.docente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidad.Departamento;
import modelo.entidad.Docente;
import modelo.jpa.JPAFactory;

/**
 * Servlet implementation class actualizarDoncenteController
 */
@WebServlet("/actualizarDoncenteController")
public class actualizarDoncenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarDoncenteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String cedula = (String) sesion.getAttribute("usuario");
	
		DAOFactory fabrica = new JPAFactory();
		Docente docente = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		request.setAttribute("docente", docente);
		Departamento departamento = docente.getDepartamento();

		int idDpt= docente.getDepartamento().getId();
    	DAOFactory fabricaA = new JPAFactory();
		List<Departamento> listaDptos = fabricaA.crearDepartamentoDAO().listar();
		listaDptos.remove(departamento);
		listaDptos.add(0,departamento);
		
		request.setAttribute("listaDptos", listaDptos);
	
		getServletContext().getRequestDispatcher("/actualizarDocente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String cedula = (String) sesion.getAttribute("usuario");
		
		DAOFactory fabricaC = new JPAFactory();
		Docente docente = (Docente) fabricaC.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
	
		
		String nombre = request.getParameter("nombreDocente");
		String apellido = request.getParameter("apellidoDocente");
		Integer idDpto = Integer.parseInt(request.getParameter("departamento"));
		DAOFactory fabrica = new JPAFactory();
		Departamento dpto = fabrica.crearDepartamentoDAO().leer(idDpto);
		DAOFactory fabricaB = new JPAFactory();
		docente.setNombre(nombre);
		docente.setDepartamento(dpto);
		docente.setApellido(apellido);
		fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).actualizar(docente);
		response.sendRedirect("visualizarDatosDocenteController");
	}

}
