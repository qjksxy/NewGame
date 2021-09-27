package org.slu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final int port = 7700;

    public static void main(String[] args) {
        int exceptionCount = 0;
        boolean eb = false;
        for (int i = 0; i < 800; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket accept = serverSocket.accept();
                ServerThread serverThread = new ServerThread(accept);
                serverThread.start();
                serverSocket.close();
                eb = false;
            } catch (IOException e) {
                if (!eb) {
                    eb = true;
                    exceptionCount = 0;
                } else {
                    exceptionCount++;
                }
                if (exceptionCount > 10) {
                    break;
                }
                e.printStackTrace();
            }
        }
    }
}
