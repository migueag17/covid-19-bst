package covid19;

public class Paciente {
	int id;
	String nombre;
	float cantidadAPagar;
	String sintomas[];
	
	public Paciente(int numId, String vlrNombre, int cantidadSintomas ) {
		id = numId;
		nombre = vlrNombre;
		sintomas = new String [cantidadSintomas];
		if (cantidadSintomas > 0) {
			for (int i = 0; i< cantidadSintomas; i++) {
				cantidadAPagar += 100;	
			}
		}
	}

		
		
		
	}
	

