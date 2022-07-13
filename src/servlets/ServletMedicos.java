package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;
import negocioImpl.MedicoNegocioImpl;

/**
 * Servlet implementation class ServletMedicos
 */
@WebServlet("/ServletMedicos")
public class ServletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMedicos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param=1")!=null) {
			try {
			MedicoNegocioImpl neg = new MedicoNegocioImpl();
			ArrayList<Turnos> lista= neg.listarTurnos();
			
			request.setAttribute("ListaT", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("TurnosMedico.jsp");
			rd.forward(request, response);	
			}
		catch (Exception e) {
				// TODO: handle exception
			}
					
		}
		
		if(request.getParameter("btnInformacion")!=null) {
			
			try {
				MedicoNegocioImpl neg = new MedicoNegocioImpl();
				Pacientes p = new Pacientes();
				p.setDNI(request.getParameter("DNI_Paciente"));
				ArrayList<Pacientes> lista= neg.ListarPacientePorDNI(p);
				
				request.setAttribute("ListaP", lista);
				RequestDispatcher rd = request.getRequestDispatcher("InformacionTurnoPaciente.jsp");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Agregado = 0;
		
		if(request.getParameter("BtnAgregar")!=null)
		{
		
		
		Usuario Us = new Usuario();
		
		Us.setNombreUsuario(request.getParameter("txtNombreUs"));
		Us.setContraseñaUsuario(request.getParameter("txtContra")); //recordar que en la vista hay que pedirla 2 veces para confirmar.
		Us.seteMailUsuario(request.getParameter("txtEmail"));
		Us.setTipoUsuario(0);
		
		Medicos med = new Medicos();
		
		med.setDni_m(request.getParameter("txtDni"));
		med.setNombre_m(request.getParameter("txtNombreMed"));
		med.setApellido_m(request.getParameter("txtApellido"));
		med.setSexo_m(request.getParameter("txtSexo"));
		med.setFechaNac_m(request.getParameter("txtFechaNac"));
		med.setDireccion_m(request.getParameter("txtDirec"));
		med.setLocalidad_m(request.getParameter("txtLocalidad"));
		med.setProvincia_m(request.getParameter("txtProv"));
		med.setCorreoElectronico_m(request.getParameter("txtEmail"));
		med.setTelefono_m(request.getParameter("txtTel"));
		med.setEspecialidad(request.getParameter("txtEsp"));
		med.setDia(request.getParameter("txtDia"));
		med.setHorarioAtencion(request.getParameter("txtHorarioAtencion"));
		med.setEstado(true);
		
		MedicoNegocioImpl negMed = new MedicoNegocioImpl();
		
		Agregado = negMed.agregarMedico(Us, med); //Si devuelve 1, sólo uno de los dos se añadió. Si devuelve 2, todo se agregó correctamente.
		}
		
		request.setAttribute("MedicoAgregado", Agregado);
		RequestDispatcher rd = request.getRequestDispatcher("Medicos.jsp");
		rd.forward(request, response);
		
	}

}
