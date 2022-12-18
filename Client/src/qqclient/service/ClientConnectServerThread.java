package qqclient.service;

import commonClass.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class ClientConnectServerThread extends Thread{
    private Socket socket;
    public ClientConnectServerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        //这个线程要一直持续 所以用while循环
        while (true){
            System.out.println("client waiting for message for server");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
//                线程从socket里面拿不到数据，线程会阻塞在这里，
            } catch (
                    Exception e) {
                System.out.println("ClientConnectServerThread 这里出错");
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
