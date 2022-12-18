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
        //����߳�Ҫһֱ���� ������whileѭ��
        while (true){
            System.out.println("client waiting for message for server");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
//                �̴߳�socket�����ò������ݣ��̻߳����������
            } catch (
                    Exception e) {
                System.out.println("ClientConnectServerThread �������");
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
