package com.web.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.repositories.AdminBancoDAO;
import com.web.app.repositories.ClienteDAO;

@Component
public class AdminBancoService implements IAdminBancoService {
	
	@Autowired
	private AdminBancoDAO adminBancoDAO;

	@Override
	public AdminBanco totalBanco() {
		
		return adminBancoDAO.totalBanco();
	}

	@Override
	public void bajarTotal(float monto) {
		adminBancoDAO.bajarTotal(monto);
		
	}

	
	

}
