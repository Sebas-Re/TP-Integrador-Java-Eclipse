<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicie sesion</title>
</head>
<center><h1>Bienvenido</h1></center>
<body>
<center>
<h2>Por favor inicie sesion para continuar</h2>

<form action="ServletLogIn" method="post">
Usuario  <input type="text" name="txtLogInUsuario" style="width: 154px; "/> <br><br>
Contrase�a <input type="password" name="txtLogInPass" style="width: 158px; "/> <br> <br>

<input type="submit" name="btnLogIn" value="Iniciar Sesion"/>




<%

if(request.getAttribute("TipoUsuario") != null)
{
%>
	<label id="LblInicioIncorrecto">Datos de inicio de sesion incorrectos</label>
<%
}
%>



</form>
</center>
</body>
</html>