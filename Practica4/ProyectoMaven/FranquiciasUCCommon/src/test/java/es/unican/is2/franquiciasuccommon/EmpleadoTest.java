package es.unican.is2.franquiciasuccommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EmpleadoTest {

	@Test
	void testEmpleado() throws OperacionNoValidaException {
		 Empleado em = new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.of(2018, 1, 23));

		 assertEquals(em.getCategoria(),Categoria.ENCARGADO);
		 assertEquals(em.getFechaContratacion(),LocalDate.of(2018, 1, 23));
		 assertThrows(NullPointerException.class, () -> new Empleado(null,"Mbappe",Categoria.ENCARGADO,LocalDate.now()));
		 assertThrows(NullPointerException.class, () -> new Empleado("72181360T",null,Categoria.ENCARGADO,LocalDate.now()));
		 //assertThrows(NullPointerException.class, () -> new Empleado("72181360T","Mbappe",SEGURIDAD,LocalDate.now()));
		 assertThrows(NullPointerException.class, () -> new Empleado("72181360T","Mbappe",null,LocalDate.now()));
		 assertThrows(NullPointerException.class, () -> new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,null));
		 assertThrows(OperacionNoValidaException.class, () -> new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.of(2030, 1, 23)));
	}
	@Test
	void testSueldoBruto() throws OperacionNoValidaException {
		//Auxiliar,true ,hoy
		Empleado em1 = new Empleado("72181360T","Mbappe",Categoria.AUXILIAR,LocalDate.now());
		em1.darDeBaja();
		assertEquals(750.0,em1.sueldoBruto());
		//Auxiliar,false ,hoy - 2 años
		Empleado em2 = new Empleado("72181360T","Mbappe",Categoria.AUXILIAR,LocalDate.now().minusYears(2));
		assertEquals(1000.0,em2.sueldoBruto());
		//VENDEDOR,false ,hoy - 5 años
		Empleado em3 = new Empleado("72181360T","Mbappe",Categoria.VENDEDOR,LocalDate.now().minusYears(5));
		em3.darDeBaja();
		assertEquals(1125.0,em3.sueldoBruto());
		//VENDEDOR,false ,hoy - 5 años - 1 dia
		Empleado em4 = new Empleado("72181360T","Mbappe",Categoria.VENDEDOR,LocalDate.now().minusYears(5).minusDays(1));
		assertEquals(1550.0,em4.sueldoBruto());
		//encargado,true ,hoy - 7
		Empleado em5 = new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.now().minusYears(7));
		em5.darDeBaja();
		assertEquals(1537.50,em5.sueldoBruto());
		//encargado,false ,hoy - 10
		Empleado em6 = new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.now().minusYears(10));
		assertEquals(2050.0,em6.sueldoBruto());
		//auxiliar,true ,hoy - 10 años - 1 dia
		Empleado em7 = new Empleado("72181360T","Mbappe",Categoria.AUXILIAR,LocalDate.now().minusYears(10).minusDays(1));
		em7.darDeBaja();
		assertEquals(825.0,em7.sueldoBruto());
		//auxiliar,false ,hoy - 15 años
		Empleado em8 = new Empleado("72181360T","Mbappe",Categoria.AUXILIAR,LocalDate.now().minusYears(15));
		assertEquals(1100.0,em8.sueldoBruto());
		//vendedor,true ,hoy - 20 años
		Empleado em9 = new Empleado("72181360T","Mbappe",Categoria.VENDEDOR,LocalDate.now().minusYears(20));
		em9.darDeBaja();
		assertEquals(1200.0,em9.sueldoBruto());
		//vendedor,FALSE ,hoy - 20 años -1 DIA
		Empleado em10 = new Empleado("72181360T","Mbappe",Categoria.VENDEDOR,LocalDate.now().minusYears(20).minusDays(1));
		assertEquals(1700.0,em10.sueldoBruto());
		//vendedor,FALSE ,hoy - 20 años -1 DIA
		Empleado em11 = new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.now().minusYears(40));
		em11.darDeBaja();
		assertEquals(1650.0,em11.sueldoBruto());
		
		//Casos de prueba no válidos
		//Empleado em12 = new Empleado("72181360T","Mbappe",Categoria.SEGURIDAD,LocalDate.now().minusYears(40));

	}

}