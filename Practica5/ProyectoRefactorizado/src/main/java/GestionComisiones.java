

import fundamentos.Menu;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones { 

	/**
	 * Programa principal basado en menu
	 */
	private static final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;
	
	public static void main(String[] args) { //WMC + 1

		// crea la tienda
		Tienda tienda = new Tienda("C:\\temp\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anhadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) { //WMC + 1 // CCOG + 1
			opcion = menu.leeOpcion();
			String msj;
			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { // CCOG + 2
			case NUEVA_VENTA: //WMC + 1
				msj = tienda.nuevaVenta();
				mensaje("NuevaVenta",msj);
				break;

			case VENDEDOR_DEL_MES: //WMC + 1
				msj = tienda.vendedorMes();
				mensaje("VendedorDelMes", msj);
				break;

			case VENDEDORES: //WMC + 1
				msj = tienda.vendedoresLista();
				mensaje("Vendedores", msj);
				break;
			}
		}
	}

	
	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { //WMC + 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
