package qqclient.service;

import commonClass.Message;
import commonClass.MessageType;
import commonClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class UserClientService {

    private User u = new User();
    private Socket socket;

    //��������֤����
    //��֤�ɹ��˲����û�����thread ��thread��Ӧ��map
    public boolean checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        boolean b = false;
//      �������õ��û���id������
        u.setUserId(userId);
        u.setPasswd(pwd);

//        �����￪ʼ���Ͷ��󣬰�id�������͵�������ȥ
        socket=new Socket(InetAddress.getLocalHost(),9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);
//        socket.shutdownOutput();
        //��֪��Ϊʲô���ﲻ�ܼ���shutdownOutput()
//        ��ȡ�ظ�����Ϣ
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message)ois.readObject();

        //ͨ��������Ľӿڵ�������֤�ǲ��ǵ�¼�ɹ���
        if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){

//        ������û��߳�
//            ����һ���ͷ���������ͨ�ŵ��߳�
//  ����һ���µ�Thread
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            ccst.start();
            //�̳߳أ�������û�������������ü���
//            �ŵ�һ���̹߳����hashmapȥ
//  ����map
//                    ������д�������� �������ôд ����Server ����ôд�ĺ������һ������ֻ����һ��thread ��Ϊid��ͬ
//                    hashmap��ȥ��
            ManageClientConnectServerThread.addClientConnectServerThread(userId,ccst);

            b=true;//��¼�ɹ��ѷ���ֵ�ĳ�true ��������еĶ��ɹ���������Ϊtrue

        }else {
            socket.close();
            //��¼ʧ�ܰ�socket�ر�
        }
        return b;
    }
//    ���������û�
    public void onlineFriendList(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());

        try {
            ClientConnectServerThread ccst = ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId());
            Socket ccstSocket = ccst.getSocket();
            OutputStream os = ccstSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(message);
//            ���Ǹ�д���ǿ��� ��Ӧ����һ���û�����̣߳�
//            ���Ŀ�ʼ��hashmapд���İ취���һ̨����һ���̣߳��������ôдû�а취ʵ������������д��


//          ������д����һ���ͻ���һ��thread��
//            OutputStream os = socket.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            oos.writeObject(message);
                    ;
        } catch (IOException e) {
            System.out.println("client UCS ������");
        }


    }

}
