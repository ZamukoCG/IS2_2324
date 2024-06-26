package es.unican.is2.franquiciasucdao;
import java.sql.ResultSet;

import java.sql.SQLException;

import es.unican.is2.franquiciasuccommon.DataAccessException;
import es.unican.is2.franquiciasuccommon.Empleado;
import es.unican.is2.franquiciasuccommon.Categoria;

/**
 * Clase de utilidad que mapea filas de la base de datos a objetos 
 * de tipo Empleado
 */
public class EmpleadoMapper {

	/**
	 * Mtodo privado de apoyo. Recibe un ResultSet de un empleado
	 * y devuelve un objeto Empleado con los datos del ResultSet
	 * @param results Fila resultado de una consulta en base de datos
	 * @return Empleado
	 */
	public static Empleado toEmpleado(ResultSet results) throws DataAccessException {

		Empleado emp = new Empleado();
		try {
			emp.setDNI(results.getString("dni"));
			emp.setNombre(results.getString("nombre"));
			emp.setBaja(results.getBoolean("baja"));
			emp.setFechaContratacion(results.getDate("fechaContratacion").toLocalDate());
			emp.setCategoria(Categoria.valueOf(results.getString("categoria")));
		}
		catch (SQLException e) {
			throw new DataAccessException();
		}
		return emp;
	}
}
