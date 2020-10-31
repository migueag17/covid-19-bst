package covid19;

public class PacientesBST {
	
	NodoPaciente root;
	int contFiebre = 0;
	
	public void addPaciente(Paciente nuevoPaciente) {
		if (root == null) {
			root = new NodoPaciente(nuevoPaciente);
			return;
		}
		
		addPaciente(root, nuevoPaciente);
	
	}
	
	private void addPaciente (NodoPaciente node, Paciente nuevoPaciente){
		if (node == null) {
			return;
		}
	
		if(nuevoPaciente.id == node.paciente.id) {
			System.out.println("El paciente ya está registrado");
		}
		if(nuevoPaciente.id > node.paciente.id) {
			if(node.derecho == null) {
				node.derecho = new NodoPaciente(nuevoPaciente);
				
			}
			else {
				addPaciente(node.derecho, nuevoPaciente);
			}
		}
		else {
			if(node.izquierdo == null) {
				node.izquierdo = new NodoPaciente(nuevoPaciente);
			}
			else {
				addPaciente(node.izquierdo, nuevoPaciente);
		
			}
			
		}
	}
	
	public Paciente buscar(int id) {
		return buscar(root,id);
	}
	private Paciente buscar(NodoPaciente nodo, int id) {
		
		if(nodo == null) {
			return null;
		}
		if(nodo.paciente.id == id) {
			return nodo.paciente;
		}
		
		if (nodo.paciente.id < id) {
			return buscar(nodo.derecho, id);
		}
		 return buscar(nodo.izquierdo, id);
	}
	
	public void verSintomas(int id) {
		verSintomasPaciente(root, id);
	}
	
	private void verSintomasPaciente(NodoPaciente root, int id) {
		if (root == null) {
			return;
		}
		 if(id == root.paciente.id) {
			 if(root.paciente.sintomas.length > 0) {
				 System.out.println("Los sintomas de " + root.paciente.nombre + " son : ");
				 for( int s = 0; s < root.paciente.sintomas.length;s++ ) {
					 System.out.println(root.paciente.sintomas[s]);
				 }
						 
			 }
		 }
		if (id >root.paciente.id) {
			verSintomasPaciente(root.derecho,id);
		}
		else {
			verSintomasPaciente(root.izquierdo,id);
		}
	}
	

	 public void contarFiebre() {
		 contarNumFiebre(root);
		 System.out.println("La cantidad de pacientes con fiebre, son " +  contFiebre);
		}
		
	private void contarNumFiebre(NodoPaciente root) {
		if(root == null) {
			return;
		}
		if(root.paciente.sintomas.length > 0) {
			for(int s = 0; s < root.paciente.sintomas.length; s++) {
					if (root.paciente.sintomas[s].equals("fiebre")) {
						contFiebre += 1;
				}
			}
		}
		if (root.derecho != null) {
			contarNumFiebre(root.derecho);	
		}
		if (root.izquierdo != null) {
			contarNumFiebre(root.izquierdo);
		}
	}
	
	public void obtenerPacienteMyrSintoma() {
		Paciente paciente = obtenerPacienteMayorSintomas(root);
		if(paciente != null) {
			System.out.println("El paciente con mayor número de síntomas es: " + paciente.nombre);
		}
		
	}
	private Paciente obtenerPacienteMayorSintomas(NodoPaciente nodo) {
		if(nodo == null) {
			return null;
		}
		
		Paciente mayorSintomasIzq = null;
		if(nodo.izquierdo != null) {
			mayorSintomasIzq = obtenerPacienteMayorSintomas(nodo.izquierdo);
		}
		
		Paciente mayorSintomasDer = null;
		if(nodo.derecho != null) {
			mayorSintomasDer = obtenerPacienteMayorSintomas(nodo.derecho);
		}
		
		Paciente masEnfermo = nodo.paciente;
		if(mayorSintomasIzq != null) {
			if(masEnfermo.sintomas.length <= mayorSintomasIzq.sintomas.length) {
				masEnfermo = mayorSintomasIzq;
		}
		}
		if(mayorSintomasDer != null) {
			if(masEnfermo.sintomas.length <= mayorSintomasDer.sintomas.length) {
				masEnfermo = mayorSintomasDer;
			}
		}
		return masEnfermo;
	}
	
	public void promedioPagos() {
		float totalPagos = totalPagos(root);
		totalPagos += root.paciente.cantidadAPagar;
		float totalPacientes = contarPacientes(root);
		totalPacientes += 1;
		float promedio =  totalPagos / totalPacientes;
		System.out.println("El promedio de pagos es: " + promedio);
	}
		private float totalPagos(NodoPaciente nodo) {
			if(nodo == null) {
				return 0;
			}		
			float derecho = 0;
			if(nodo.derecho != null) {
				derecho =  totalPagos(nodo.derecho);
				derecho += nodo.derecho.paciente.cantidadAPagar;
			}
			float izquierdo = 0;
			if(nodo.izquierdo != null) {
				izquierdo =  totalPagos(nodo.izquierdo);
				izquierdo += nodo.izquierdo.paciente.cantidadAPagar;
			}
			float totalPagos = derecho + izquierdo;
			return totalPagos;		
		}
		private float contarPacientes(NodoPaciente nodo) {
			if(nodo == null) {
				return 0;
			}		

			float derecho = 0;
			if(nodo.derecho != null) {
				derecho =  contarPacientes(nodo.derecho);
				derecho += 1;
			}
			
			float izquierdo = 0;
			if(nodo.izquierdo != null) {
				izquierdo =  contarPacientes(nodo.izquierdo);
				izquierdo += 1;
			}

			float totalPacientes = derecho + izquierdo;
			
			return totalPacientes;
			
		}
}


