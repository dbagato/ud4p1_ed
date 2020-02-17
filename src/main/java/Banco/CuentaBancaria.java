package Banco;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CuentaBancaria {
	private String titular, saldo = "0", entidad, oficina, numCuenta, dni;
	public static final int MINIMO = 10, MAXIMO = 100;

	public CuentaBancaria(String titular, String entidad, String oficina, String dc, String numCuenta, String dni) {
		super();
		this.titular = titular;
		this.entidad = entidad;
		this.oficina = oficina;
		this.numCuenta = numCuenta;
		this.dni = dni;
//		if(!entidad.equals(String.valueOf(Integer.parseInt(entidad)))) {
//			throw new IllegalArgumentException("La entidad debe ser un número");
//		}
//		if(!oficina.equals(String.valueOf(Integer.parseInt(oficina)))) {
//			throw new IllegalArgumentException("La oficina debe ser un número");
//		}
//		if(!numCuenta.equals(String.valueOf(Integer.parseInt(numCuenta)))) {
//			throw new IllegalArgumentException("El número de cuenta debe ser un número");
//		}
	}

	public String getDni(String dni) throws IOException {
		FileInputStream leer = new FileInputStream("bd_banco.txt");
		BufferedReader bf = new BufferedReader(new InputStreamReader(leer));
		String line = bf.readLine();
		String cadena = null;

		while (line != null) {
			cadena = line.substring(0, 9);
			line = bf.readLine();
			if (dni.equals(cadena)) {
				System.out.println(dni);
				break;
			}
		}
		bf.close();
		if (dni.equals(cadena)) {
			return dni;
		} else {
			return null;
		}
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public CuentaBancaria(String titular, String ccc) {
		this.titular = titular;
		ccc = this.entidad + this.oficina + CuentaBancaria.obtenerDigitosControl(entidad, oficina, numCuenta)
				+ this.numCuenta;

	}

	public String[] getTitular() throws IOException {
		FileInputStream read = new FileInputStream("bd_banco.txt");
		BufferedReader bf = new BufferedReader(new InputStreamReader(read));
		String line = bf.readLine();
		String[] cadena = null;
		cadena = line.split(" ", 2);
		return cadena;
	}

	public void setTitular(String titular) {
		this.titular = titular;
		if (titular.length() < MINIMO || titular.length() > MAXIMO) {
			throw new IllegalArgumentException("El nombre del titular solo puede tener entre 10 y 100 carácteres");
		}
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public void ingresar(double cantidad) {

		double saldo1;
		saldo1 = Double.parseDouble(this.saldo);
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
		} else {
			saldo1 = saldo1 + cantidad;
		}
		this.saldo = String.valueOf(saldo1);

	}

	public void retirar(double cantidad) {
		double saldo1 = Double.parseDouble(this.saldo);
		if (cantidad < 0 || cantidad > Double.valueOf(saldo)) {
			throw new IllegalArgumentException(
					"La cantidad a retirar debe ser positiva y no puede sobrepasar el saldo");
		} else {
			saldo1 = saldo1 - cantidad;
		}
		this.saldo = String.valueOf(saldo1);
	}

	public static boolean comprobarCCC(String ccc) {

		if (ccc.substring(9, 10).equals(ccc.substring(9, 10))) {
			return false;
		} else {
			return true;
		}

	}

	public static String obtenerDigitosControl(String entidad, String oficina, String numCuenta) {
		String entidadOficina = "00" + entidad + oficina;
		int[] pesosArray = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		int dc1 = 0;
		int dc2 = 0;
		for (int i = 0; i < 10; i++) {
			dc1 = dc1 + (entidadOficina.charAt(i) * pesosArray[i]);
		}
		dc1 = dc1 % 11;
		dc1 = 11 - dc1;
		if (dc1 == 10) {
			dc1 = 1;
		} else if (dc1 == 11) {
			dc1 = 0;
		}
		for (int i = 0; i < 10; i++) {
			dc2 = dc2 + (numCuenta.charAt(i) * pesosArray[i]);
		}
		dc2 = dc2 % 11;
		dc2 = 11 - dc2;
		if (dc2 == 10) {
			dc2 = 1;
		} else if (dc2 == 11) {
			dc2 = 0;
		}
		String dcFinal = Integer.toString(dc1) + Integer.toString(dc2);
		return dcFinal;
	}

}
