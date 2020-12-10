package com.web.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.models_entitys.Cliente;
import com.web.app.repositories.ClienteDAO;
@Component
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Override
	public void insert(Cliente c) {
		
		clienteDAO.insert(c);
		
	}

	@Override
	public Cliente find(Integer id) {
		
		return clienteDAO.find(id);
	}

	@Override
	public List<Cliente> findAll() {
		
		return clienteDAO.findAll();
	}

	
	@Override
	public void delete(Integer id) {
		
		clienteDAO.delete(id);
		
	}

	@Override
	public void update(Cliente c) {
		clienteDAO.update(c);
		
	}

	@Override
	public List<Cliente> findName(String nombre) {
	
		return clienteDAO.findName(nombre);
	}

	@Override
	public List<Cliente> findMayor() {
		
		return clienteDAO.findMayor();
	}

}
