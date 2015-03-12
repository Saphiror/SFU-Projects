import java.io.*;
import java.net.*;
import java.util.Random;



public class TCPServer{
	
	ServerSocket serverSocket;
	Socket clientSocket;

	//Send to client
	PrintWriter outToClient;

	//Variables for DH algorithm
	int prime;
	int base;
	int serverResult;
	int clientResult;
	int secretKey;
	int b;

	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static void main(String args[]){
		TCPServer server = new TCPServer();
		server.run(new Integer(args[0]).intValue());

	}

	public void run(int port){
		String capitalizedSentance;
		String clientMessage;

		try{
			//Regular TCPCServer setup
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();

			BufferedReader inFromClient =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToClient = new PrintWriter(clientSocket.getOutputStream());

			//Server execution
			while(true){
				System.out.println("Waiting....");

				clientMessage = inFromClient.readLine();
				
				//if encryption setup message
				if(clientMessage.contains("DH-ES:")){
					System.out.println("Encryption Setup: "+clientMessage);
					String[] parts = clientMessage.split(":");
					prime= new Integer(Integer.parseInt(parts[1]));
					base = new Integer(Integer.parseInt(parts[2]));
					clientResult = new Integer(Integer.parseInt(parts[3]));

					//generate b and B
					serverResult = new Integer(calculate());
					outToClient.println("ServerResult:"+serverResult);
					outToClient.flush();

				}
				else{

					//System.out.println(clientMessage+secretKey);
					System.out.println("Recieved Encrpyted Message: "+clientMessage);
					String decryptedMessage = decrypt(clientMessage,secretKey);
					
					System.out.println(decryptedMessage);

					String encryptedMessage = encrypt(decryptedMessage,secretKey);
					//String sendMessage = "ServerACK "+decryptedMessage;
					System.out.println("Message Decrypted and ACK: "+encryptedMessage);
					outToClient.println(encryptedMessage);
					outToClient.flush();
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public int calculate(){
		//random number between 1-10
		Random rand = new Random();
		//b = rand.nextInt(9) + 1;
		b = 15;
		//create result
		int result = (base^b) % prime;
		
		//create key
		secretKey = (clientResult^b) % prime;
		System.out.println(secretKey);
		return result;
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