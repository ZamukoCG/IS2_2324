package es.unican.is2.franquiciasucgui;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import es.unican.is2.franquiciasucbusiness.GestionEmpleados;
import es.unican.is2.franquiciasucbusiness.GestionTiendas;
import es.unican.is2.franquiciasucdao.EmpleadosDAO;
import es.unican.is2.franquiciasucdao.TiendasDAO;

class VistaGerenteTest {

    private FrameFixture demo;

    @BeforeEach
    public void setUp() {

        TiendasDAO tiendasDAO = new TiendasDAO();
        EmpleadosDAO empleadosDAO = new EmpleadosDAO();

        GestionTiendas tiendas = new GestionTiendas(tiendasDAO);
        GestionEmpleados empleados = new GestionEmpleados(tiendasDAO, empleadosDAO);

        VistaGerente gui = new VistaGerente(tiendas, empleados);
        demo = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    void test() {
    	
        demo.button("btnBuscar").requireText("Buscar");
        demo.textBox("txtNombreTienda").enterText("Tienda A");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Direccion A");
        demo.textBox("txtNombreTienda").deleteText();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	
        }

        demo.textBox("txtNombreTienda").enterText("Tienda1");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        	
        }
    }

}
