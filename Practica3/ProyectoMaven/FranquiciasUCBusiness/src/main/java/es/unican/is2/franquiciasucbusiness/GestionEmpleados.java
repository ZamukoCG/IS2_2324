package es.unican.is2.franquiciasucbusiness;

import es.unican.is2.franquiciasuccommon.*;


public class GestionEmpleados implements IGestionEmpleados {
	
	private ITiendasDAO itiendasDAO;
	private IEmpleadosDAO iempleadosDAO;

	public GestionEmpleados(ITiendasDAO itiendasDAO, IEmpleadosDAO iempleadosDAO) {

		this.itiendasDAO = itiendasDAO;
		this.iempleadosDAO = iempleadosDAO;
	}

	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
			if (iempleadosDAO.empleado(e.getDNI()) != null){
				throw new OperacionNoValidaException ("El empleado ya existe") ;
			}
			Tienda t = itiendasDAO.tiendaPorNombre(nombre);
			if (t != null) {
				iempleadosDAO.crearEmpleado(e);
				t.getEmpleados().add(e);
			} 
		return e;
	}

	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
        
		Empleado e =  iempleadosDAO.empleado(dni);
		Tienda t = itiendasDAO.tiendaPorNombre(nombre);
		if (t.buscaEmpleado(dni) == null){
			throw new OperacionNoValidaException ("El empleado no existe") ;
		}
		if (t != null) {
			iempleadosDAO.eliminarEmpleado(dni) ;
			t.getEmpleados().remove(e);
		} 
		return e;
	}

	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		
		boolean b = false;
		Empleado e =  iempleadosDAO.empleado(dni);
		
		if (e == null){
			throw new OperacionNoValidaException ("El empleado no existe") ;
		}
		
		Tienda t = itiendasDAO.tiendaPorNombre(actual);
		Tienda t2 = itiendasDAO.tiendaPorNombre(destino);
		
		if (t2 != null & t != null ) {
			t.getEmpleados().remove(e) ;
			t2.getEmpleados().add(e);
			b = true;
		}
		
		return b;
	}

	public Empleado empleado(String dni) throws DataAccessException {
		return iempleadosDAO.empleado(dni);
	}

}
