package es.unican.is2.franquiciasucbusiness;

import es.unican.is2.franquiciasuccommon.*;
import es.unican.is2.franquiciasucdao.EmpleadosDAO;
import es.unican.is2.franquiciasucdao.TiendasDAO;

public class GestionEmpleados implements IGestionEmpleados {

	public GestionEmpleados(TiendasDAO tiendasDAO, EmpleadosDAO empleadosDAO) {
		// TODO Auto-generated constructor stub
	}

	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	public Empleado empleado(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
