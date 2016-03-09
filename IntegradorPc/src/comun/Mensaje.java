package comun;
import java.io.Serializable;


public class Mensaje implements Serializable {
	String nombre, contra, separar;


	public Mensaje (String nombre,String separar, String contra){
		this.separar=separar;
		this.nombre=nombre;
		this.contra=contra;
	}

	public String getNombre(){
		return nombre;
	}

	public String getContra() {
		return contra;
	}

}
