package org.slu.server;

import org.apache.log4j.Logger;
import org.slu.business.MsgProcess;
import org.slu.dao.UsernameDao;

import java.io.*;
import java.net.Socket;

/**
 * 对收到的消息进行简单解析
 * 本class只负责：
 * 1. 将消息以空格分割为String数组，交给business.MsgProcess处理
 * 2. 获得处理结果（String），将结果发回
 *    若处理结果为空，发回 空消息
 */
public class ServerThread extends Thread {
    private Logger logger = Logger.getLogger(ServerThread.class);
    private Socket socket;

    public ServerThread(Socket accept) {
        this.socket = accept;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] b = new byte[1024];
            int msgLen = dataInputStream.read(b);
            String clientMsg = new String(b, 0, msgLen);
            String[] clientMsgs = clientMsg.split(" ");
            logger.info("client:" + clientMsg);
            String serverMsg = "";
            try {
                serverMsg = MsgProcess.msgProcess(clientMsgs);
            } catch (Exception e) {
                serverMsg = e.toString();
                e.printStackTrace();
            }
            if (serverMsg.equals("")) {
                serverMsg = "空消息";
            }
            serverMsg = UsernameDao.getUsernameByQqAcc(clientMsgs[0]) + ":\n" + serverMsg;
            logger.info("server:\n" + serverMsg);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(serverMsg);
            dataOutputStream.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
