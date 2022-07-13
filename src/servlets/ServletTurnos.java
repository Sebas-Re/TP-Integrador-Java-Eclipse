package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daolmpl.DaoTurnosImpl;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
