package com.web.app.repositories;

import com.web.app.models_entitys.AdminBanco;
import com.web.app.models_entitys.Cliente;

public interface AdminBancoDAO {

	AdminBanco totalBanco();

	void aumentarTotal(float monto);

	void bajarTotal(float monto);

}
