import java.io.*;
import java.net.*;


class TCPClient2{
        public static void main(String argv[]) throws Exception  {
                String sentence;
                String modifiedSentence;
                BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
                Socket clientSocket = new Socket("172.17.1.20", 1908);

                //      System.out.println("Testing");
                        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                for(int i=0; i<8; i++){ 
                        Thread.sleep(2000);
                        sentence = "Hello World";
                        //sentence = inFromUser.readLine();
                        outToServer.writeBytes(sentence + '\n');
                //      System.out.println("here");
                        //modifiedSentence = inFromServer.readLine();
                        //System.out.println("FROM SERVER: " + modifiedSentence);
                }
                clientSocket.close();
        }
}
