package main;

import machine.*;
import machine.model.Event;

// El main sólo debería ser modificado para crear nuevos objetos que formen
// parte de la solución.
//
public class Main 
{
	public static void main(String[] args) 
	{		
		Machine machine = new Machine();
				
		// se introducen unos eventos en la máquina expendedora
		machine.addEvent(new Event("Concierto de los Rolling Stones", 120, 5));
		machine.addEvent(new Event("Cartelera: Blade runner", 10, 100));
		machine.addEvent(new Event("Actuación de Rubén García", 60, 80));
		machine.addEvent(new Event("Concierto de la OSPA", 90, 30));
		machine.addEvent(new Event("Ópera: Nabuco", 160, 20));
		
		// se cede el control de la ejecución a la máquina
		machine.run();
	}
}
