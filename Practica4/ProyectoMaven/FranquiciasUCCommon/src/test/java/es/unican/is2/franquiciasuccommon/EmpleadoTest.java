package es.unican.is2.franquiciasuccommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EmpleadoTest {

	@Test
	void testEmpleado() {
		 Empleado em = new Empleado("72181360T","Mbappe",Categoria.ENCARGADO,LocalDate.of(2018, 1, 23));
		 assertEquals(em.getDNI(),"72181360T");
		 assertEquals(em.getNombre(),"Mbappe");
		
		 assertEquals(em.getCategoria(),Categoria.ENCARGADO);
		 assertEquals(em.getFechaContratacion(),LocalDate.of(2018, 1, 23));
	}

}