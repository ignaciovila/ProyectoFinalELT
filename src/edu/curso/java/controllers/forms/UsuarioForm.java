package edu.curso.java.controllers.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UsuarioForm {
	
	private Long id;
	private String nombreCompleto;
	private String usuario;
	private String password; 
	private boolean projectManager;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAlta = new Date();	
	
	
	
	public boolean isProjectManager() {
		return projectManager;
	}
	public void setProjectManager(boolean projectManager) {
		this.projectManager = projectManager;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	
}
