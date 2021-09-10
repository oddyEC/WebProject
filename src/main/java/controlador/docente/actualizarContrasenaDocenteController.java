package controlador.docente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidad.Docente;
import modelo.jpa.JPAFactory;

@WebServlet("/actualizarContrasenaDocenteController")
public class actualizarContrasenaDocenteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public actualizarContrasenaDocenteController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();

		String cedula = (String) sesion.getAttribute("usuario");//Usuario jsp
		
		String contraseñaActual = request.getParameter("passwd");
		String nuevaContraseña = request.getParameter("passwd1");
		String nuevaContraseñaVerificar = request.getParameter("passwd2");

		DAOFactory fabrica = new JPAFactory();
		Docente docente = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).leer(cedula);
		if ( nuevaContraseña.equals(nuevaContraseñaVerificar) && cedula.equals(docente.getCedula()) 
				&& contraseñaActual.equals(docente.getContraseña())){
			docente.setContraseña(nuevaContraseña);
			fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).actualizar(docente);
			response.sendRedirect("docentePasswd.jsp");
		}else{
			request.setAttribute("mensajeError", "Las contraseñas no coinciden");
			getServletContext().getRequestDispatcher("/docentePasswd.jsp").forward(request, response);
		}
	}
	
}
