package edu.curso.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.curso.java.bo.Proyecto;
import edu.curso.java.bo.Usuario;
import edu.curso.java.controllers.forms.UsuarioForm;
import edu.curso.java.services.UsuarioService;

@Controller
@RequestMapping(value="/usuarios")

public class UsuariosController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model){
		List<Usuario> usuarios = usuarioService.recuperarUsuarios();
		model.addAttribute("usuarios", usuarios);
		return null;
	}
	
	@RequestMapping(value="/buscarusuario")
	public String buscarUsuario(@RequestParam String nombre, Model model){
		List<Usuario> usuarios = usuarioService.buscarUsuariosPorNombre(nombre);
		model.addAttribute("usuarios", usuarios);
		return "/usuarios/tablaUsuarios";
	}
	
	@RequestMapping(value="/borrarusuario")
	public String borrarUsuario(@RequestParam Long id, Model model){
		Usuario usuario = usuarioService.recuperarUsuarioPorId(id);
		usuario.setBorrado(true);
		usuario.setActivo(false);
		usuarioService.actualizarUsuario(usuario);
		return "redirect:/usuarios/listar.html";
	}
	
	@RequestMapping(value="/verusuario")
	public String verUsuario(@RequestParam Long id, Model model ){		
		Usuario usuario = usuarioService.recuperarUsuarioPorId(id);
		model.addAttribute("usuario",usuario);
		return null;
	}
	

	@RequestMapping(value = "/nuevousuario")
	public String nuevoUsuario(Model model) {
		model.addAttribute("usuarioForm", new UsuarioForm());
		return "/usuarios/form";
	}
	
	@RequestMapping(value = "/editarusuario")
	public String editarUsuario(@RequestParam Long id,Model model) {
		
		Usuario usuario = usuarioService.recuperarUsuarioPorId(id);
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setId(usuario.getId());
		usuarioForm.setNombreCompleto(usuario.getNombreCompleto());
		usuarioForm.setUsuario(usuario.getUsuario());
		usuarioForm.setPassword(usuario.getPassword());
		usuarioForm.setFechaAlta(usuario.getFechaAlta());
		usuarioForm.setProjectManager(usuario.isProjectManager());
		model.addAttribute("usuarioForm", usuarioForm);
		return "/usuarios/form";
	}

	@RequestMapping(value = "/guardarusuario", method = RequestMethod.POST)
	public String guardarUsuario(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm, Model model) {

		Usuario usuario = null;
		Long idFormulario = usuarioForm.getId();
		
		if(idFormulario != null) {
			usuario = usuarioService.recuperarUsuarioPorId(idFormulario);
			usuario.setNombreCompleto(usuarioForm.getNombreCompleto());
			usuario.setUsuario(usuarioForm.getUsuario());
			usuario.setPassword(usuarioForm.getPassword());
			usuario.setFechaAlta(usuarioForm.getFechaAlta());
			usuario.setProjectManager(usuarioForm.isProjectManager());
			usuarioService.actualizarUsuario(usuario);
			
		} else {
			usuario = new Usuario();
			usuario.setNombreCompleto(usuarioForm.getNombreCompleto());
			usuario.setUsuario(usuarioForm.getUsuario());
			usuario.setPassword(usuarioForm.getPassword());
			usuario.setFechaAlta(usuarioForm.getFechaAlta());
			usuario.setBorrado(false);
			usuario.setProjectManager(usuarioForm.isProjectManager());
			idFormulario = usuarioService.crearNuevoUsuario(usuario);

		}
		return "redirect:/usuarios/verusuario.html?id=" + idFormulario;
	}
	
	@RequestMapping(value = "/tablaUsuarios")
	public String recargarTabla(Model model){
		List<Usuario> usuarios = usuarioService.recuperarUsuarios();
		model.addAttribute("usuarios", usuarios);
		return null;
	}

}
