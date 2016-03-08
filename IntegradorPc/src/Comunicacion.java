import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import processing.core.*;
public class Comunicacion {

	private final int PORT = 5000;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private String command="";
	
	Comunicacion(){

		try {
			// Initialize the DatagramSocket to receive commands
			socket = new DatagramSocket(PORT);
			System.out.println("UDP socket open and waiting data");

			// Create the buffer and the receiving packet
			byte[] buffer = new byte[64];
			packet = new DatagramPacket(buffer, buffer.length);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
		while (true) {
			try {
				// Receive packets and process the information
				


				
				socket.receive(packet);
				command= new String(packet.getData(),0,packet.getLength());
				
				
				
				
		
				
				
			} catch (IOException e) {
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
