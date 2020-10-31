package covid19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PacientesBST pac = new PacientesBST();
		
		BufferedReader datosCovid = new BufferedReader(new FileReader("datos-covid.txt"));
		
		int numPacientes = Integer.parseInt(datosCovid.readLine());
		
		for ( int i = 0; i<numPacientes; i++) {
			String datosPaciente[] = datosCovid.readLine().split(" ");
			Paciente paciente = new Paciente(Integer.parseInt(datosPaciente[0]), datosPaciente[1], Integer.parseInt(datosPaciente[2]));
			int numSintomas = Integer.parseInt(datosPaciente[2]);
			
			for ( int j = 0; j<numSintomas; j++) {
				String sintoma = datosCovid.readLine();
				paciente.sintomas[j] = sintoma;
			}
			pac.addPaciente(paciente);
			System.out.println("Paciente agregado al sistema");
			
			}
	
		
		Paciente buscarResultado = pac.buscar(3);
		if (buscarResultado == null) {
			System.out.println("Paciente no encontrado");
		}
		else {
			System.out.println("Paciente encontrado. Nombre: " + buscarResultado.nombre);
			pac.verSintomas(3);
			System.out.println("Valor a pagar: " + buscarResultado.cantidadAPagar);
			pac.contarFiebre();
			pac.promedioPagos();
			pac.obtenerPacienteMyrSintoma();
		}
	}
}
	
