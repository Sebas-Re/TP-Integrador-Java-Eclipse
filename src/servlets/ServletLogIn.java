package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import daolmpl.DaoUsuarioImpl;
import entidad.Usuario;


/**
 * Servlet implementation class ServletLogIn
 */
@WebServlet("/ServletLogIn")
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnLogIn")!=null) {

			
		DaoUsuarioImpl DaoUsuario = new DaoUsuarioImpl();
		Usuario us = DaoUsuario.validarUsuario(request.getParameter("txtLogInUsuario").toString(), request.getParameter("txtLogInPass").toString());
		
		request.setAttribute("TipoUsuario", us.getTipoUsuario());
		HttpSession session = request.getSession();	
		session.setAttribute("DatosUsuario", us);
		
		switch (us.getTipoUsuario()) {
		case 1: //Administrador
			response.sendRedirect(request.getContextPath() + "/ServletReportes?" + "MostrarReportes=1");
			break;
		case 0: //Medico
			response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1");
			break;
		case -1: // No registrado en el sistema
			RequestDispatcher rd_LogIn = request.getRequestDispatcher("LogIn.jsp");
			rd_LogIn.forward(request, response);
			break;
		}
		
			
		
		}
		
	}



}
