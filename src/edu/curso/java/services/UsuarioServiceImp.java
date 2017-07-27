package edu.curso.java.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.bo.Usuario;
import edu.curso.java.dao.GenericDAO;
import edu.curso.java.dao.ProyectoDAO;
import edu.curso.java.dao.UsuarioDAO;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {

	private static final Logger log = Logger.
			getLogger(UsuarioServiceImp.class);
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProyectoDAO proyectoDAO;
	
	@Override
	public Long crearNuevoUsuario(Usuario usuario) {
		return usuarioDAO.create(usuario);
	}
	
	@Override
	public List<Usuario> recuperarUsuarios() {
		return usuarioDAO.returnList();
	}
	
	public List<Usuario> recuperarUsuariosInactivos() {
		return usuarioDAO.recuperarUsuariosInactivos();
	}
	
	public List<Usuario> recuperarUsuariosPrincipalesInactivos(){
		return usuarioDAO.recuperarUsuariosPrincipalesInactivos();
	}
	
	@Override
	public Usuario recuperarUsuarioPorId(Long id) {
		log.info("Recuperando el usuario con id: " + id);
		return usuarioDAO.read(id);
	}
	
	@Override
	public void actualizarUsuario(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	public List<Usuario> buscarUsuariosPorNombre(Long idProyecto, String nombre){
		return usuarioDAO.buscarUsuariosPorNombre(idProyecto,nombre);
	}

	@Override
	public List<Usuario> buscarUsuariosPorNombre(String nombre) {
		return usuarioDAO.buscarUsuariosPorNombre(nombre);
	}
}
