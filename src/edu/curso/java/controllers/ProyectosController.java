package edu.curso.java.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.curso.java.bo.Proyecto;
import edu.curso.java.bo.Tarea;
import edu.curso.java.bo.Usuario;
import edu.curso.java.controllers.forms.ProyectoForm;
import edu.curso.java.services.ProyectoService;
import edu.curso.java.services.TareaService;
import edu.curso.java.services.UsuarioService;

@Controller
@RequestMapping(value = "/proyectos")

public class ProyectosController {

	@Autowired
	private ProyectoService proyectoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TareaService tareaService;
	
	@RequestMapping(value = "/ListadoProyectosExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel(Model model) {
		List<Proyecto> proyectos = proyectoService.recuperarProyectos();
		model.addAttribute("proyectos", proyectos);
		return new ModelAndView("excelView", "proyectos", proyectos);
	}
	
	// LISTADO
	@RequestMapping(value = "/listado/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Proyecto> proyectos = proyectoService.recuperarProyectos();
		model.addAttribute("proyectos", proyectos);
		List<Usuario> todosusuarios = new ArrayList<Usuario>();
		todosusuarios.addAll(usuarioService.recuperarUsuariosInactivos());
		todosusuarios.addAll(usuarioService.recuperarUsuariosPrincipalesInactivos());
		List<Usuario> usuariosPrincipales = usuarioService.recuperarUsuariosPrincipalesInactivos();
		
		List<Usuario> usuarios = usuarioService.recuperarUsuariosInactivos();
		model.addAttribute("todosUsuarios", todosusuarios);
		model.addAttribute("usuariosPrincipales", usuariosPrincipales);
		model.addAttribute("usuarios", usuarios);
		return null;
	}

	@RequestMapping(value = "/listado/borrarproyecto")
	public String borrarProyecto(@RequestParam Long id, Model model) {
		proyectoService.borrarProyectoPorId(id);
		return "redirect:/proyectos/listado/listar.html";
	}

	@RequestMapping(value = "/listado/listarproyectos")
	public String listado(Model model) {
		List<Proyecto> proyectos = proyectoService.recuperarProyectos();
		model.addAttribute("proyectos", proyectos);
		List<Usuario> usuarios = usuarioService.recuperarUsuariosInactivos();
		model.addAttribute("usuarios", usuarios);
		return "/proyectos/listado/buscarproyectos";
	}

	@RequestMapping(value = "/listado/buscarproyectos", method = RequestMethod.POST)
	public String buscarProyecto(@RequestParam String nombreBuscado, Model model) {
		List<Proyecto> proyectos = proyectoService.buscarProyectosPorNombre(nombreBuscado);
		model.addAttribute("proyectos", proyectos);
		return null;
	}

	@RequestMapping(value = "/listado/infoproyectos", method = RequestMethod.POST)
	public String infoProyecto(@RequestParam Long id, Model model) {
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		model.addAttribute("proyecto", proyecto);
		return null;
	}

	@RequestMapping(value = "/listado/confirmarborrado", method = RequestMethod.POST)
	public String confirmarborrado(@RequestParam Long id, Model model) {
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		model.addAttribute("proyecto", proyecto);
		return null;
	}

	// VER PROYECTO
	@RequestMapping(value = "/verproyecto/verproyecto")
	public String verProyecto(@RequestParam Long id, Model model) {

		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		model.addAttribute("proyecto", proyecto);
		Integer horas = proyecto.getHorasAsignadas() - proyecto.getHorasUtilizadas();
		model.addAttribute("horasDisponibles", horas);
		return null;
	}

	// VER PROYECTO-USUARIOS
	@RequestMapping(value = "/verproyecto/usuarios/tablausuarios", method = RequestMethod.POST)
	public String loadUsuarios(@RequestParam Long id, Model model) {
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		model.addAttribute("proyecto", proyecto);
		return null;
	}
	
	@RequestMapping(value = "/verproyecto/usuarios/listarusuarios", method = RequestMethod.POST)
	public String listarUsuarios(@RequestParam Long idProyecto, Model model){
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("usuarios", proyecto.getUsuarios());
		model.addAttribute("idProyecto", idProyecto);
		return "/proyectos/verproyecto/usuarios/buscarusuarios";
	}
	
	@RequestMapping(value = "/verproyecto/usuarios/buscarusuarios", method = RequestMethod.POST)
	public String buscarUsuario(@RequestParam String nombreBuscado, @RequestParam Long idProyecto, Model model) {
		List<Usuario> usuarios = usuarioService.buscarUsuariosPorNombre(idProyecto, nombreBuscado);
		model.addAttribute("usuarios", usuarios);
		return null;
	}

	@RequestMapping(value = "/verproyecto/usuarios/infousuarios")
	public String verUsuario(@RequestParam Long id, Model model) {
		Usuario usuario = usuarioService.recuperarUsuarioPorId(id);
		model.addAttribute("usuario", usuario);

		return null;
	}

	// VER PROYECTO-TAREAS
	@RequestMapping(value = "/verproyecto/tareas/tablatareas", method = RequestMethod.POST)
	public String loadTareas(@RequestParam Long id, Model model) {
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		model.addAttribute("proyecto", proyecto);
		Integer horasRestantes = proyecto.getHorasAsignadas() - proyecto.getHorasUtilizadas();
		boolean horas;		
		if(horasRestantes>0){
			horas = true;
		}else{
			horas = false;
		}
		
		model.addAttribute("horasRestantes", horas);
		return null;
	}
	
	@RequestMapping(value = "/verproyecto/tareas/listartareas", method = RequestMethod.POST)
	public String listarTareas(@RequestParam Long idProyecto, Model model){
		model.addAttribute("tareas", proyectoService.recuperarProyectoPorId(idProyecto).getTareas());
		model.addAttribute("idProyecto", idProyecto);
		return "/proyectos/verproyecto/tareas/buscotareas";
	}
	
	@RequestMapping(value = "/verproyecto/tareas/buscotareas", method = RequestMethod.POST)
	public String buscarTarea(@RequestParam String tituloBuscado, @RequestParam Long idProyecto, Model model)  {
		List<Tarea> tareas = tareaService.buscarTareasPorNombre(idProyecto, tituloBuscado);
		model.addAttribute("tareas", tareas);
		model.addAttribute("idProyecto", idProyecto);
		return null;
	}

	@RequestMapping(value = "/verproyecto/tareas/infotareas")
	public String verTarea(@RequestParam Long id, Model model) {
		Tarea tarea = tareaService.recuperarTareaPorId(id);
		model.addAttribute("tarea", tarea);
		return null;
	}
	// FORM

	@RequestMapping(value = "/nuevoproyecto")
	public String nuevoProyecto(Model model) {
		model.addAttribute("proyectoForm", new ProyectoForm());
		model.addAttribute("usuariosPrincipales", usuarioService.recuperarUsuariosPrincipalesInactivos());
		model.addAttribute("usuarios", usuarioService.recuperarUsuariosInactivos());
		Integer horasRestantes = 0;
		model.addAttribute("horasRestantes", horasRestantes);
		return "/proyectos/form/form";
	}

	@RequestMapping(value = "/editarproyecto")
	public String editarProyecto(@RequestParam Long id, Model model) {

		Proyecto proyecto = proyectoService.recuperarProyectoPorId(id);
		ProyectoForm proyectoForm = new ProyectoForm();
		proyectoForm.setId(proyecto.getId());
		proyectoForm.setNombre(proyecto.getNombre());
		proyectoForm.setDescripcion(proyecto.getDescripcion());
		proyectoForm.setFechaInicio(proyecto.getFechaInicio());
		proyectoForm.setFechaFinalizacion(proyecto.getFechaFinalizacion());
		proyectoForm.setHorasAsignadas(proyecto.getHorasAsignadas().intValue());
		proyectoForm.setIdUsuarioPrincipal(proyecto.getUsuarioPrincipal().getId());

		List<Long> idUsuarios = proyecto.getUsuarios().stream().map((Usuario u) -> u.getId()).collect(Collectors.toList());
		proyectoForm.setIdUsuarios(idUsuarios);

		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.addAll(usuarioService.recuperarUsuariosInactivos());
		usuarios.addAll(proyecto.getUsuarios());
		
		List<Usuario> usuariosPrincipales = new ArrayList<Usuario>();
		usuariosPrincipales.addAll(usuarioService.recuperarUsuariosPrincipalesInactivos());
		usuariosPrincipales.add(proyecto.getUsuarioPrincipal());
		
		Integer horasRestantes = proyecto.getHorasUtilizadas();
		model.addAttribute("horasRestantes", horasRestantes);
		
		model.addAttribute("usuariosPrincipales", usuariosPrincipales);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("proyectoForm", proyectoForm);
		return "/proyectos/form/form";
	}

	@RequestMapping(value = "/guardarproyecto", method = RequestMethod.POST)
	public String guardarProyecto(@ModelAttribute("proyectoForm") ProyectoForm proyectoForm, Model model) {
		Long idFormulario = proyectoForm.getId();
		Long idUsuarioPrincipal = proyectoForm.getIdUsuarioPrincipal();
		List<Long> idUsuarios = proyectoForm.getIdUsuarios();
		Proyecto proyecto = new Proyecto();

		if (idFormulario != null) {
			proyecto = proyectoService.recuperarProyectoPorId(idFormulario);
			proyecto.setNombre(proyectoForm.getNombre());
			proyecto.setDescripcion(proyectoForm.getDescripcion());
			proyecto.setFechaInicio(proyectoForm.getFechaInicio());
			proyecto.setFechaFinalizacion(proyectoForm.getFechaFinalizacion());
			proyecto.setHorasAsignadas(Integer.valueOf(proyectoForm.getHorasAsignadas()));
			proyectoService.actualizarProyecto(proyecto, idUsuarioPrincipal, idUsuarios);
		} else {
			proyecto.setNombre(proyectoForm.getNombre());
			proyecto.setDescripcion(proyectoForm.getDescripcion());
			proyecto.setFechaInicio(proyectoForm.getFechaInicio());
			proyecto.setFechaFinalizacion(proyectoForm.getFechaFinalizacion());
			proyecto.setHorasAsignadas(Integer.valueOf(proyectoForm.getHorasAsignadas()));
			proyecto.setHorasUtilizadas(0);
			proyecto.setBorrado(false);
			idFormulario = proyectoService.guardarProyecto(proyecto, idUsuarioPrincipal, idUsuarios);
		}
		return "redirect:/proyectos/verproyecto/verproyecto.html?id=" + idFormulario;
	}
}