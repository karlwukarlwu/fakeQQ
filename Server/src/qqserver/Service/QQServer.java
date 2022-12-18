package qqserver.Service;

import commonClass.Message;
import commonClass.MessageType;
import commonClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class QQServer {
    private ServerSocket ss = null;

//    ConcurrentHashMap<String,User> �̰߳�ȫ��hashMap ����û��
//    
    private static HashMap<String,User> validUser = new HashMap<>();

    static {
        validUser.put("100",new User("100","123"));
        validUser.put("200",new User("200","123"));
        validUser.put("300",new User("300","123"));
        validUser.put("400",new User("400","123"));
        validUser.put("500",new User("500","123"));
        validUser.put("600",new User("600","123"));
        validUser.put("700",new User("700","123"));
        validUser.put("800",new User("800","123"));
    }
    private boolean checkUser(String userId,String pwd){
        User user = validUser.get(userId);
        if(user==null){
            return false;
        }
        if(!user.getPasswd().equals(pwd)){
            return false;
        }
        return true;
    }

    public QQServer() {
        System.out.println("server is on port 9999");
        try {
//            ��ʵ���԰Ѷ˿�д�������ļ���
            ss = new ServerSocket(9999);
            while (true) {
                //��ĳ���ͻ������ӿۣ���������
                Socket accept = ss.accept();//���û�� socket�������ͻ�����������
                //�õ�socket �����Ķ���������
                ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());

                //��ΪҪ���� ���������
                OutputStream outputStream = accept.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                User u = (User) ois.readObject();
                //����һ��Message �ظ��ͻ���
                Message message = new Message();
                //�ٶ� id="100" ����="123456" �ĳ�hashmap��
                if (checkUser(u.getUserId(), u.getPasswd())) {
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //��message �ظ�
                    oos.writeObject(message);
                    //�������˼���Ѿ��ظ���¼�ɹ��ˣ���������ʼ����Ӧ���߳�
                    ServerConnectClientThread scct = new ServerConnectClientThread(accept, u.getUserId());
                    //��������߳�
                    scct.start();
                    //�����Ӧ���߳�hashMap
//                    ������д�������� �������ôд ����client ����ôд�ĺ������һ������ֻ����һ��thread ��Ϊid��ͬ
//                    hashmap��ȥ��
                    ManageClientThreads.addClientThread(u.getUserId(), scct);

                    //
                } else {
                    System.out.println("user id= "+ u.getUserId()+" login failed");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    accept.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�������˳� �ر���Դ
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
