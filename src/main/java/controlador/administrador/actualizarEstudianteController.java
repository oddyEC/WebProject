package controlador.administrador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidad.Estudiante;
import modelo.jpa.JPAEstudiante;
import modelo.jpa.JPAFactory;

/**
 * Servlet implementation class actualizarEstudianteController
 */
@WebServlet("/actualizarEstudianteController")
public class actualizarEstudianteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarEstudianteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cedula =  request.getParameter("act");
		DAOFactory fabrica = new JPAFactory();
		Estudiante e = (Estudiante)fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).leer(cedula);
		request.setAttribute("Nombre", e.getNombre());
    	request.setAttribute("Cedula", e.getCedula());
    	request.setAttribute("Apellido", e.getApellido());
    	getServletContext().getRequestDispatcher("/actualizarEstudiante.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}
	
	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String cedula = req.getParameter("cedula");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		DAOFactory fabrica = new JPAFactory();
		Estudiante e = (Estudiante) fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).leer(cedula);
		
		e.setApellido(apellido);
		e.setNombre(nombre);
		fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).actualizar(e);
    	
		//req.setAttribute("mensajeExito", "Se actualizó el estudiante");//mensaje
		//getServletContext().getRequestDispatcher("listarEstudianteController.jsp").forward(req, resp);
		resp.sendRedirect("listarEstudianteController");
	}

}
