
package es.unican.is2.franquiciasuccommon;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TiendaiTest {
	
	@Test
	void testTienda() {
		Tienda t = new Tienda("LaVersacce","Barcelona");
		assertEquals(t.getNombre(),"LaVersacce"); 
		assertEquals(t.getDireccion(),"Barcelona");
		assertThrows(NullPointerException.class, () -> new Tienda(null,"Barcelona"));
		assertThrows(NullPointerException.class, () -> new Tienda("LaVersacce",null));
	}
	@Test
	void testGastoMensual() throws OperacionNoValidaException {
		
		Tienda t = new Tienda("LaVersacce","Barcelona");
		t.gastoMensualSueldos();
		assertEquals(0.0,t.gastoMensualSueldos());
		Empleado em1 = new Empleado("72181360T","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		Empleado em2 = new Empleado("72181360","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		Empleado em3 = new Empleado("7218136","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		Empleado em4 = new Empleado("721813","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		Empleado em5 = new Empleado("72181","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		Empleado em6 = new Empleado("7218","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		t.getEmpleados().add(em1);
		t.getEmpleados().add(em2);
		t.getEmpleados().add(em3);
		t.getEmpleados().add(em4);
		t.getEmpleados().add(em5);
		t.getEmpleados().add(em6);
		assertEquals(6000.0,t.gastoMensualSueldos());		
	}

}
