<%@page import="java.sql.*"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<title>Index Admin</title>

</head>
<body>
<form method="post" action="ServletReportes">
</form>
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
	  <li class="nav__li"><a class="nav__li-a" href="Turnos.jsp">Turnos</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="TurnosMedico.jsp">Turnos Medicos</a></li>
	   <li class="nav__li"><%if(UsuarioLogeado.getTipoUsuario() == 1){ %><a class="nav__li-a" href="Reportes.jsp">Reportes</a><%} %></li>
	 <li class="nav__Usuario"><%=MensajeBienvenida%></li>
	 
	</ul>
</div>

<%
}catch(NullPointerException e){
response.sendRedirect(request.getContextPath() + "/ServletLogIn?" + "SessionVencida=1");

}
%>




<%
if(request.getAttribute("DiaMasConcurrido1")!=null){
	%>
	<h3>Reporte General</h3>
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Dia mas concurrido</th>
      <th scope="col">Frecuencia</th>

    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td><%=request.getAttribute("DiaMasConcurrido1").toString() %></td>
      <td><%=request.getAttribute("FrecuenciaDiaMasConcurrido1").toString() %></td>

    </tr>
    <tr>
      <th scope="row">2</th>
      <td><%=request.getAttribute("DiaMasConcurrido2").toString()%></td>
      <td><%=request.getAttribute("FrecuenciaDiaMasConcurrido2").toString()%></td>

    </tr>
        <tr>
      <th scope="row">3</th>
      <td><%=request.getAttribute("DiaMasConcurrido3").toString()%></td>
      <td><%=request.getAttribute("FrecuenciaDiaMasConcurrido3").toString()%></td>

    </tr>
    </tbody>
    </table>
    
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Horario mas concurrido</th>
      <th scope="col">Frecuencia</th>

    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td><%=request.getAttribute("HorarioMasConcurrido1").toString() %></td>
      <td><%=request.getAttribute("FrecuenciaHorarioMasConcurrido1").toString() %></td>

    </tr>
    <tr>
      <th scope="row">2</th>
      <td><%=request.getAttribute("HorarioMasConcurrido2").toString()%></td>
      <td><%=request.getAttribute("FrecuenciaHorarioMasConcurrido2").toString() %></td>

    </tr>
        <tr>
      <th scope="row">3</th>
      <td><%=request.getAttribute("HorarioMasConcurrido3").toString()%></td>
      <td><%=request.getAttribute("FrecuenciaHorarioMasConcurrido3").toString() %></td>

    </tr>
    </tbody>
    </table>

<% 
}
%>




</body>
</html>