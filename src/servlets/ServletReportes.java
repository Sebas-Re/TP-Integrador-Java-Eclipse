package servlets;

import java.io.IOException;
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
			Reportes reporte = new Reportes();
			reporte = negRep.CargarReportes();
			
			request.setAttribute("DiaMasConcurrido", reporte.getDias_Mas_Concurridos());
			request.setAttribute("FrecuenciaDiaMasConcurrido", reporte.getFrec_Dias_Mas_Concurridos());
			request.setAttribute("HorariosMasConcurridos", reporte.getHorarios_Mas_Concurridos());
			request.setAttribute("FrecuenciaHorarioMasConcurrido", reporte.getFrec_Horarios_Mas_Concurridos());
			
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
