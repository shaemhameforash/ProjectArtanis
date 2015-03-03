package multiplayer;

import java.net.*;
import java.util.Arrays;

public class UDPServer {
	static byte[] gameID = {0,3,0,6};

	public static void initialize() throws Exception {
		@SuppressWarnings("resource")
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		while (true) {
			serverSocket.receive(receivePacket);
			byte[] headerID= new byte[4];
			for(int x = 0; x<4; ++x){
				headerID[x] = receivePacket.getData()[x];
			}
			if(Arrays.equals(headerID,gameID)){
				System.out.println("packet contains the game identifier");
				String sentence = new String(receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}else{
				System.out.println("packet does not contain the game identifier");
			}
		}
	}
}