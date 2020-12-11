package com.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.models_entitys.Cliente;
import com.web.app.models_entitys.Prestamo;
import com.web.app.services.IAdminBancoService;
import com.web.app.services.IClienteService;
import com.web.app.services.IPrestamoService;

@Controller
@RequestMapping("/bd")
public class BaseDeDatosController {

	@Autowired
	private IAdminBancoService adminBancoS;
	@Autowired
	private IClienteService clienteS;
	@Autowired
	private IPrestamoService prestamoS;

	@PostMapping(path = "agregar")
	public String form(Cliente c, Model model) {

		if (clienteS.find(c.getIdCliente()) != null) {
			model.addAttribute("advertencia", "El ID que intentas poner ya existe");
			model.addAttribute("titulo", "Agregar Cliente");
			return "Administrador/agregarCliente";
		}
		clienteS.insert(c);
		model.addAttribute("titulo", "Agregar Cliente");
		model.addAttribute("advertencia", "Cliente agregado con exito");
		model.addAttribute("cliente", new Cliente());
		return "Administrador/agregarCliente";

	}
	
	@PostMapping(path = "agregarPrestamo")
	public String form(
			@RequestParam Integer idCliente ,
			Prestamo p, Model model) {
		p.setCliente(clienteS.find(idCliente));
        prestamoS.insert(p);
        
        if(p.getTipoPrestamo() <1||p.getTipoPrestamo() >3) {
			model.addAttribute("titulo", "Nuevo Prestamo");
			model.addAttribute("advertencia", "Solo hay 3 opciones para tipo de prestamo.");
			prestamoS.delete(p.getIdPrestamo());
			model.addAttribute("prestamo", new Prestamo());
			return "Administrador/darPrestamo";
		}
        if(p.getMonto() >= adminBancoS.totalBanco().getDineroBanco()) {
			model.addAttribute("titulo", "Nuevo Prestamo");
			model.addAttribute("advertencia", "Imposible darte el prestamo, el presupuesto del banco es menor o igual");
			prestamoS.delete(p.getIdPrestamo());
			model.addAttribute("prestamo", new Prestamo());
			return "Administrador/darPrestamo";
		}
        
        adminBancoS.bajarTotal(p.getMonto());  //fuuncion que rebaja el prestamo del dinero total
		model.addAttribute("titulo", "Nuevo Prestamo");
		model.addAttribute("advertencia", "Exito al crear prestamo");
		model.addAttribute("prestamo", new Prestamo());
		return "Administrador/darPrestamo";

	}
	
	@PostMapping(path = "solicitarPrestamo")
	public String form_solicitar_p(
			@RequestParam Integer idCliente ,
			Prestamo p, Model model) {
		p.setCliente(clienteS.find(idCliente));
        prestamoS.insert(p);
        
        if(p.getTipoPrestamo() <1||p.getTipoPrestamo() >3) {
			model.addAttribute("titulo", "Nuevo Prestamo");
			model.addAttribute("advertencia", "Solo hay 3 opciones para tipo de prestamo.");
			prestamoS.delete(p.getIdPrestamo());
			model.addAttribute("prestamo", new Prestamo());
			return "ClienteF/solicitaPrestamo";
		}
        if(p.getMonto() >= adminBancoS.totalBanco().getDineroBanco()) {
			model.addAttribute("titulo", "Nuevo Prestamo");
			model.addAttribute("advertencia", "Imposible darte el prestamo, el presupuesto del banco es menor o igual");
			prestamoS.delete(p.getIdPrestamo());
			model.addAttribute("prestamo", new Prestamo());
			return "ClienteF/solicitaPrestamo";
		}
        
        adminBancoS.bajarTotal(p.getMonto());  //fuuncion que rebaja el prestamo del dinero total
		model.addAttribute("titulo", "Nuevo Prestamo");
		model.addAttribute("advertencia", "Exito al crear prestamo");
		model.addAttribute("prestamo", new Prestamo());
		return "ClienteF/solicitaPrestamo";

	}

	@PostMapping("editar")
	public String editar(Cliente c, Model model) {
		clienteS.update(c);
		model.addAttribute("titulo", "Editar Cliente");
		model.addAttribute("advertencia", "Actualizacion con exito");
		model.addAttribute("clientes", clienteS.find(c.getIdCliente()));
		return "Administrador/editarCliente";

	}
	
	@PostMapping("editarPorCliente")
	public String editarPorCliente(Cliente c, Model model) {
		clienteS.update(c);
		model.addAttribute("titulo", "Edita tu informacion");
		model.addAttribute("advertencia", "Actualizacion con exito");
		model.addAttribute("clientes", clienteS.find(c.getIdCliente()));
		return "ClienteF/editarCliente";

	}
	
	
	@PostMapping("abonarPrestamo")
	public String abonarPrestamo(@RequestParam float montoAbonado,
			Prestamo p, Model model) {
		
		if (prestamoS.find(p.getIdPrestamo()) == null ) {
			model.addAttribute("advertencia", "El ID de usuario no existe o no tiene prestamo");
			model.addAttribute("titulo", "Abonar al prestamo");
			model.addAttribute("prestamo", new Prestamo());
			return "Administrador/abonarPrestamo";
		}
		if(prestamoS.find(p.getIdPrestamo()).getMonto() == 0 ) {
			model.addAttribute("advertencia", "Este prestamo ya ha sido pagado, consulte los prestamos activos para abonar");
			model.addAttribute("titulo", "Abonar al prestamo");
			model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
			return "Administrador/abonarPrestamo";
		}
		if(prestamoS.find(p.getIdPrestamo()).getMonto() < montoAbonado ) {
			model.addAttribute("advertencia", "Su abono excede el total del adeudo.");
			model.addAttribute("titulo", "Abonar al prestamo");
			model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
			return "Administrador/abonarPrestamo";
		}
		
		
		prestamoS.updateMonto(p.getIdPrestamo(), montoAbonado); //aqui rebajo el prestamo dependiendo el abono
		model.addAttribute("titulo", "Abonar ");
		model.addAttribute("advertencia", "Actualizacion con exito");
		model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
		return "Administrador/abonarPrestamo";

	}
	
	@PostMapping("abonarPrestamoC/{idPrestamo}")
	public String abonarPrestamoC(@RequestParam Integer idPrestamo,@RequestParam float montoAbonado,
			Prestamo p, Model model) {
		
		if(prestamoS.find(p.getIdPrestamo()).getMonto() == 0 ) {
			model.addAttribute("advertencia", "Este prestamo ya ha sido pagado, consulte los prestamos activos para abonar");
			model.addAttribute("titulo", "Abonar al prestamo");
			model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
			return "ClienteF/abonarPrestamoC";
		}
		if(prestamoS.find(p.getIdPrestamo()).getMonto() < montoAbonado ) {
			model.addAttribute("advertencia", "Su abono excede el total del adeudo.");
			model.addAttribute("titulo", "Abonar a mi prestamo");
			model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
			return "ClienteF/abonarPrestamoC";
		}
		
		//Esta funcion es diferente a la de el admin porque consideramos que a un admin se le paga en efectivo y en esta consideramos que lo paga desde su cuenta.
		//Restamos el abono de su cuenta.
		clienteS.updateMontoC(prestamoS.find(idPrestamo).getCliente().getIdCliente() , montoAbonado);
		prestamoS.updateMonto(p.getIdPrestamo(), montoAbonado); //aqui rebajo el prestamo dependiendo el abono
		model.addAttribute("titulo", "Abonar a mi credito");
		model.addAttribute("advertencia", "Actualizacion con exito");
		model.addAttribute("prestamo", prestamoS.find(p.getIdPrestamo()));
		return "ClienteF/abonarPrestamoC";

	}

	@GetMapping(path = "eliminar/{idCliente}")
	public String eliminar(@PathVariable Integer idCliente, Model model) {
		clienteS.delete(idCliente);
		model.addAttribute("titulo", "Opciones Cliente");
		model.addAttribute("clientes", clienteS.findAll());
		return "Administrador/listaClientes";

	}

	@GetMapping(path = "opcionesCliente")
	public String listar(Model model) {
		model.addAttribute("titulo", "Opciones Cliente");
		model.addAttribute("clientes", clienteS.findAll());
		return "Administrador/listaClientes";

	}

	@GetMapping(path = "opcionesPrestamo")
	public String verPrestamos(Model model) {
		model.addAttribute("titulo", "Opciones prestamos");
		model.addAttribute("prestamos", prestamoS.findAllJoin());
		return "Administrador/listaPrestamos";
	}

	@PostMapping(path = "buscarId")
	public String buscarId(Integer idCliente, Model model) {
		if (idCliente == null || idCliente < 0) {
			model.addAttribute("titulo", "Ingresaste algo mal");
			model.addAttribute("cliente", new Cliente());
			return "Administrador/clientesPorID";
		}
		model.addAttribute("titulo", "Cliente por ID");
		model.addAttribute("clientes", clienteS.find(idCliente));
		return "Administrador/ResultadosDeBusquedaClientes";

	}
	
	@GetMapping(path = "buscarIdCliente")
	public String buscarIdCliente(@RequestParam Integer idCliente, Model model) {
		if (clienteS.find(idCliente) == null || idCliente < 0) {
			model.addAttribute("titulo", "Ingresaste algo mal o no estas registrado");
			model.addAttribute("cliente", new Cliente());
			return "ClienteF/validarCliente";
		}
		model.addAttribute("titulo", "Tu informacion");
		model.addAttribute("clientes", clienteS.find(idCliente));
		return "ClienteF/opcionesCliente";

	}

	@PostMapping(path = "buscarNombre")
	public String buscarNombre(String nombre, Model model) {

		if (nombre == "" || nombre == null) {
			model.addAttribute("titulo", "Ingresaste algo mal");
			model.addAttribute("cliente", new Cliente());
			return "Maestro/busquedaNom";
		}
		model.addAttribute("titulo", "Cliente por nombre");
		model.addAttribute("clientes", clienteS.findName(nombre));
		return "Administrador/ResultadosDeBusquedaClientes";

	}
	
	@PostMapping(path = "buscarPrestamoId")
	public String buscarPrestamoId(@RequestParam Integer idCliente, Model model) {
		if (idCliente == null || idCliente < 0) {
			model.addAttribute("titulo", "Ingresaste algo mal");
			model.addAttribute("prestamo", new Prestamo());
			return "Administrador/prestamosPorIdCliente";
		}
		model.addAttribute("titulo", "Prestamo por ID cliente");
		model.addAttribute("prestamos", prestamoS.findIdCliente(idCliente));
		return "Administrador/ResultadosDeBusquedaPrestamos";

	}
	
	@PostMapping(path = "buscarPorFecha")
	public String buscarPorFecha(String fechaInicio,String fechaFin, Model model) {
		
		model.addAttribute("titulo", "Prestamo por fecha");
		model.addAttribute("prestamos", prestamoS.findInDates(fechaInicio , fechaFin));  //aqui va la funcion que busca rango de fechas
		return "Administrador/ResultadosDeBusquedaPrestamos";

	}
	
	@GetMapping(path = "prestamosPagados")
	public String prestamosPagados(Model model) {
		model.addAttribute("titulo","Prestamos Pagados");
		model.addAttribute("prestamos", prestamoS.PrestamosPagados());
		return "Administrador/ResultadosDeBusquedaPrestamos";
	}
	
	@GetMapping(path = "prestamosActivos")
	public String prestamosActivos(Model model) {
		model.addAttribute("titulo","Prestamos Activos");
		model.addAttribute("prestamos", prestamoS.PrestamosActivos());
		return "Administrador/ResultadosDeBusquedaPrestamos";
	}
	
	@GetMapping(path = "montoTotalEnBanco")
	public String montoTotalBanco(Model model) {
		model.addAttribute("titulo","Monto total en banco");
		model.addAttribute("adminBanco",adminBancoS.totalBanco());
		return "Administrador/montoTotalEnBanco";
	}
	
	@GetMapping(path = "clienteConMasDinero")
	public String clienteConMasDinero(Model model) {
		model.addAttribute("titulo","Cliente con mas dinero");
		model.addAttribute("clientes",clienteS.findMayor());
		return "Administrador/clienteConMasDinero";
	}
	
	@GetMapping(path = "retirarMonto")
	public String retirarMonto(@RequestParam Integer idCliente ,
			@RequestParam float montoRetirado,
			Model model) {
		
		if(montoRetirado <= clienteS.find(idCliente).getMonto()) {
			clienteS.updateMontoC(idCliente , montoRetirado);
			model.addAttribute("tituloAdevertencia","Retiro con exito.");
		}else {
			model.addAttribute("tituloAdevertencia","No te alcanza para retirar esa cantidad.");
		}
		
		model.addAttribute("titulo","Retirar de mi cuenta");
		model.addAttribute("idCliente",idCliente);
		return "ClienteF/retirarMonto";
	}
}
