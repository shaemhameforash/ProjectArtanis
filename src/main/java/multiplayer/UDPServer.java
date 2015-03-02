package multiplayer;

import java.net.*;

public class UDPServer {

	public static void initialize() throws Exception {
		@SuppressWarnings("resource")
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		while (true) {
			serverSocket.receive(receivePacket);
			if(receiveData[0] == 0 && receiveData[1] ==3 && receiveData[2]==0 && receiveData[3] ==6){
				System.out.println("connection established");
			}
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}