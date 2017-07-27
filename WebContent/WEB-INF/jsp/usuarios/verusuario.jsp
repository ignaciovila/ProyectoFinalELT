<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/general/template_top.jsp" />
<div class='page-header'>
	<div class='btn-toolbar pull-right'>
		<div class='btn-group'>
			<a href="listar.html" class="btn btn-default">Volver</a>
		</div>
	</div>
	<h2>Usuario: ${usuario.nombreCompleto}</h2>
</div>

<div class="row">
	<div class="col-md-6">
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td><b>Id</b></td>
				<td>${usuario.id}</td>
			</tr>
			<tr>
				<td><b>Usuario</b></td>
				<td>${usuario.usuario}</td>
			</tr>
			<tr>
				<td><b>Fecha Alta</b></td>
				<td><fmt:formatDate value="${usuario.fechaAlta}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td><b>Usuario Principal</b></td>
				<td><c:choose>
						<c:when test="${usuario.projectManager}">
							SI
						</c:when>
						<c:otherwise>
							NO
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td><b>Activo</b></td>
				<td><c:choose>
						<c:when test="${usuario.activo}">
							SI
						</c:when>
						<c:otherwise>
							NO
						</c:otherwise>
					</c:choose></td>
			</tr>

		</table>
	</div>
</div>
<c:import url="/general/template_bottom.jsp" />