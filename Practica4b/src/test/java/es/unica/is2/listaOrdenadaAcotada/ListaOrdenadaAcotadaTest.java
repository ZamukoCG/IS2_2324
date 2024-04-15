package es.unica.is2.listaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class ListaOrdenadaAcotadaTest {

	@Test
	void testaddGetSize() {
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(3);
		ListaOrdenadaAcotada<Integer> listaErronea = null;
		//Casos Pruebas válidas
		assertThrows(IndexOutOfBoundsException.class,() -> lista.get(0));
		lista.add(1);
		assertEquals(lista.get(0),1);
		lista.size();
		lista.add(3);
		assertEquals(lista.get(1),3);
		lista.add(2);
		assertEquals(lista.get(1),2);
		lista.size();
		assertEquals(lista.get(0),1);
		//Casos de Prueba No Válidos
		//Lista llena
		//Get
		assertThrows(IndexOutOfBoundsException.class,() -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class,() -> lista.get(10));
		assertThrows(NullPointerException.class,() -> listaErronea.get(10));
		//ADD
		assertThrows(IllegalStateException.class,() -> lista.add(4));
		assertThrows(NullPointerException.class,() -> listaErronea.add(4));
		lista.clear();
		assertThrows(NullPointerException.class,() -> lista.add(null));
		//Size
		assertThrows(NullPointerException.class,() -> listaErronea.size());
	}
	
	
	@Test
	void testRemove() {
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(3);
		ListaOrdenadaAcotada<Integer> listaErronea = null;
		//Casos Pruebas válidas
		assertThrows(IndexOutOfBoundsException.class,() -> lista.get(0));
		lista.add(1);
		assertEquals(lista.remove(0),1);
		lista.add(1);
		lista.add(2);
		lista.add(3);
		assertEquals(lista.remove(0),1);
		lista.add(1);
		assertEquals(lista.remove(1),2);
		lista.add(2);
		assertEquals(lista.remove(2),3);
		//Casos de Prueba No Válidos
		//Lista llena

		assertThrows(IndexOutOfBoundsException.class,() -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class,() -> lista.remove(10));
		assertThrows(NullPointerException.class,() -> listaErronea.get(10));
		
	}
	@Test
	void testClear() {
		ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(3);
		ListaOrdenadaAcotada<Integer> listaErronea = null;
		//Casos Pruebas válidas
		lista.clear();
		assertEquals(lista.size(),0);
		lista.add(1);
		lista.clear();
		assertEquals(lista.size(),0);
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.clear();
		assertEquals(lista.size(),0);
	
		//Casos de Prueba No Válidos

		assertThrows(NullPointerException.class,() -> listaErronea.size());
	}
	

}
