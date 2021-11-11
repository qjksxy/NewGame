package org.slu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 只负责接收请求，收到请求后开新进程处理，继续监听
 */
public class MainServer {
    private static final int port = 7700;

    public static void main(String[] args) {
        // 连续报错十次终止程序
        int exceptionCount = 0;
        boolean exceptionFlag = false;
        for (int i = 0; i < 800; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket accept = serverSocket.accept();
                ServerThread serverThread = new ServerThread(accept);
                serverThread.start();
                serverSocket.close();
                exceptionFlag = false;
            } catch (IOException e) {
                if (!exceptionFlag) {
                    exceptionFlag = true;
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
