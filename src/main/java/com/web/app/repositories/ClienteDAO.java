package com.web.app.repositories;


import java.util.List;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.models_entitys.Cliente;


public interface ClienteDAO {
	
	
	void insert (Cliente c);
	Cliente find(Integer id);
	List<Cliente>findAll();
	void update(Cliente c);
	void delete(Integer id);
	List<Cliente> findName(String nombre);
	List<Cliente> findMayor();

}
