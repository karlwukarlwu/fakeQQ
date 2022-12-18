package qqclient.service;

import commonClass.Message;
import commonClass.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }
//    ������ô��ǵ��Ұ�

    @Override
    public void run() {
        //����߳�Ҫһֱ���� ������whileѭ��
        while (true) {
            System.out.println("client waiting for message for server");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
//                �̴߳�socket�����ò������ݣ��̻߳����������
//                �����message�����ͽ����ж�
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    // default return format "100 200 300 400"
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\t\t online users");
                    for (String s :
                            onlineUsers) {
                        System.out.println("user " + s + " online");
                    }

                } else {
                    System.out.println("other status");
                }
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
