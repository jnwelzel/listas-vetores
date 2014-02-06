package com.jonwelzel.listasvetores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	private static final String PROMPT_1 = "Informe um valor múltiplo de 10 entre 100 e 1000:";
	private static final String PROMPT_2 = "Deseja saber o somatório das posições (I)mpares ou (P)ares?";
	private static Integer numero = -1;
	private static Double[] numeros = new Double[10];
	private static String parOuImpar;
	private static Double resultado = 0d;

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(PROMPT_1);
		try {
			lerNumero(reader);
			gerarNumeros();
			System.out.println(PROMPT_2);
			lerParImpar(reader);
			reader.close();
			gerarResultado();
			System.out.println("Resultado final é: " + resultado);
		} catch (IOException e) {
			System.err.println("Erro ao abrir leitor de comandos (IOException).");
		}
	}

	private static void gerarResultado() {
		for (int i = 0; i < 10; i++) {
			if (parOuImpar.equalsIgnoreCase("P") && i % 2 == 0) {
				System.out.println(i + " é par, adiciona");
				resultado = resultado + numeros[i];
			} else if (parOuImpar.equalsIgnoreCase("I") && i % 2 != 0) {
				System.out.println(i + " é impar, adiciona");
				resultado = resultado + numeros[i];
			}
		}
	}

	private static void lerParImpar(BufferedReader reader) throws IOException {
		parOuImpar = reader.readLine();
		if (!parOuImpar.equalsIgnoreCase("P") && !parOuImpar.equalsIgnoreCase("I")) {
			lerParImpar(reader);
		}
	}

	private static void gerarNumeros() {
		for (int i = 0; i < 10; i++) {
			if (i % 3 == 0) {
				numeros[i] = i * 0.3 * numero;
			} else {
				numeros[i] = i * 0.1 * numero;
			}
		}
	}

	private static void lerNumero(BufferedReader reader) throws IOException {
		String numeroConsole = reader.readLine();
		converterNumero(numeroConsole);
		if (numero == -1 || !validarNumero(numero)) {
			System.out.println(PROMPT_1);
			lerNumero(reader);
		}
	}

	private static void converterNumero(String numeroConsole) {
		numero = -1;
		try {
			numero = Integer.parseInt(numeroConsole);
		} catch (NumberFormatException nfe) {
			System.out.println("Formato inválido!");
		}
	}

	private static boolean validarNumero(Integer numeroConsole) {
		return numeroConsole > 99 && numeroConsole < 1001 && numeroConsole % 10 == 0;
	}
}
