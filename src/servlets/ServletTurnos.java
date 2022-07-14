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

import daolmpl.DaoPacienteImpl;
import daolmpl.DaoTurnosImpl;
import entidad.Medicos;
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
			NegocioTurnosImpl neg = new NegocioTurnosImpl();
			PacienteNegocioImpl negP = new PacienteNegocioImpl();
			
			ArrayList<Medicos> lista = neg.ListarMedicos();
			request.setAttribute("ListaM", lista);
			
			Pacientes  p = new Pacientes();
			
			HttpSession session = request.getSession();
			p.setDNI(session.getAttribute("SessionDNIP").toString());
			
			
			ArrayList<Pacientes>  ListaPaciente= negP.listarPacientexDNI(p);
			request.setAttribute("DNIP", ListaPaciente);
			
			RequestDispatcher rd = request.getRequestDispatcher("Turnos.jsp");
			rd.forward(request, response);
			
			}
			catch (Exception e) {
				
			}
			
		}
		
		if(request.getParameter("ListaTurnos")!=null) {
    		try {
    			
    			
    			NegocioTurnosImpl neg = new NegocioTurnosImpl();
    			ArrayList<Turnos> lista = neg.ListarTurnos();
    			
    			request.setAttribute("ListaT", lista);
    			
    			RequestDispatcher rd = request.getRequestDispatcher("TurnosAdmin.jsp");
    			rd.forward(request, response);
    			
    		}
    		catch (Exception e) {
				// TODO: handle exception
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
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean filas= false;
		

		if(request.getParameter("btnAgregarTurno")!=null) {
    		
    		try {
    			Turnos t = new Turnos();
    			NegocioTurnosImpl neg = new NegocioTurnosImpl();
    			
    			String data = request.getParameter("Medico").toString();
    			String[] split = data.split("-");
    			
    			HttpSession session = request.getSession();
    			
    			t.setDNI_Paciente_Turno(session.getAttribute("SessionDNIP").toString());
    			t.setDNI_Medico_Turno(split[0]);
    			
    			t.setCod_Horario_Turno(Integer.valueOf(neg.CodigoHorario(t.getDNI_Medico_Turno())));
    			t.setDia_Turno(split[3]);
    			t.setFecha_Turno(request.getParameter("FechaTur").toString());
    			t.setInicio_Turno(request.getParameter("Hora_Inicio").toString());
    			t.setFin_Turno(request.getParameter("Hora_Fin").toString());
    			t.setEstado_Turno("OCUPADO");
    			
    			filas = neg.AgregarTurno(t);
    			
    			ArrayList<Turnos> lista = neg.ListarTurnos();
    			
    			request.setAttribute("ListaT", lista);
    			request.setAttribute("AgregadoEs", filas);
    			RequestDispatcher rd = request.getRequestDispatcher("TurnosAdmin.jsp");
    			rd.forward(request, response);
    		}
    		catch (Exception e) {
				// TODO: handle exception
    			e.printStackTrace();
			}
    	}
		
		if(request.getParameter("btnModificar")!=null) {
			try {
			NegocioTurnosImpl neg = new NegocioTurnosImpl();
			Turnos t = new Turnos();
			Usuario us = new Usuario();
			HttpSession session = request.getSession();
			
			t.setDNI_Paciente_Turno(request.getParameter("DNI_Paciente"));
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
		
		if(request.getParameter("btnModificarAdmin")!=null) {
			try {
			NegocioTurnosImpl neg = new NegocioTurnosImpl();
			Turnos t = new Turnos();
			
			t.setDNI_Paciente_Turno(request.getParameter("DNI_Paciente"));
			t.setObservacines(request.getParameter("Observaciones"));
			t.setEstado_Turno(request.getParameter("Estado"));
			t.setCod_Turno(Integer.valueOf(request.getParameter("Cod_Turno")));
			
			
			filas = neg.modificarTurno(t);
			
			
			ArrayList<Turnos> lista = neg.ListarTurnos();
			
			request.setAttribute("ListaT", lista);
			request.setAttribute("ModificacionEs", filas);
			RequestDispatcher rd = request.getRequestDispatcher("TurnosAdmin.jsp");
			rd.forward(request, response);
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
