package controlador.administrador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidad.Docente;
import modelo.entidad.Estudiante;
import modelo.jpa.JPAFactory;

@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String recordar= req.getParameter("recordarme");
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		
		// admin //admin2021
		DAOFactory fabrica = new JPAFactory();
		Docente docente = (Docente) fabrica.crearUsuarioDAO(JPAFactory.DOCENTE).autorizar(usuario, password);
		Estudiante estudiante = (Estudiante) fabrica.crearUsuarioDAO(JPAFactory.ESTUDIANTE).autorizar(usuario, password);
		Cookie galletaUser= new Cookie("usuario", "");
		Cookie galletaPassword = new Cookie("password", "");
		Cookie galletaBandera = new Cookie("recordar", "");
	
		
		if (recordar!=null && recordar.equals("on")) {
			
			galletaUser.setValue(usuario);
			galletaPassword.setValue(password);
			galletaBandera.setValue(recordar);
			
		}
			else{
			
			galletaUser.setMaxAge(0);
			galletaPassword.setMaxAge(0);
			galletaBandera.setMaxAge(0);
			resp.addCookie(galletaPassword);
			resp.addCookie(galletaUser);
			resp.addCookie(galletaBandera);
		}


		if (usuario.equals("admin") && password.equals("admin2021")) {
			// getServletContext().getRequestDispatcher("/ModuloAdministrador.jsp").forward(req,
			// resp);
			HttpSession sesion = req.getSession();
			sesion.setAttribute("usuario", usuario);
			String tipo = "admin";
			sesion.setAttribute("tipo", tipo);
			// Navego hacia el JSP
			resp.addCookie(galletaPassword);
			resp.addCookie(galletaUser);
			resp.addCookie(galletaBandera);
			getServletContext().getRequestDispatcher("/ModuloAdministrador.jsp").forward(req, resp);

		} else if (docente != null) {

			HttpSession sesion = req.getSession();
			sesion.setAttribute("usuario", usuario);
			String tipo = "docente";
			sesion.setAttribute("tipo", tipo);
			// Navego hacia el JSP
			resp.addCookie(galletaPassword);
			resp.addCookie(galletaUser);
			resp.addCookie(galletaBandera);
			getServletContext().getRequestDispatcher("/ModuloDocente.jsp").forward(req, resp);

		} else if (estudiante != null) {
			HttpSession sesion = req.getSession();
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("tipo", "estudiante");
			
			resp.addCookie(galletaPassword);
			resp.addCookie(galletaUser);
			resp.addCookie(galletaBandera);
			getServletContext().getRequestDispatcher("/moduloEstudiante.jsp").forward(req, resp);

		} else {
			galletaUser.setMaxAge(0);
			galletaPassword.setMaxAge(0);
			galletaBandera.setMaxAge(0);
			resp.addCookie(galletaPassword);
			resp.addCookie(galletaUser);
			resp.addCookie(galletaBandera);
			resp.sendRedirect("index.jsp");
		}

	}

}
