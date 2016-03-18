package comun;

import java.io.Serializable;

public class Mensaje implements Serializable {
	  String nombre, contra, nickName, apellido;
	    int solicitud;


	    public Mensaje (int solicitud, String nombre, String apellido,  String contra, String nickName){
	        this.solicitud=solicitud;
	        this.nombre=nombre;
	        this.apellido=apellido;
	        this.contra=contra;
	        this.nickName=nickName;
	    }

	    public Mensaje (int solicitud, String nickName,  String contra){
	        this.solicitud=solicitud;
	        this.contra=contra;
	        this.nickName=nickName;
	    }

	    public Mensaje (int solicitud){
	        this.solicitud=solicitud;
	    }

	    public int getSolicitud() {
	        return solicitud;
	    }

	    public String getNombre(){
	        return nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public String getContra() {  return contra; }

	    public String getNickName() {
	        return nickName;
	    }



}
