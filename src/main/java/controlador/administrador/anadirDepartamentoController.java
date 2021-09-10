package controlador.administrador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidad.Departamento;
import modelo.entidad.Docente;
import modelo.jpa.JPAFactory;

@WebServlet("/anadirDepartamentoController")
public class anadirDepartamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String nombre = req.getParameter("nombreDepartamento");
		
        Departamento departamento = new Departamento(nombre);
        
        DAOFactory fabrica = new JPAFactory();
        List<Departamento> dptos = fabrica.crearDepartamentoDAO().listar();
        
        boolean bandera = false;
        if (dptos == null) {
            bandera = true;
        } else {
            for (Departamento d: dptos) {
                bandera = !d.getNombre().equals(nombre);
                if (!bandera) {
                	break;
                }
            }
        }
        
        if (bandera) {
        	fabrica.crearDepartamentoDAO().crear(departamento);	
            //resp.sendRedirect("listaDepartamentos.jsp");
        	req.setAttribute("estadoSolicitud", true);//mensaje
			getServletContext().getRequestDispatcher("/añadirDepartamento.jsp").forward(req, resp);
        } else {
        	req.setAttribute("depNombre", nombre);//Docente
			req.setAttribute("estadoSolicitud", false);//mensaje
			//Navego hacia el JSP
			getServletContext().getRequestDispatcher("/añadirDepartamento.jsp").forward(req, resp);
        }
        
	}

}
