package grupal;

import java.util.*;

public class Ejercicio_2 {
	
	/* Definición de variables a utilizar
	 * se hace uso de TreeSets debido a su
	 * complejidad logarítimca en todas sus operaciones
	 * y por su conveniencia de uso*/
	final static double EPS = 0.0000001; //para precisión en operaciones con números de coma flotante
	static TreeSet<Event> events;
	static TreeSet<Double> fallos_maquina_A; //guarda los tiempos de fallos de la maquina 
	static TreeSet<Double> fallos_maquina_B;
	static TreeSet<Double> Arreglos_maquina_A; //guarda los tiempos de arreglo de la maquina 
	static TreeSet<Double> Arreglos_maquina_B;
	static Queue<Pieza> cola_maquina_A;
	static Queue<Pieza> cola_maquina_B;
	static Queue<Pieza> cola_maquina_C;
	static int max_size_A; //tamaños máximos de las colas
	static int max_size_B;
	static int max_size_C;
	static int curr_size_A; //tamaño actual de las colas
	static int curr_size_B;
	static int curr_size_C;
	static int numero_piezas_1;  //piezas de este tipo procesadas completamente
	static int numero_piezas_2;
	static double tiempo_activo_A;  //tiempo de funcionamiento de las maquinas
	static double tiempo_activo_B;
	static double tiempo_activo_C;
	static double siguiente_fallo_A;	//tiempo del siguiente fallo de la maquina
	static double siguiente_fallo_B;
	static double siguiente_arreglo_A;	//tiempo del siguiente arreglo de la maquina
	static double siguiente_arreglo_B;
	static boolean estado_A;	
	static boolean estado_B;
	static boolean ocupacion_A;		//variables que dictan si una maquina está procesando una pieza o no
	static boolean ocupacion_B;
	static boolean ocupacion_C;
	
	
	/* Función generadora de números aleatorios
	 * de una distribución uniforme */
	private static double randomUniform(double mean, double dev){
		double random = Math.random();
		return random + mean*2.0 * dev - dev;
	}
	
	/*Inicializar variables*/
	public static void init(){
		max_size_A = max_size_B = max_size_C = 0;
		numero_piezas_1 = numero_piezas_2 = 0;
		curr_size_A = curr_size_B = curr_size_C = 0;
		tiempo_activo_A = tiempo_activo_B = tiempo_activo_C = 0;
		events = new TreeSet<Event>();
		fallos_maquina_A = new TreeSet<Double>();
		fallos_maquina_B = new TreeSet<Double>();
		Arreglos_maquina_A = new TreeSet<Double>();
		Arreglos_maquina_B = new TreeSet<Double>();
		cola_maquina_A = new LinkedList<Pieza>();
		cola_maquina_B = new LinkedList<Pieza>();
		cola_maquina_C = new LinkedList<Pieza>();
		
	}

	public static void main(String[] args) {

	}

	/* Clase evento
	 * usada para llevar cuenta de una manera más organizada de los eventos, tipo de los eventos:
	 * 1 - Llegada Pieza maquina A
	 * 2 - Llegada Pieza maquina B
	 * 3 - Llegada Pieza maquina C
	 * 4 - Salida Maquina A
	 * 5 - Salida Maquina B
	 * 6 - Salida Maquina C
	 * 7 - Daño Maquina A
	 * 8 - Arreglo Maquina A
	 * 9 - Daño Maquina B
	 * 10 - Arreglo Maquina B
	 * 11 - 8 horas, reiniciar contadores
	 */
	private static class Event implements Comparable<Event> {
		
		private int tipo, p;
		double tiempo;
		
		public Event(int tipo, int p, double tiempo) {
			this.tipo = tipo;
			this.p = p;
			this.tiempo = tiempo;
		}

		@Override
		public int compareTo(Event o) {
			return this.tiempo - o.tiempo < EPS? -1:1;									
		}

	}
	
	/* Clase Pieza*/
	private static class Pieza implements Comparable<Pieza>{
		private int tipo;
		public Pieza(int tipo) {
			this.tipo = tipo;
		}
		
		@Override
		public int compareTo(Pieza o) {
			return this.tipo - o.tipo;
		}
		
	}

}
