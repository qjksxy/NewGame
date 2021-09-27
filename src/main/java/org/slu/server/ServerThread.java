package org.slu.server;

import org.apache.log4j.Logger;
import org.slu.business.MsgProcess;
import org.slu.dao.UsernameDao;

import java.io.*;
import java.net.Socket;

//processing:处理
public class ServerThread extends Thread{
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
            String serverMsg = MsgProcess.msgProcess(clientMsgs);
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
