<%-- 
    Document   : pruebaGenerarKardexJSP
    Created on : 1/04/2021, 01:10:20 AM
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mensaje = "";
    
    if (session.getAttribute("mensaje") != null) {
        mensaje = (String) session.getAttribute("mensaje");
        session.removeAttribute("mensaje");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>Generar Kardex</header>
        <form action="generarKardex" >

            <label for="matricula: ">Ingrese matricula del alumno: </label>
            <input type="text" name="matricula" id="matricula" required>

            <input type="submit" value="Generar reporte" />
        </form>
        <p><%=mensaje%></p>
    </body>
</html>
