<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover">
<tr>
	<th class="text-center">Id</th>
	<th class="text-center">Usuario</th>
	<th class="text-center">Nombre Completo</th>
	<th class="text-center">Fecha alta</th>
	<th class="text-center">Usuario Principal</th>
	<th class="text-center">Activo</th>
	<th class="text-center">Opciones</th>
</tr>
<c:forEach items="${usuarios}" var="u">
		<tr>
			<td class="text-center">${u.id}</td>
			<td class="text-center">${u.usuario}</td>
			<td class="text-center">${u.nombreCompleto}</td>
			<td class="text-center"><fmt:formatDate value="${u.fechaAlta}"
					pattern="yyyy-MM-dd" /></td>
			<td class="text-center"><c:choose>
			<c:when test="${u.projectManager}">
				SI
			</c:when>
			<c:otherwise>
				NO
			</c:otherwise>
			</c:choose></td>
			<td class="text-center"><c:choose>
			<c:when test="${u.activo}">
				SI
			</c:when>
			<c:otherwise>
				NO
			</c:otherwise>
			</c:choose></td>
			<td class="text-left">
			<a href="verusuario.html?id=${u.id}" class="btn btn-default">
					<span class="botonIcono glyphicon glyphicon-eye-open" aria-hidden="true">
					</span>Ver
				</a>
				<a href="editarusuario.html?id=${u.id}" class="btn btn-default">
					<span class="botonIcono glyphicon glyphicon-pencil" aria-hidden="true">
					</span>Editar
				</a>
				<c:if test="${not u.activo}">
					<a href="borrarusuario.html?id=${u.id}" class="btn btn-default btn-borrar-usuario">
						<span class="botonIcono glyphicon glyphicon-trash" aria-hidden="true">
						</span>Borrar
					</a> 
				</c:if>							
			</td>
		</tr>
</c:forEach>		
</table>