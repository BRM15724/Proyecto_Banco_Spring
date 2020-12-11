package com.web.app.repositories;


import java.util.List;

import com.web.app.models_entitys.Cliente;
import com.web.app.models_entitys.Prestamo;


public interface ClienteDAO {
	
	
	void insert (Cliente c);
	Cliente find(Integer idCliente);
	List<Cliente>findAll();
	void update(Cliente c);
	void delete(Integer idCliente);
	List<Cliente> findName(String nombre);
	List<Cliente> findMayor();
	public void updateMontoC(Integer idCliente, float monto);
}
