package qqserver.Service;

import commonClass.Message;
import commonClass.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * Karl Rules!
 * 2022/12/17
 *
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    public String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("keep connecting whit "+userId+ " Client ");
            try {
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Message message = (Message) ois.readObject();

                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
//                    �����������������˼
//                    ��client���Ƕ��socket��һ��
//                    ��server�˾͸ĳ�Ĭ��һ���û�һ��thread��
                    System.out.println(message.getSender()+" need online users");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    Message message1 = new Message();
                    message1.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setGetter(message.getSender());

//                    д������
                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(message1);


                }else {
                    System.out.println("other status");
                }


            } catch (Exception e) {
                System.out.println("ServerConnectClientThread �������");
            }
        }
    }
}
