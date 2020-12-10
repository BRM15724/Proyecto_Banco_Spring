package com.web.app.services;

import com.web.app.models_entitys.AdminBanco;

public interface IAdminBancoService {
	
	AdminBanco totalBanco();
	void bajarTotal(float monto);
}
