import java.util.ArrayList;

import processing.core.*;
public class Logica {
PApplet app;
Comunicacion com;
ArrayList <String> nombres;
ArrayList <String> contrasenas;

	Logica (PApplet app){
		this.app=app;
		nombres = new ArrayList();
		contrasenas = new ArrayList();
		com = new Comunicacion(app);
		com.start();
	}
}
