package edu.curso.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.curso.java.bo.Proyecto;
import edu.curso.java.bo.Tarea;
import edu.curso.java.bo.Usuario;

@Repository
public class UsuarioDAOImp extends GenericDAOImp<Usuario, Long> implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Usuario> recuperarUsuariosInactivos() {
		String hql = "from Usuario as u where u.activo = false AND u.borrado = false  AND u.projectManager = false order by u.nombreCompleto";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public List<Usuario> recuperarUsuariosPrincipalesInactivos() {
		String hql = "from Usuario as u where u.activo = false AND u.borrado = false  AND u.projectManager = true order by u.nombreCompleto";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<Usuario> buscarUsuariosPorNombre(Long idProyecto, String nombreCompleto){
		String sql = "select * FROM proyecto_usuario as p inner join usuario as u on p.usuarios_id = u.id where  u.borrado = false AND p.Proyecto_id = :idProyecto AND u.projectManager = false AND u.nombreCompleto like :nombreCompleto order by u.nombreCompleto";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.addEntity(Usuario.class);
		query.setLong("idProyecto",idProyecto);
		query.setString("nombreCompleto","%" + nombreCompleto + "%");		
		return query.list();
		
	}

	@Override
	public List<Usuario> buscarUsuariosPorNombre(String nombre) {
		String hql = "from Usuario as u WHERE u.borrado = false AND u.nombreCompleto LIKE :nombre ORDER BY u.nombreCompleto";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("nombre", "%" + nombre + "%");
		return query.list();
	}
	
}
