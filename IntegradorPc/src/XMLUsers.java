import processing.core.PApplet;
import processing.data.XML;

public class XMLUsers {

	private XML users;
	private PApplet app;

	public XMLUsers(PApplet app) {
		this.app = app;
		//Se carga el XML
		users = app.loadXML("../data/listaUsuarios.xml");

	}

	public void addUser(String usuario, String contrasena) {
		System.out.println("Se guarda");
		boolean yaEsta = false;
		//Se eval�an los hijos
		XML[] variantes = users.getChildren("user");
		for (int i = 0; i < variantes.length; i++) {
			//Si el usuario ingresado ya est�...
			if (variantes[i].getString("usuario").equals(usuario)) {
				yaEsta = true; //Ya existe
				System.out.println("Este usuario ya existe, no se puede registrar");
			}
		}
		
		//Si no est�...
		if (!yaEsta) {
			//Que se cree una l�nea de c�digo con el usuario y contrase�a ingresados
			XML hijo = app
					.parseXML("<user usuario=\"" + usuario + "\" contrasena=\"" + contrasena + "\"></user>");
			//Se agrega el hijo
			users.addChild(hijo);
			//Y finalmente se guarda
			app.saveXML(users, "../data/listaUsuarios.xml");
			System.out.println("Usuario " + usuario + " registrado");
			
		}
	}

	public int validate(String usuario, String contrasena) {
		int state = 0; //Nope
		//Se validan los hijos
		XML[] hijos = users.getChildren("user");
		for (int i = 0; i < hijos.length; i++) {
			//Si el hijo ya est� bien...
			if (hijos[i].getString("usuario").equals(usuario)) {
				//Si el usuario coincide
				if (hijos[i].getString("contrasena").equals(contrasena)) {
					state = 1;//Y la contrase�a tambi�n
				} else {
					state = 2; //Si no
				}
			}
		}
		return state;
	}
}
