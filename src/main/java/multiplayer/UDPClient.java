package multiplayer;

import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
	  while(true){
			      DatagramSocket clientSocket = new DatagramSocket();
			      InetAddress IPAddress = InetAddress.getByName("52.16.70.139");
			      byte[] sendData = new byte[1024];
			      byte[] receiveData = new byte[1024];
			      String sentence;
			      sentence ="PING";
			      sendData = sentence.getBytes();
			      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			      clientSocket.send(sendPacket);
			      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			      clientSocket.receive(receivePacket);
			      String modifiedSentence = new String(receivePacket.getData());
			      System.out.println("FROM SERVER:" + modifiedSentence);
			      clientSocket.close();
	  }
   }
}
