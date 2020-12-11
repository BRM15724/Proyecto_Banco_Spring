package com.web.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.models_entitys.Cliente;
import com.web.app.services.IClienteService;

@Repository
public class AdminBancoImpl implements AdminBancoDAO {

	AdminBanco ab = new AdminBanco();
	@Autowired
	private IClienteService clienteS;

	@Override
	@Transactional
	public AdminBanco totalBanco() {
		
		// ESTE ES EL SALDO INICIAL DEL BANCO
		float total = 1000000;
		for (Cliente cliente : clienteS.findAll()) {
			total = total + cliente.getMonto();
		}

		ab.setDineroBanco(total);
		return ab;
	}

	@Override
	@Transactional
	public void aumentarTotal(float monto) {
		
		ab.setDineroBanco(ab.getDineroBanco()+monto);

	}
	
	@Override
	@Transactional
	public void bajarTotal(float monto) {
		
		totalBanco().setDineroBanco(totalBanco().getDineroBanco()-monto);

	}
	


}
