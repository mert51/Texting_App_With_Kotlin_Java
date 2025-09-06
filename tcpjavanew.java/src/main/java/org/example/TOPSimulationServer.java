package org.example;

import java.io.*;
import java.net.*;

public class TOPSimulationServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("TCP Server started on port 8081...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start(); // ClientHandler'ı aşağıda tanımladık
        }
    }
}

// ClientHandler sınıfını ekliyoruz
class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                out.println("Server reply: " + inputLine);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
