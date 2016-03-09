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

	private final int PORT = 5000;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private String command="";
	private byte[] buzon;
	private DatagramPacket pRecibir;
	
	Comunicacion(){

		try {
			// Initialize the DatagramSocket to receive commands
			socket = new DatagramSocket(PORT);
			System.out.println("UDP socket open and waiting data");

			// Create the buffer and the receiving packet
			byte[] buffer = new byte[2364];
			packet = new DatagramPacket(buffer, buffer.length);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Object recibir() throws IOException, ClassNotFoundException {
		buzon = new byte[1025];
		pRecibir = new DatagramPacket(buzon, buzon.length);
		
			socket.receive(pRecibir);
			Object obj = deserializar(buzon);
			System.out.println(buzon);
			return obj;

	}

	public void run() {
		
		while (true) {
			Mensaje mensaje;
			try {
				// Receive packets and process the information
				mensaje = (Mensaje) recibir();
				socket.receive(packet);
				command= new String(packet.getData(),0,packet.getLength());
				
				System.out.println(command);
				
				
		
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	public byte[] serializar(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput oos = new ObjectOutputStream(bos);

		oos.writeObject(obj);
		oos.close();
		return bos.toByteArray();

	}

	public Object deserializar(byte[] des) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(des);
		ObjectInputStream is = new ObjectInputStream(bais);
		Object obj = (Object) is.readObject();
		is.close();

		return obj;

	}
}
