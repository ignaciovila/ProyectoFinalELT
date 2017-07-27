package edu.curso.java.controllers.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class ProyectoForm {

	private Long id;
	private String nombre;
	private String descripcion;
	private Long idUsuarioPrincipal;
	private List<Long> idUsuarios = new ArrayList<Long>();
	private int horasAsignadas;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio = new Date();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFinalizacion = new Date();
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public List<Long> getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(List<Long> idUsuarios) {
		this.idUsuarios = idUsuarios;
	}

	public Long getIdUsuarioPrincipal() {
		return idUsuarioPrincipal;
	}

	public void setIdUsuarioPrincipal(Long idUsuarioPrincipal) {
		this.idUsuarioPrincipal = idUsuarioPrincipal;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getHorasAsignadas() {
		return horasAsignadas;
	}

	public void setHorasAsignadas(int horasAsignadas) {
		this.horasAsignadas = horasAsignadas;
	}
	
}
