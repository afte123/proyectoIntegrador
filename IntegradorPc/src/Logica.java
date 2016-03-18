import java.util.ArrayList;

import processing.core.*;
public class Logica {
PApplet app;
Comunicacion com;

	Logica (PApplet app){
		this.app=app;
		com = new Comunicacion(app);
		com.start();
	}
}
