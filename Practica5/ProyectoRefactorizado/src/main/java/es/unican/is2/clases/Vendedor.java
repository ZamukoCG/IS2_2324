package es.unican.is2.clases;
/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private double comision;
	private double totalVentas;
	private String dni;
	
	public Vendedor(String nombre, String id,String dni) { //WC + 1
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() { //WC + 1
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() { //WC + 1
		return id;
	}
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getComision() { //WC + 1
		return comision;
	}
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setComision(double value) { //WC + 1
		this.comision = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) { //WC + 1
		return totalVentas;
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) { //WC + 1
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public void anhadeVenta(double importe)  { //WC + 1
		totalVentas += importe;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni del vendedor
	 */	
	public String getDni() { //WC + 1
		return dni;
	}
}
