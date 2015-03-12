import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.Random;
import java.util.StringTokenizer;


public class TCPClient{
	Socket clientSocket;
	PrintWriter outToServer;
	BufferedReader inFromUser;
	BufferedReader inFromServer;

	int prime;
	int base;
	int serverResult;
	int a;
	int clientResult;
	int secretKey;

	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static void main(String args[]){
		TCPClient client = new TCPClient();

		client.initializeClient(args[0], new Integer(args[1]).intValue());
		client.initValues();
		client.run(args[0], new Integer(args[1]).intValue());

	}

	public void initializeClient(String ip_address, int port){
		try{
			clientSocket = new Socket(ip_address,port);
			outToServer = new PrintWriter(clientSocket.getOutputStream());
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(String ip_address, int port){
		String message = "";
		String serverACK;
		String encrypted_message;
		String decrypted_message;

		try{
			while(true){
				message = inFromUser.readLine();
				System.out.println("Message before encryption: "+message);
				encrypted_message = encrypt(message, secretKey);
				System.out.println("Sending encrypted message: "+encrypted_message);
				outToServer.println(encrypted_message);
				outToServer.flush();
				//wait for server response.
				serverACK = inFromServer.readLine();
				System.out.println("Server ACK: "+serverACK);
				decrypted_message = decrypt(serverACK,secretKey);
				System.out.println("Server ACK Decrypted: "+decrypted_message);
			}
			//clientSocket.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void initValues(){
		prime = 23;
		base = 5;

		Random rand = new Random();
		//a = rand.nextInt(19) + 1;
		a=6;
		System.out.println("Ready to send to server...");
		clientResult = (base^a) % prime;
		try{
			outToServer.println("DH-ES:"+prime+":"+base+":"+clientResult);
			outToServer.flush();
			System.out.println("Waiting for response...");
			String response = inFromServer.readLine();
			System.out.println(response);
			String[] parts = response.split(":");
			serverResult=Integer.parseInt(parts[1]);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		secretKey = (serverResult^a) % prime;
		System.out.println(secretKey);
		//System.out.println(prime+" "+base+" "+clientResult);
	}
	public String encrypt(String message, int key){
		String encrypted="";
		for(int i=0; i<message.length(); i++){
			char c = (char)(message.charAt(i) + key);
			if(c>'z'){
				encrypted += (char)(message.charAt(i) - (26-key));
			}
			else{
				encrypted += c;
				//encrypted += (char)(message.charAt(i) + key);
			}
		}
		return encrypted;

	}
	public String decrypt(String message, int key){
		String decrypted="";
		for(int i=0; i<message.length(); i++){
			char c = (char)(message.charAt(i) - key);
			if(c<'a'){
				decrypted += (char)(message.charAt(i) + (26-key));
			}
			else{
				decrypted+=c;
				//decrypted += (char)(message.charAt(i) - key);
			}
		}
		return decrypted;
	}


}