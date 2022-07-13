<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuario Medico</title>

<style type="text/css">
	<jsp:include page="css\medicos.css"></jsp:include> 
	
</style>


</head>
<body>
<%

Usuario UsuarioLogeado = new Usuario();
UsuarioLogeado = (Usuario) session.getAttribute("DatosUsuario");
String MensajeBienvenida = "Bienvenido "+UsuarioLogeado.getNombreUsuario();
%>
<div>

	<ul class="" id="Navegacion">
	  <li class="nav__li"><a class="nav__li-a" href="UsuarioAdm.jsp">Home</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Medicos.jsp">Medicos</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Pacientes.jsp">Pacientes</a></li>
	  <li class="nav__li"><a class="nav__li-a" href="Turnos.jsp">Turnos</a></li>
	 <li class="nav__Usuario"><%=MensajeBienvenida%></li>
	</ul>
</div>




<!-- 		<div class="ConteinerTable"> -->
<!-- 				<table class="table table-bordered" > -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th>DNI</th> -->
<!-- 						<th>NOMBRE</th> -->
<!-- 						<th>APELLIDO</th> -->
<!-- 						<th>SEXO</th> -->
<!-- 						<th>NACIONALIDAD</th> -->
<!-- 						<th>FECHA DE NACIMIENTO</th> -->
<!-- 						<th>DIRECCION</th> -->
<!-- 						<th>LOCALIDAD</th> -->
<!-- 						<th>PROVINCIA</th> -->
<!-- 						<th>CORREO ELECTRONICO</th> -->
<!-- 						<th>TELEFONO</th> -->
<!-- 						<th>ESPECIALIDAD</th> -->
<!-- 						<th>DIA</th> -->
<!-- 						<th>HORARIO DE ATENCION</th> -->
<!-- 						<th>ACCIONES</th> -->
<!-- 					</tr> -->
<!-- 				   </thead> -->
<%-- 				   <% --%>
<!--  				   	while(rs.next()){ -->
<%-- 				   %> --%>
<!-- 				   <tbody> -->
<!-- 					<tr> -->
<%-- 						<td><%= rs.getString("dniMedico") %></td> --%>
<%-- 						<td><%= rs.getString("nombreMedico") %></td> --%>
<%-- 						<td><%= rs.getString("apellidoMedico") %></td> --%>
<%-- 						<td><%= rs.getString("sexoMedico") %></td> --%>
<%-- 						<td><%= rs.getString("nacionalidadMedico") %></td> --%>
<%-- 						<td><%= rs.getString("fechaNacimientoMedico") %></td> --%>
<%-- 						<td><%= rs.getString("direccionMedico") %></td> --%>
<%-- 						<td><%= rs.getString("localidadMedico") %></td> --%>
<%-- 						<td><%= rs.getString("provinciaMedico") %></td> --%>
<%-- 						<td><%= rs.getString("correoElectronicoMedico") %></td> --%>
<%-- 						<td><%= rs.getString("telefono") %></td> --%>
<%-- 						<td><%= rs.getString("especialidad") %></td> --%>
<%-- 						<td><%= rs.getString("dia") %></td> --%>
<%-- 						<td><%= rs.getTime("horario") %></td> --%>
<!-- 						<td class="btnControl"> -->
<!-- 							<a class="btn btn-warning btn-sm">Editar</a> -->
<!-- 							<a class="btn btn-danger btn-sm">Eliminar</a> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					</tbody> -->
<%-- 					<%} %> --%>
<!-- 				</table> -->
<!-- 			</div> -->








</body>
</html>