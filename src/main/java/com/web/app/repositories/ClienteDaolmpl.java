package com.web.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.models_entitys.Cliente;

@Repository
public class ClienteDaolmpl implements ClienteDAO {

	
	@Autowired
	private EntityManager em;  
	                                 
	
	@Override
	@Transactional                 
	public void insert(Cliente c) {
		
	     em.persist(c);
	   
  
	}

	@Override
	@Transactional
	public Cliente find(Integer id) {
		
		return em.find(Cliente.class, id);
	}
	
	@Override
	@Transactional
	public List<Cliente> findMayor() {
		
		float mayor=0;
		
		for(Cliente cliente: findAll()) {
			
			if(mayor < cliente.getMonto()) {
				mayor=cliente.getMonto();
				
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Cliente> resultado = em.createQuery("SELECT c FROM Cliente c WHERE c.monto=:monto")
				.setParameter("monto", mayor)
				.getResultList();
		
		return resultado;
	}
		
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {

		
		return em.createQuery("from Cliente order by id asc",Cliente.class).getResultList();
		
	}

	@Override
	public void update(Cliente c) {
		
		Cliente cambio = find(c.getId());
		cambio.setNombre(c.getNombre());
		cambio.setTelefono(c.getTelefono());
		cambio.setCorreo(c.getCorreo());
		cambio.setDireccion(c.getDireccion());
		cambio.setMonto(c.getMonto());
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		Cliente eliminar = find(id);
		em.remove(eliminar);
		
	}
	
	@Override
	@Transactional
	public List<Cliente> findName(String nombre) {
		
		@SuppressWarnings("unchecked")
		List<Cliente> resultado = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre=:nombre order by nombre asc")
				.setParameter("nombre", nombre)
				.getResultList();
		return  resultado;
	}

}
