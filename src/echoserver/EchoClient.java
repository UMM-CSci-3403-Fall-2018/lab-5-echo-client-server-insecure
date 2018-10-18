package echoserver;

import java.net.*;
import java.io.*;

public class EchoClient {
  public static final int portNumber = 6013;

public static void main(String[] args) throws IOException {
  String server;
  // Use "127.0.0.1", i.e., localhost, if no server is specified.
  if (args.length == 0) {
    server = "127.0.0.1";
  } else {
    server = args[0];
  }

  try {
    // Connect to the server
    Socket socket = new Socket(server, portNumber);
    //System.out.println("Connected to socket");

    // Get the input stream so we can read from that socket
    OutputStream output = socket.getOutputStream();
    InputStream input = socket.getInputStream();
    //System.out.println("Initalize output stream");

    // Print all the input we receive from the server
    int byte1;
    int byte2;
    while ((byte1 = System.in.read()) != -1) {
      output.write(byte1);
      output.flush();
      byte2 = input.read();
      System.out.write(byte2);
      // System.out.print((char)byte2);
    }


    //System.out.println("We should be done!");

    // Close the socket when we're done reading from it
    socket.close();

  // Provide some minimal error handling.
  } catch (ConnectException ce) {
    System.out.println("We were unable to connect to " + server);
    System.out.println("You should make sure the server is running.");
  } catch (IOException ioe) {
    System.out.println("We caught an unexpected exception");
    System.err.println(ioe);
  }
}
}
