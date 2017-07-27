package edu.curso.java.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.bo.Proyecto;
import edu.curso.java.bo.Usuario;
import edu.curso.java.dao.ProyectoDAO;
import edu.curso.java.dao.UsuarioDAO;

@Service
@Transactional
public class ProyectoServiceImp implements ProyectoService {

	@Autowired
	ProyectoDAO proyectoDAO;

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public List<Proyecto> recuperarProyectos() {
		return proyectoDAO.returnList();
	}

	@Override
	public Long guardarProyecto(Proyecto proyecto) {
		Usuario usuarioPrincipal = proyecto.getUsuarioPrincipal();
		usuarioPrincipal.setActivo(true);
		usuarioDAO.update(usuarioPrincipal);
		List<Usuario> usuarios = proyecto.getUsuarios();
		for (Usuario usuario : usuarios) {
			usuario.setActivo(true);
			usuarioDAO.update(usuario);
		}
		return proyectoDAO.create(proyecto);
	}

	@Override
	public Proyecto recuperarProyectoPorId(Long id) {
		return proyectoDAO.read(id);
	}

	@Override
	public void borrarProyectoPorId(Long id) {
		Proyecto proyecto = proyectoDAO.read(id);

		Usuario usuarioPrincipal = proyecto.getUsuarioPrincipal();
		usuarioPrincipal.setActivo(false);
		usuarioDAO.update(usuarioPrincipal);

		for (Usuario usuario : proyecto.getUsuarios()) {
			usuario.setActivo(false);
			usuarioDAO.update(usuario);
		}

		proyecto.setBorrado(true);

		proyectoDAO.update(proyecto);
	}

	public List<Proyecto> buscarProyectosPorNombre(String nombre) {
		return proyectoDAO.buscarProyectosPorNombre(nombre);

	}

	@Override
	public void actualizarProyecto(Proyecto proyecto) {
		proyectoDAO.update(proyecto);
	}

	@Override
	public void actualizarProyecto(Proyecto proyecto, Long idUsuarioPrincipal, List<Long> idUsuarios) {

		Usuario usuarioPrinOriginal = new Usuario();
		usuarioPrinOriginal = proyecto.getUsuarioPrincipal();
		
		List<Usuario> usuariosOriginales = new ArrayList<Usuario>();
		usuariosOriginales.addAll(proyecto.getUsuarios());

		proyecto.getUsuarios().clear();

		Usuario usuarioPrincipal = usuarioDAO.read(idUsuarioPrincipal);
		
		for (Long idUsuario : idUsuarios) {
			Usuario usuario = usuarioDAO.read(idUsuario);
			usuario.setActivo(true);
			usuarioDAO.update(usuario);
			if(idUsuario != idUsuarioPrincipal){
				proyecto.getUsuarios().add(usuario);
			}
		}

		if (usuarioPrinOriginal.getId() != idUsuarioPrincipal) {
			if (!proyecto.getUsuarios().contains(usuarioPrinOriginal)) {
				usuarioPrinOriginal.setActivo(false);
				usuarioDAO.update(usuarioPrinOriginal);
			}

			if (!usuariosOriginales.contains(usuarioPrincipal)) {
				usuarioPrincipal.setActivo(true);
				usuarioDAO.update(usuarioPrincipal);
			}
			proyecto.setUsuarioPrincipal(usuarioPrincipal);
		}
		
		for (Usuario usuario : usuariosOriginales) {
			if(!proyecto.getUsuarios().contains(usuario) ){
				usuario.setActivo(false);
				usuarioDAO.update(usuario);
			}
		}		

		proyectoDAO.update(proyecto);
	}

	@Override
	public Long guardarProyecto(Proyecto proyecto, Long idUsuarioPrincipal, List<Long> idUsuarios) {

		Usuario usuarioPrincipal = usuarioDAO.read(idUsuarioPrincipal);
		usuarioPrincipal.setActivo(true);
		usuarioDAO.update(usuarioPrincipal);
		proyecto.setUsuarioPrincipal(usuarioPrincipal);

		for (Long idUsuario : idUsuarios) {
			Usuario usuario = usuarioDAO.read(idUsuario);
			usuario.setActivo(true);
			usuarioDAO.update(usuario);
			
				proyecto.getUsuarios().add(usuario);
			
		}

		proyectoDAO.create(proyecto);

		return proyecto.getId();
	}
}
