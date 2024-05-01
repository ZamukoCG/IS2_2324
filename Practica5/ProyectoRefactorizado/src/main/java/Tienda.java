import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import fundamentos.Lectura;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;
	static final double COMISION_JUNIOR = 0.005;
	static final double COMISION_SENIOR = 0.01;
	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { //WMC + 1
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String getDireccion() { //WMC + 1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String getNombre() { //WMC + 1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException { //WMC + 1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { //WMC + 1 // CCOG + 1
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException { //WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) { //WMC + 1 // CCOG + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {//WMC + 1 
		Vendedor v = buscaVendedor(id);
		if (v == null) { //WMC + 1 // CCOG + 1
			return false;
		}
		double comision = 0;
		if (v instanceof VendedorEnPlantilla) { //WMC + 1 // CCOG + 1
			switch (((VendedorEnPlantilla) v).getTipo()) { // CCOG + 2
			case Junior: //WMC + 1
				comision = importe * COMISION_JUNIOR;
				break;
			case Senior: //WMC + 1
				comision = importe * COMISION_SENIOR; 
				break;
			}
		}
		v.anhadeVenta(importe);
		v.setComision(v.getComision()+comision);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException { //WMC + 1

		lista = new LinkedList<Vendedor>();
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) { //WMC + 2 // CCOG + 2

				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Senior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Practicas")) { //WMC + 2 // CCOG + 2
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Junior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			while (in.hasNext()) { //WMC + 1 // CCOG + 1
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) { //WMC + 1 // CCOG + 1
			throw new DataAccessException();
		} finally {
			if (in != null) { //WMC + 1 // CCOG + 1
				in.close();
			}
		} // try

		for (Vendedor v : lista) { //WMC + 1 // CCOG + 1
			if (v.getId().equals(id)) { //WMC + 1 // CCOG + 2
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException { //WMC + 1
		lista = new LinkedList<Vendedor>();

		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) { //WMC + 2 // CCOG + 2
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Senior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Practicas")) { //WMC + 2 // CCOG + 2
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Junior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			while (in.hasNext()) { //WMC + 1 // CCOG + 1
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) { //WMC + 1 // CCOG + 1
			throw new DataAccessException();
		} finally {
			if (in != null) { //WMC + 1 // CCOG + 1
				in.close();
			}
		} // try

		return lista;

	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException { //WMC + 1
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) { //WMC + 1 // CCOG + 1
			if (v instanceof VendedorEnPracticas) { //WMC + 1 // CCOG + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) { //WMC + 1 // CCOG + 1
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.getTipo().equals(TipoVendedor.Junior)) //WMC + 1 // CCOG + 3
					junior.add(vp);
				else // CCOG + 1
					senior.add(vp);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) { //WMC + 1 // CCOG + 1
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.getDni()
						+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getComision());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) { //WMC + 1 // CCOG + 1
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v2.getNombre() + " Id: " + v2.getId() + " DNI: " + v2.getDni()
						+ " TotalVentasMes: " + v2.getTotalVentas() + " TotalComision: "+ v2.getComision());
			}
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) { //WMC + 1 // CCOG + 1
				VendedorEnPracticas v3 = (VendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) { //WMC + 1 // CCOG + 1
			throw new DataAccessException();

		} finally {
			if (out != null) //WMC + 1 // CCOG + 1
				out.close();
		}
	}
	
	
	/**
	 * Da la lista de los vendedores(Refactorización);
	 */
	
	public String vendedoresLista() { //WMC + 1
		String msj;
		List<Vendedor> vendedores;
		try {
			vendedores = this.vendedores();
			System.out.println(vendedores.size());
			Collections.sort(vendedores, new Comparator<Vendedor>() {
				public int compare(Vendedor o1, Vendedor o2) { //WMC + 1 
					if (o1.getTotalVentas() > o2.getTotalVentas()) //WMC + 1 // CCOG + 1
						return -1;
					else if (o1.getTotalVentas() < o2.getTotalVentas()) //WMC + 1 // CCOG + 1
						return 1;
					return 0;
				}
			});
			msj = "Todo correcto";
			for (Vendedor vn : vendedores) { //WMC + 1 // CCOG + 1
				msj += vn.getNombre() + " (" + vn.getId()+ ") "+vn.getTotalVentas() + "\n";
			}
			return msj;
		
		} catch (DataAccessException e) { //WMC + 1 // CCOG + 1
			msj =  "No se pudo acceder a los datos";
			return msj;
		}
		
	}
	/**
	 * Da el vendedor del mes(Refactorización);
	 */
	public String vendedorMes() { //WMC + 1
	
		String msj;
		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		try {
			vendedores = this.vendedores();
			resultado = new LinkedList<Vendedor>();
			double maxVentas = 0.0;
			for (Vendedor v : vendedores) { //WMC + 1 // CCOG + 1
				if (v.getTotalVentas() > maxVentas) { //WMC + 1 // CCOG + 2
					maxVentas = v.getTotalVentas();
					resultado.clear();
					resultado.add(v);
				} else if (v.getTotalVentas() == maxVentas) { //WMC + 1 // CCOG + 1
					resultado.add(v);
				}
			}

			msj = "Todo correcto";
			for (Vendedor vn : resultado) { //WMC + 1 // CCOG + 1
				msj += vn.getNombre() + "\n";
			}
			
			return msj;
		} catch (DataAccessException e) { //WMC + 1 // CCOG + 1
			msj =  "No se pudo acceder a los datos";
			return msj;
		}
	}
    
	
	/**
	 * Gestiona la nueva venta(Refactorización);
	 */
	public String nuevaVenta() { //WMC + 1
		Lectura lect;
		String dni;
		String msj = "Todo correcto"; 
		lect = new Lectura("Datos Venta");
		lect.creaEntrada("ID Vendedor", "");
		lect.creaEntrada("Importe", "");
		lect.esperaYCierra();
		dni = lect.leeString("ID Vendedor");
		double importe = lect.leeDouble("Importe");
		try {
			if (!this.anhadeVenta(dni, importe)) { //WMC + 1 // CCOG + 1
				msj = "El vendedor no existe" ;
				
			}
			return msj;
		} catch (DataAccessException e) { //WMC + 1 // CCOG + 1
			msj = "No se pudo guardar el cambio" ;
			return msj;
		}
	
		
	}

}
