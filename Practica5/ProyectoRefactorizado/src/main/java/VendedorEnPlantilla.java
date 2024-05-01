public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;

	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) { //WC + 1
		super(nombre, id, dni); 
		this.tipo = tipo;

	}
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @return tipo del vendedor
	 */
	public TipoVendedor getTipo() { //WC + 1
		return tipo;
	}
	

	@Override
	public boolean equals(Object obj) { //WC + 1
		if (!(obj instanceof VendedorEnPlantilla))  //WC + 1 //CCOG + 1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
