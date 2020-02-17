package Banco;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaBancoTest {

	@Test
	void testSetTitular1() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
	}
	@Test
	void testSetTitular2() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		Exception exception = assertThrows(Exception.class, () -> usuario.setTitular("Pepe"));
		assertTrue(exception.getMessage().contains("El nombre del titular solo puede tener entre 10 y 100 carácteres"));
		
	}
	@Test
	void testSetTitular3() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		Exception exception = assertThrows(Exception.class, () -> usuario.setTitular("Brhadaranyakopanishadvivekachudamani Erreh Muñoz Castillo Helen Chufe Igor Dito De la Rosalia de la Concepcion"));
		assertTrue(exception.getMessage().contains("El nombre del titular solo puede tener entre 10 y 100 carácteres"));
		
	}
	@Test
	void testIngresar1() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("100.0");
		usuario.ingresar(100.0);
		assertEquals("200.0",usuario.getSaldo() );
	}
	@Test
	void testIngresar2() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("100.0");
		Exception exception = assertThrows(Exception.class, () -> usuario.ingresar(-100.0));
		assertTrue(exception.getMessage().contains("La cantidad a ingresar debe ser positiva"));
		
	}
	@Test
	void testIngresar3() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("100.0");
		Exception exception = assertThrows(Exception.class, () -> usuario.ingresar(0.0));
		assertTrue(exception.getMessage().contains("La cantidad a ingresar debe ser positiva"));
	}

	@Test
	void testRetirar1() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("1000.0");
		usuario.retirar(200.0);
		assertEquals("800.0", usuario.getSaldo());
	}
	@Test
	void testRetirar2() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("1000.0");
		Exception exception = assertThrows(Exception.class, () -> usuario.retirar(-200.0));
		assertTrue(exception.getMessage().contains("La cantidad a retirar debe ser positiva y no puede sobrepasar el saldo"));
	}
	
	@Test
	void testRetirar3() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		usuario.setSaldo("1000.0");
		Exception exception = assertThrows(Exception.class, () -> usuario.retirar(2000.0));
		assertTrue(exception.getMessage().contains("La cantidad a retirar debe ser positiva y no puede sobrepasar el saldo"));
	}

	/*
	 * @Test void testComprobarCCC1() { CuentaBancaria usuario = new
	 * CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470",
	 * "12345678A"); assertEquals(true,usuario.comprobarCCC("79691192016688325470")
	 * ); }
	 */
	@Test
	void testComprobarCCC2() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		assertEquals(false,usuario.comprobarCCC("32132132132132132321") );
		}

	@Test
	void testObtenerDigitosControl1() {
		CuentaBancaria usuario = new CuentaBancaria("Pepe Leproso Canonico", "7969","1192","01","6688325470", "12345678A");
		assertEquals("01", usuario.obtenerDigitosControl(usuario.getEntidad(), usuario.getOficina(), usuario.getNumCuenta()));
	}

}
