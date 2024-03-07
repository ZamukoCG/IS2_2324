package es.unican.is2.franquiciasucbusiness;



import es.unican.is2.franquiciasuccommon.*;


public class GestionTiendas implements IGestionTiendas {

	
	private ITiendasDAO tiendasDAO;
	public GestionTiendas(ITiendasDAO tiendasDAO) {
		this.tiendasDAO = tiendasDAO;
	}

	public Tienda nuevaTienda(Tienda t) throws DataAccessException {

			if (tiendasDAO.tiendaPorNombre(t.getNombre()) != null) {
				return null;
			} 
			tiendasDAO.crearTienda(t);
		return t;
	}

	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
			
			Tienda t = tiendasDAO.tiendaPorNombre(nombre);
			if ( t == null) {
				return null;
			}
			if (t.getEmpleados().isEmpty()) {
				tiendasDAO.eliminarTienda(t.getId());
				
			} else   {
			
				throw new OperacionNoValidaException("Error de que hay empleados");
				
			}

		return t;
	}

	public Tienda tienda(String nombre) throws DataAccessException {
		// TODO Auto-generated method stub
		   Tienda t = tiendasDAO.tiendaPorNombre(nombre);
		return t;
	}

}
