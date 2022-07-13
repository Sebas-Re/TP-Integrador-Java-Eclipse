<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos</title>
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

</head>
<body>
<div id="formulario" align="center">
<form name="formRegistro" action="" method="post">

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
			<table>
			<tr>
				<td>Especialidad</td>
				<td>
					<select name="Especialidad">
					<option value="Elegir" selected>Especialidad</option>
					<option value="Proctólogo" slected>Proctólogo</option>
					<option value="Anestesiólogo" slected>Anestesiólogo</option>
					<option value="Alergólogo" slected>Alergólogo</option>
					<option value="Cardiólogo" slected>Cardiólogo</option>
					<option value="Neurólogo" slected>Neurólogo</option>
					<option value="Cirujano" slected>Cirujano</option>
					<option value="Dermatólogo" slected>Dermatólogo</option>
					<option value="Endocrinólogo" slected>Endocrinólogo</option>
					<option value="Gastroenterólogo" slected>Gastroenterólogo</option>
					<option value="Neumólogo" slected>Neumólogo</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Medico</td>
				<td>
					<select name="NombresMedicos">
				</td>
			</tr>
			<tr>
				<td>dia</td>
				<td>
					<select name="Dias">
				</td>
			</tr>
			<tr>
				<td>Mes</td>
				<td>
					<select name="NombresMedicos">
			</td>
			<tr>
				<td>Medico</td>
				<td>
					<select name="NombresMedicos">
				</td>
			</tr>
			</tr>
				<tr>
				<td>Horario</td>
				<td>
					<select name="NombresMedicos">
				</td>
			</tr>
			
		</table>
		</form>
		</div>
</body>
</html>