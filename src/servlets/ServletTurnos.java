package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daolmpl.DaoTurnosImpl;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.NegocioTurnosImpl;
import negocioImpl.PacienteNegocioImpl;

/**
 * Servlet implementation class ServletTurnos
 */
@WebServlet("/ServletTurnos")
public class ServletTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTurnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @param session 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("AgregarInfo")!=null) {
			try{
			DaoTurnosImpl dao = new DaoTurnosImpl();
			ArrayList<String> listaEspecialidad = dao.listarEspecialidad();
		
			request.setAttribute("ListaE", listaEspecialidad);
			
			RequestDispatcher rd = request.getRequestDispatcher("Turnos.jsp");
			rd.forward(request, response);
			
			}
			catch (Exception e) {
				
			}
			
		}
		
		if(request.getParameter("TurnosxMedico")!=null) {
    		try {
    			Usuario us = new Usuario();
    			HttpSession session = request.getSession();
    			us = (Usuario) session.getAttribute("DatosUsuario");
    			
    			NegocioTurnosImpl neg = new NegocioTurnosImpl();
    			ArrayList<Turnos> lista = neg.ListarTurnosXMedico(us);
    			
    			request.setAttribute("ListaTM", lista);
    			
    			RequestDispatcher rd = request.getRequestDispatcher("TurnosMedico.jsp");
    			rd.forward(request, response);
    			
    		}
    		catch (Exception e) {
				// TODO: handle exception
			}
    	}
		
		if(request.getParameter("AgregarMedicos")!=null) {
			//String Especialidad = request.getParameter("SelectEspecialidad").toString();
			System.out.println(request.getParameter("SelectEspecialidad").toString());
			try{
				DaoTurnosImpl dao = new DaoTurnosImpl();
				
				
			//ArrayList<String> listaMedicos = dao.ListarMedicos(request.getParameter("SelectEspecialidad"));
			
			//request.setAttribute("ListaM", listaMedicos);
			
			RequestDispatcher rd = request.getRequestDispatcher("Turnos.jsp");
			rd.forward(request, response);
			}
			catch (Exception e) {
				
			}
			
		}
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean filas= false;
		if(request.getParameter("btnModificar")!=null) {
			try {
			NegocioTurnosImpl neg = new NegocioTurnosImpl();
			Turnos t = new Turnos();
			Usuario us = new Usuario();
			HttpSession session = request.getSession();
			
			t.setObservacines(request.getParameter("Observaciones"));
			t.setEstado_Turno(request.getParameter("Estado"));
			t.setCod_Turno(Integer.valueOf(request.getParameter("Cod_Turno")));
			
			
			filas = neg.modificarTurno(t);
			
			us = (Usuario) session.getAttribute("DatosUsuario");
			
			ArrayList<Turnos> lista = neg.ListarTurnosXMedico(us);
			
			request.setAttribute("ListaTM", lista);
			request.setAttribute("ModificacionEs", filas);
			RequestDispatcher rd = request.getRequestDispatcher("TurnosMedico.jsp");
			rd.forward(request, response);
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
