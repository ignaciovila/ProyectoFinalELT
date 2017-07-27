package edu.curso.java.services;

import java.util.List;

import edu.curso.java.bo.Usuario;

public interface UsuarioService {

	Long crearNuevoUsuario(Usuario usuario);

	List<Usuario> recuperarUsuarios();
	
	List<Usuario> recuperarUsuariosPrincipalesInactivos();

	Usuario recuperarUsuarioPorId(Long id);
	
	void actualizarUsuario(Usuario usuario);
	
	List<Usuario> buscarUsuariosPorNombre(Long idProyecto,String nombre);
	
	List<Usuario> recuperarUsuariosInactivos();
	
	List<Usuario> buscarUsuariosPorNombre(String nombre);
}