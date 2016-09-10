package grupal;

import java.util.*;
public class Ejercicio_2 {
	
	static TreeSet<Event> events;
	static TreeSet<Double> fallosA;
	static TreeSet<Double> arreglosA;
	static TreeSet<Double> fallosB;
	static TreeSet<Double> arreglosB;
	static TreeSet<Pieza> queueA;
	static TreeSet<Pieza> queueB;
	static TreeSet<Pieza> queueC;
	static int maxA;
	static int curA;
	static int maxB;
	static int curB;
	static int maxC;
	static int curC;
	static double EPS = 0.000001;
	static int piezas1;
	static int piezas2;
	static double demora1;
	static double demora2;
	static double demora3;
	static double demoraA;
	static double demoraB;
	static boolean estadoA;
	static boolean estadoB;
	static double siguienteFalloA;
	static double siguienteArregloA;
	static double siguienteFalloB;
	static double siguienteArregloB;
	static boolean workingA;
	static boolean workingB;
	static boolean workingC;
	static double time;
	static int no_runs = 2;
	static ArrayList<Data> runs = new ArrayList<Data>();
	
/*	public static void main(String[] args) {
		for(int r = 1; r <=no_runs; r++){
			initialize();

			while(!events.isEmpty()) {
				Event cur = events.pollFirst();
				time = cur.tiempo;
				switch(cur.tipo) {
				case 1:
					queueA.add( new Pieza(1) );
					curA++;
					if(curA > maxA) maxA = curA;
					if(estadoA && queueA.size() == 1 && !workingA) maquinaA();
					break;

				case 2:
					queueB.add( new Pieza(cur.p) );
					curB++;
					if(curB > maxB) maxB = curB;
					if(estadoB && queueB.size() == 1 && !workingB) maquinaB();
					break;	

				case 3:
					queueC.add( new Pieza(cur.p) );
					curC++;
					if(curC > maxC) maxC = curC;
					if(queueC.size() == 1 && !workingC) maquinaC();
					break;

				case 4:
					workingA = false;
					if(!queueA.isEmpty()) maquinaA();
					break;

				case 5:
					workingB = false;
					if(!queueB.isEmpty()) maquinaB();
					break;

				case 6:
					if(cur.p == 1) piezas1++;
					else piezas2++;
					workingC = false;
					if(!queueC.isEmpty()) maquinaC();
					break;

				case 7:
					estadoA = false;
					if(fallosA.isEmpty()) siguienteFalloA = 1000000;
					else siguienteFalloA = fallosA.pollFirst();
					break;

				case 8:
					estadoA = true;
					if(arreglosA.isEmpty()) siguienteArregloA = 1000000;
					else siguienteArregloA = arreglosA.pollFirst();

					if(!queueA.isEmpty()) maquinaA();
					break;

				case 9:
					estadoB = false;
					if(fallosB.isEmpty()) siguienteFalloB = 1000000;
					else siguienteFalloB = fallosB.pollFirst();
					break;

				case 10:
					estadoB = true;
					if(arreglosB.isEmpty()) siguienteArregloB = 1000000;
					else siguienteArregloB = arreglosB.pollFirst();

					if(!queueB.isEmpty()) maquinaB();
					break;

				case 11:
					piezas1 = 0; piezas2 = 0; 
					demora1 = 0.0; demora2 = 0.0; demora3 = 0.0;
					maxA = 0; maxB = 0; maxC = 0;
					curA = 0; curB = 0; curC = 0;
					break;
				default:
					break;
				}
			}
			
			Data data = new Data(piezas1, piezas2, demora1, demora2, demora3, maxA, maxB, maxC);
			runs.add(data);
		}
		calcAverage();
		printruns();
		
		
	}
	*/
	public static void printruns(){
		System.out.println("item				Run 1				Run 2			Average of 2 Runs");
		System.out.println("Utilizacion A		"+runs.get(0).tiempo_A+"		"+runs.get(1).tiempo_A+"		"+runs.get(2).tiempo_A);
		System.out.println("Utilizacion B		"+runs.get(0).tiempo_B+"		"+runs.get(1).tiempo_B+"		"+runs.get(2).tiempo_B);
		System.out.println("Utilizacion C		"+runs.get(0).tiempo_C+"		"+runs.get(1).tiempo_C+"		"+runs.get(2).tiempo_C);
		System.out.println("Piezas I			"+runs.get(0).piezasI+"				"+runs.get(1).piezasI+"				"+runs.get(2).piezasI);
		System.out.println("Piezas II			"+runs.get(0).piezasII+"				"+runs.get(1).piezasII+"				"+runs.get(2).piezasII);
		System.out.println("tamano maximo cola A		"+runs.get(0).max_cola_A+"				"+runs.get(1).max_cola_A+"				"+runs.get(2).max_cola_A);
		System.out.println("tamano maximo cola B		"+runs.get(0).max_cola_B+"				"+runs.get(1).max_cola_B+"				"+runs.get(2).max_cola_B);
		System.out.println("tamano maximo cola C		"+runs.get(0).max_cola_C+"				"+runs.get(1).max_cola_C+"				"+runs.get(2).max_cola_C);
	}
	
	public static void calcAverage(){
		int p1 = (runs.get(0).piezasI + runs.get(1).piezasI)/2;
		int p2 = (runs.get(0).piezasII + runs.get(1).piezasII)/2;
		double d1 = (runs.get(0).tiempo_A + runs.get(1).tiempo_A)/2.0;
		double d2 = (runs.get(0).tiempo_B + runs.get(1).tiempo_B)/2.0;
		double d3 = (runs.get(0).tiempo_C + runs.get(1).tiempo_C)/2.0;
		int ma = (runs.get(0).max_cola_A + runs.get(1).max_cola_A)/2;
		int mb = (runs.get(0).max_cola_B + runs.get(1).max_cola_B)/2;
		int mc = (runs.get(0).max_cola_C + runs.get(1).max_cola_C)/2;
		
		Data data = new Data(p1,p2,d1,d2,d3,ma,mb,mc);
		runs.add(data);
	}
	
	public static void initialize() {
		//Inicializar variables
		piezas1 = 0;
		piezas2 = 0;
		demora1 = 0.0;
		demora2 = 0.0;
		demora3 = 0.0;
		maxA = 0;
		maxB = 0;
		maxC = 0;
		curA = 0;
		curB = 0;
		curC = 0;
		estadoA = true;
		estadoB = true;
		time = 480.0;
		workingA = false;
		workingB = false;
		workingC = false;
		events = new TreeSet<Event>();
		queueA = new TreeSet<Pieza>();
		queueB = new TreeSet<Pieza>();
		queueC = new TreeSet<Pieza>();
		fallosA = new TreeSet<Double>();
		arreglosA = new TreeSet<Double>();
		fallosB = new TreeSet<Double>();
		arreglosB = new TreeSet<Double>();
		
		//Generar todos los eventos de tipo fallo-arreglo y llegada de piezas.
		double t = 480.0;
		while(t < 2880) {
			t += 400.0 + random(350);
			if(t <= 2880) {
				events.add( new Event(7,t,0) );
				fallosA.add( t );
			}
			t += 15.0 + random(14);
			if(t <= 2880) {
				events.add( new Event(8,t,0) );
				arreglosA.add( t );
			}
		}
		t = 480.0;
		while(t < 2880) {
			t += 200.0 + random(150);
			if(t <= 2880) {
				events.add( new Event(9,t,0) );
				fallosB.add( t );
			}
			t += 10.0 + random(8);
			if(t <= 2880) {
				events.add( new Event(10,t,0) );
				arreglosB.add( t );
			}
		}
		t = 0.0;
		while(t < 2880) {
			t += 100.0 + random(10);
			if(t <= 2880) {
				if(t >= 480) events.add( new Event(1,t,1) );
				queueA.add( new Pieza(1) );
				maxA++;
			}
		}
		t = 0.0;
		while(t < 2880) {
			t += 10.0 + random(6);
			if(t <= 2880) {
				if(t >= 480) events.add( new Event(2,t,2) );
				queueB.add( new Pieza(2) );
				maxB++;
			}
		}
		events.add( new Event(11, 480.0,0));
		siguienteFalloA = fallosA.pollFirst();
		siguienteArregloA = arreglosA.pollFirst();
		siguienteFalloB = fallosB.pollFirst();
		siguienteArregloB = arreglosB.pollFirst();
		curA = maxA;
		curB = maxB;
		curC = maxC;
	}
	
	public static double random(int max) {
		double random = Math.random()*(max);
		if(Math.random() > 0.5) random *= (-1.0) ;
		return random;
	}
	
	public static void maquinaA() {
		workingA = true;
		queueA.pollFirst();
		curA--;
		demoraA = 90 + random(15);
		if(siguienteFalloA < time + demoraA) {
			demoraA = siguienteFalloA - time;
			events.add( new Event(2,siguienteFalloA,1) );
		}
		else { 
			events.add( new Event(4,time+demoraA,1) );
			events.add( new Event(3,time+demoraA,1) );
		}
		demora1 += demoraA;
	}
	
	public static void maquinaB() {
		workingB = true;
		Pieza cur = queueB.pollFirst();
		curB--;
		if(cur.tipo == 1) demoraB = 100.0 + random(20);
		else demoraB = 8 + random(5);
		demora2 += demoraB;
		
		if(siguienteFalloB < time + demoraB) demoraB += siguienteArregloB - siguienteFalloB;
		events.add( new Event(5,time+demoraB,cur.tipo) );
		events.add( new Event(3,time+demoraB,cur.tipo) );
		
	}
	
	public static void maquinaC() {
		workingC = true;
		Pieza cur = queueC.pollFirst();
		curC--;
		double demoraC = 8.0 + random(6);
		events.add( new Event(6,time+demoraC,cur.tipo) );
		demora3 += demoraC;
	}
	
	protected static class Event implements Comparable<Event> {
		/*tipo:
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
		int tipo;
		int p;
		double tiempo;
		
		public Event(int tipo, double tiempo, int p) {
			this.tipo = tipo;
			this.tiempo = tiempo;
			this.p = p;
		}

		public int compareTo(Event o) {
			return this.tiempo - o.tiempo < EPS ? -1 : 1;
		}
	}
	
	protected static class Pieza implements Comparable<Pieza> {
		int tipo;
		
		public Pieza(int tipo) {
			this.tipo = tipo;
		}

		public int compareTo(Pieza o) {
			return this.tipo - o.tipo;
		}
	}
	
	protected static class Data{
		int piezasI;
		int piezasII;
		double tiempo_A;
		double tiempo_B;
		double tiempo_C;
		int max_cola_A;
		int max_cola_B;
		int max_cola_C;
		public Data(int piezasI, int piezasII, double tiempo_A, double tiempo_B, double tiempo_C, int max_cola_A, int max_cola_B,
				int max_cola_C) {
			this.piezasI = piezasI;
			this.piezasII = piezasII;
			this.tiempo_A = tiempo_A;
			this.tiempo_B = tiempo_B;
			this.tiempo_C = tiempo_C;
			this.max_cola_A = max_cola_A;
			this.max_cola_B = max_cola_B;
			this.max_cola_C = max_cola_C;
		}
	}
}
