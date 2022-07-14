<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Pacientes"%>
<%@page import="entidad.Medicos"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>


</head>
<body>
<%
try{
Usuario UsuarioLogeado = new Usuario();
UsuarioLogeado = (Usuario) session.getAttribute("DatosUsuario");
String MensajeBienvenida = "Bienvenido "+UsuarioLogeado.getNombreUsuario();

if(UsuarioLogeado.getTipoUsuario() == 0)
{
	response.sendRedirect(request.getContextPath() + "/ServletMedicos?" + "ListarMedicos=1");
}
%>
<div>

	<ul class="" id="Navegacion">
	<li class="nav__Usuario"><a class="nav__li-a" href="ServletLogIn?CerrarSesion=1">Cerrar Sesion</a></li>
	  <li class="nav__li"><%if(UsuarioLogeado.getTipoUsuario() == 1){ %><a class="nav__li-a" href="ServletReportes?MostrarReportes=1">Home</a><%} %></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServletMedicos?ListarMedicos=1">Medicos</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServeletPaciente?ListarPacientes=1">Pacientes</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="ServletTurnos?ListaTurnos=1">Listar Turnos</a></li>
	   <li class="nav__li"><%if(UsuarioLogeado.getTipoUsuario() == 1){ %><a class="nav__li-a" href="Reportes.jsp">Reportes</a><%} %></li>
	 <li class="nav__Usuario"><%=MensajeBienvenida%></li>
	 
	</ul>
</div>

<%
}catch(NullPointerException e){
response.sendRedirect(request.getContextPath() + "/ServletLogIn?" + "SessionVencida=1");

}
%>

<h1>Pagina Reportes</h1>

	<form method="post" action="ServletReportes">
	<p>		<select name="FiltrarTipoPersona">		
			  <option value="1">Medico</option>
			  <option value="2">Paciente</option>
			</select>
			<select name="FiltrarEstado">
			  <option value="0">Todos</option>		
			  <option value="1">Baja</option>
			  <option value="2">Alta</option>
			</select>
			
		Fecha Inicial<input type="date" name="FechaInicial" required/>
		Fecha Final<input type="date" name="FechaFinal" required/>
		<input type="submit" name="btnFiltrar" value="Filtar">
</p>





<table class="table table-hover table-borderless" >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">DNI</th>
      <th scope="col">Nombre</th>
	  <th scope="col">Apellido</th>
	  <th scope="col">Email</th>
	  <th scope="col">Telefono</th>
	  <th scope="col">Estado</th>
	  <th scope="col"># Turnos activos</th>

    </tr>
  </thead>
  <tbody>
  
  <%
  ArrayList<Pacientes> lsPacientes = null;
  ArrayList<Medicos> lsMedicos = null;
  
  
 if(request.getAttribute("ListaMedicos") != null)
{
	 lsMedicos = (ArrayList<Medicos>)request.getAttribute("ListaMedicos");
	 int Fila = 0;
	 for(Medicos m : lsMedicos)
	 {
		 Fila++;
		 %>
		    <tr>
		      <th scope="row"><%=Fila %></th>
		      <td><%=m.getDni_m()%></td>
		      <td><%=m.getNombre_m()%></td>
			  <td><%=m.getApellido_m()%></td>
		      <td><%=m.getCorreoElectronico_m()%></td>
		      <td><%=m.getTelefono_m()%></td>
		      <td><%=m.getEstado()%></td>
		      <td><%=m.getTurnosActivos()%></td>
		    </tr>
		    <%
	 }
}

if(request.getAttribute("ListaPacientes") != null)
{
	lsPacientes = (ArrayList<Pacientes>)request.getAttribute("ListaPacientes");
	int Fila = 0;
	
	for(Pacientes p : lsPacientes)
	{
		Fila++;
		%>
	    <tr>
	      <th scope="row"><%=Fila %></th>
	      <td><%=p.getDNI()%></td>
	      <td><%=p.getNombre()%></td>
		  <td><%=p.getApellido()%></td>
	      <td><%=p.getCorreo()%></td>
	      <td><%=p.getTelefono()%></td>
	      <td><%=p.getEstado()%></td>
	      <td><%=p.getTurnosActivos()%></td>
	    </tr>
	    <%
		
	}
}
  %>
    </tbody>
    </table>



</form>
</body>
</html>