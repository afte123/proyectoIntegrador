import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


import comun.Mensaje;
import processing.core.*;
public class Comunicacion extends Thread {
	//Mensaje mensaje;

	private final int PORT = 5000;
	private DatagramSocket socket;
	private byte[] buzon;
	private DatagramPacket pRecibir;
	//Declaramos la clase de manejo de XML
	private XMLUsers xml;
	
	Comunicacion(PApplet app){
		try {
			xml = new XMLUsers(app);
			//La inicializamos
			socket = new DatagramSocket(PORT);
			System.out.println("UDP socket open and waiting data");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				//Recibimos mensajes constantemente
				recibir();
				sleep(10);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
public void recibir() {
		buzon = new byte[128];
		pRecibir = new DatagramPacket(buzon, buzon.length);
		try {
			//Recibe el paquete
			socket.receive(pRecibir);
			//Deserializa
			Mensaje mensaje = deserializar(pRecibir.getData());
			//Usuario que ingresa
			System.out.println(mensaje.getNombre() + " " + mensaje.getContra());
			//Agrega el usuario al XML
			xml.addUser(mensaje.getNombre(), mensaje.getContra());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//Serializa
	public byte[] serializar(Object obj) throws IOException {
		byte[] datos = null;
		ByteArrayOutputStream byteOut;
		ObjectOutputStream objectOut;

		try {
			byteOut = new ByteArrayOutputStream();
			objectOut = new ObjectOutputStream(byteOut);
			objectOut.writeObject(obj);
			objectOut.close();
			datos = byteOut.toByteArray();
		} catch (IOException e) {
//Fasha
		}
		return datos;
	}

	
	//Deserializa
	public Mensaje deserializar(byte[] des){
		Mensaje obj = null;
		ByteArrayInputStream byteIn;
		ObjectInputStream objectIn;
		try {
			byteIn = new ByteArrayInputStream(des);
			objectIn = new ObjectInputStream(byteIn);
			obj = (Mensaje) objectIn.readObject();
		} catch (IOException e) {
			//Fasha
		} catch (ClassNotFoundException e) {
			//Fasha again
		}
		return obj;
	}

}
