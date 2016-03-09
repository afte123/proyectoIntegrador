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
	private String command="";
	private byte[] buzon;
	private DatagramPacket pRecibir;
	//dddddd
	Comunicacion(){

		try {
			// Initialize the DatagramSocket to receive commands
			socket = new DatagramSocket(PORT);
			System.out.println("UDP socket open and waiting data");

			// Create the buffer and the receiving packet
			byte[] buffer = new byte[2364];
			//packet = new DatagramPacket(buffer, buffer.length);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	public void run() {
		
		while (true) {
			
			try {
				// Receive packets and process the information
				recibir();
				sleep(10);
				
				
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
public void recibir() {
		
		buzon = new byte[2225];
		pRecibir = new DatagramPacket(buzon, buzon.length);
		
		try {
			socket.receive(pRecibir);
			Mensaje mensaje = deserializar(pRecibir.getData());
			
			//Object obj = deserializar(buzon);
			System.out.println(mensaje.getNombre());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		



	}
	
	
	
	
	
	
	public byte[] serializar(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput oos = new ObjectOutputStream(bos);

		oos.writeObject(obj);
		oos.close();
		return bos.toByteArray();

	}

	public Mensaje deserializar(byte[] des){
		ByteArrayInputStream bais = new ByteArrayInputStream(des);
		Mensaje msj=null;
		
		try {
			ObjectInputStream is = new ObjectInputStream(bais);
			msj = (Mensaje) is.readObject();
			is.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			

		}

		return msj;

	}
}
