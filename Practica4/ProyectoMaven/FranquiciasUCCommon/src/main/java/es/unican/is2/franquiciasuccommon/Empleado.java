package es.unican.is2.franquiciasuccommon;




import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 * @throws OperacionNoValidaException 
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws OperacionNoValidaException {
		if (DNI ==null || nombre == null || categoria == null || fechaContratacion == null) {
			throw new NullPointerException();
		}
		if (fechaContratacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("La fecha de contratación es mayor que la fecha actual");
		}
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		 
			Categoria cat = this.categoria;
			double sueldo = 0;
			if (cat == Categoria.AUXILIAR) {
				sueldo = 1000;
			} else if (cat == Categoria.VENDEDOR) {
				sueldo = 1500;
			} else if (cat == Categoria.ENCARGADO) {
				sueldo = 2000;
			}
			
			LocalDate fechaAhora = LocalDate.now();
			double comp = 0;
			
			if (fechaAhora.minusYears(20).isAfter(fechaContratacion)) {
				comp = 200;
			} else if (fechaAhora.minusYears(10).isAfter(fechaContratacion)) {
				comp = 100;
			} else if (fechaAhora.minusYears(5).isAfter(fechaContratacion)) {
				comp = 50;
			}
			
			double sueldoBruto= comp + sueldo;
			if(baja) {
				sueldoBruto = sueldoBruto * 0.75;
			}
		return sueldoBruto;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
