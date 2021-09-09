package org.slu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final int port = 42060;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket accept = serverSocket.accept();
                ServerThread serverThread = new ServerThread(accept);
                serverThread.start();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
