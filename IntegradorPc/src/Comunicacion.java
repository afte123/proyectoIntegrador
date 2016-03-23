import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import comun.Mensaje;
import processing.core.*;

public class Comunicacion extends Thread {
	// Mensaje mensaje;

	private final int PORT = 5000;
	private DatagramSocket socket;
	private byte[] buzon;
	private DatagramPacket packete;
	// Declaramos la clase de manejo de XML
	private XMLUsers xml;

	Comunicacion(PApplet app) {
		try {
			xml = new XMLUsers(app);
			// La inicializamos
			socket = new DatagramSocket(PORT);
			System.out.println("Esperando datos");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				// Recibimos mensajes constantemente
				recibir();
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void recibir() {
		buzon = new byte[1024];
		packete = new DatagramPacket(buzon, buzon.length);
		try {
			// Recibe el paquete
			socket.receive(packete);
			InetAddress ip = packete.getAddress();
			// Deserializa
			Mensaje mensaje = deserializar(packete.getData());
			// Usuario que ingresa
			if (mensaje.getSolicitud().equals("Registro")) {
				System.out.println(mensaje.getNombre() + " " + mensaje.getContra() + " " + mensaje.getSolicitud());
				// Agrega el usuario al XML
				xml.addUser(mensaje.getNombre(), mensaje.getApellido(), mensaje.getContra(), mensaje.getNickName());
			}
			if (mensaje.getSolicitud().equals("Login")) {
				int result = xml.validate(mensaje.getNickName(), mensaje.getContra());
				System.out.println("Resultado validación " + result);
				if (result == 0) {
					enviar("No existe", ip);
					System.out.println("Ste men no existe");
				}
				if (result == 1) {
					enviar("Bienvenido", ip);
					System.out.println("Holi");
				}
				if (result == 1) {
					enviar("Contrasena Incorrecta", ip);
					System.out.println("Error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void enviar(String comando, InetAddress ip) {
		byte[] datos = comando.getBytes();
		try {
			DatagramPacket packetes = new DatagramPacket(datos, datos.length, ip, PORT);
			socket.send(packetes);
			packete = null;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	// Serializa
	public byte[] serializar(Object obj) {
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
			// Fasha
		}
		return datos;
	}

	// Deserializa
	public Mensaje deserializar(byte[] des) {
		Mensaje obj = null;
		ByteArrayInputStream byteIn;
		ObjectInputStream objectIn;
		try {
			byteIn = new ByteArrayInputStream(des);
			objectIn = new ObjectInputStream(byteIn);
			obj = (Mensaje) objectIn.readObject();
		} catch (IOException e) {
			// Fasha
		} catch (ClassNotFoundException e) {
			// Fasha again
		}
		return obj;
	}

}
