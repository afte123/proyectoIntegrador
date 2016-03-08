package comun;
import java.io.Serializable;


public class Mensaje implements Serializable {
	String mensaje;
	int origen;
	int x,y;
	public Mensaje(String mensaje, int origen) {
		this.mensaje = mensaje;
		this.origen = origen;
	}
	
	public Mensaje (String mensaje, int x, int y){
		this.mensaje = mensaje;
		this.x=x;
		this.y=y;
	}

	public int getOrigen(){
		return origen;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
