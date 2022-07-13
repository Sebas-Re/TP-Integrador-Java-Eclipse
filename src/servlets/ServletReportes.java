package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Reportes;
import negocioImpl.ReportesNegocioImpl;

/**
 * Servlet implementation class ServletReporte
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if(request.getParameter("MostrarReportes")!= null)
				{
			ReportesNegocioImpl negRep = new ReportesNegocioImpl();
			ArrayList<Reportes> vRep = new ArrayList<Reportes>(3);
			vRep = negRep.CargarReportes();
			
			request.setAttribute("DiaMasConcurrido1", vRep.get(0).getDias_Mas_Concurridos());
			request.setAttribute("FrecuenciaDiaMasConcurrido1", vRep.get(0).getFrec_Dias_Mas_Concurridos());
			request.setAttribute("HorarioMasConcurrido1",  vRep.get(0).getHorarios_Mas_Concurridos());
			request.setAttribute("FrecuenciaHorarioMasConcurrido1", vRep.get(0).getFrec_Horarios_Mas_Concurridos());
			
			request.setAttribute("DiaMasConcurrido2", vRep.get(1).getDias_Mas_Concurridos());
			request.setAttribute("FrecuenciaDiaMasConcurrido2", vRep.get(1).getFrec_Dias_Mas_Concurridos());
			request.setAttribute("HorarioMasConcurrido2",  vRep.get(1).getHorarios_Mas_Concurridos());
			request.setAttribute("FrecuenciaHorarioMasConcurrido2", vRep.get(1).getFrec_Horarios_Mas_Concurridos());
			
			request.setAttribute("DiaMasConcurrido3", vRep.get(2).getDias_Mas_Concurridos());
			request.setAttribute("FrecuenciaDiaMasConcurrido3", vRep.get(2).getFrec_Dias_Mas_Concurridos());
			request.setAttribute("HorarioMasConcurrido3",  vRep.get(2).getHorarios_Mas_Concurridos());
			request.setAttribute("FrecuenciaHorarioMasConcurrido3", vRep.get(2).getFrec_Horarios_Mas_Concurridos());
				}
		
		
		
		request.getRequestDispatcher("/UsuarioAdm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
