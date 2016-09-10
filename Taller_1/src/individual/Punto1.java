package individual;

import java.util.*;

public class Punto1 {
	static double EPS = 0.000001;
	static int tipo1; static int tipo2; static int tipo3; static int utilidad; static double tiempoCliente;
	static boolean available; static int clientesTotales; static double time; static TreeSet<Event> events;
	static ArrayList<Cliente> queue;
	
	
	public static void main(String[] args) {
		initialize();
		
		double lastEvent = 0.0;
		
		while(!events.isEmpty()) {
			Event cur = events.pollFirst();
			time = cur.tiempo;
			switch(cur.tipo) {
				case 1:
					Cliente next = new Cliente(tipo1, tipo2, tipo3, time); 
					queue.add( next );
					if( next.tipo == 1 ) tipo1++;
					else if( next.tipo == 2 ) tipo2++;
					else tipo3++;
					if(available && queue.size() == 1) cajero();
					break;
				
				case 2:
					available = true;
					if(!queue.isEmpty()) cajero();
					lastEvent = cur.tiempo;
					break;
					
				default:
					break;
			}
		}
		System.out.println("Se atendieron un total de " + clientesTotales + " clientes");
		System.out.println("De los cuales: ");
		System.out.println(tipo1 + " no compraron nada");
		System.out.println(tipo2 + " compraron una prenda tipo A");
		System.out.println(tipo3 + " compraron una prenda tipo B");
		System.out.println("Los clientes estan en la cola un promedio de " + tiempoCliente / clientesTotales + " minutos");
		System.out.println("El cajero termina de atender a los " + lastEvent + " minutos");
		System.out.println("La utilidad del día fue de " + utilidad);
		
	}
	
	public static void cajero() {
		Cliente cliente = queue.remove(0);
		available = false;
		double tiempoCaja;
		if(cliente.tipo == 1) tiempoCaja = 1.5;
		else if(cliente.tipo == 2) {
			tiempoCaja = (Math.random()*0.7 + 3.1);
			utilidad += 2500;
		}
		else {
			tiempoCaja = (Math.log(1.0-Math.random()) * -7.0);
			utilidad += 4500;
		}
		tiempoCliente += tiempoCaja + time - cliente.tiempo;
		events.add( new Event(2, tiempoCaja + time) );
	}
	
	public static void initialize() {
		tipo1 = 0; tipo2 = 0; tipo3 = 0; utilidad = 0; clientesTotales = 0;
		tiempoCliente = 0.0; time = 0.0;
		available = true;
		events = new TreeSet<Event>();
		queue = new ArrayList<Cliente>();
		
		double t = (Math.log(1-Math.random()) * -1.5);
		while( t <= 480.0 ) {
			events.add(new Event(1,t));
			t += (Math.log(1-Math.random()) * -1.5);
			clientesTotales++;
		}
	}
	
	protected static class Event implements Comparable<Event> {
		/*tipo:
		 * 1 - Llegada cliente a la cola
		 * 2 - Salida del cliente de la caja
		 */
		int tipo;
		double tiempo;
		
		public Event(int tipo, double tiempo) {
			this.tipo = tipo;
			this.tiempo = tiempo;
		}

		public int compareTo(Event o) {
			return this.tiempo - o.tiempo < EPS ? -1 : 1;
		}
	}
	
	protected static class Cliente {
		int tipo;
		double tiempo;
		
		public Cliente(double tipo1, double tipo2, double tipo3, double time) {
	    	double pa = tipo1 / (tipo1+tipo2+tipo3);
	    	double pb = tipo2 / (tipo1+tipo2+tipo3);
	    	if(pa < 0.2) this.tipo = 1;
	    	else if(pb < 0.5) this.tipo = 2;
	    	else this.tipo = 3;
	    	this.tiempo = time;
	    }
	}
}

