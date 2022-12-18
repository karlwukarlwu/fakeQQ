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

    //服务器验证功能
    //验证成功了并将用户加入thread 和thread对应的map
    public boolean checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        boolean b = false;
//      这里是拿到用户的id和密码
        u.setUserId(userId);
        u.setPasswd(pwd);

//        从这里开始发送对象，把id和密码送到服务器去
        socket=new Socket(InetAddress.getLocalHost(),9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);
//        socket.shutdownOutput();
        //不知道为什么这里不能加上shutdownOutput()
//        读取回复的信息
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message)ois.readObject();

        //通过外面造的接口的属性验证是不是登录成功了
        if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){

//        这个叫用户线程
//            创建一个和服务器保持通信的线程
//  加入一个新的Thread
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            ccst.start();
            //线程池，但是他没讲，所以这里用集合
//            放到一个线程管理的hashmap去
//  加入map
//                    他这里写的有问题 他如果这么写 包括Server 他这么写的后果就是一个主机只能有一个thread 因为id相同
//                    hashmap会去重
            ManageClientConnectServerThread.addClientConnectServerThread(userId,ccst);

            b=true;//登录成功把返回值改成true 最好是所有的都成功了再设置为true

        }else {
            socket.close();
            //登录失败把socket关闭
        }
        return b;
    }
//    请求在线用户
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
//            他那个写法是可以 对应的是一个用户多个线程，
//            他的开始的hashmap写法的办法最多一台主机一个线程，因此他那么写没有办法实现他的这样的写法


//          我这种写法是一个客户端一个thread的
//            OutputStream os = socket.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            oos.writeObject(message);
                    ;
        } catch (IOException e) {
            System.out.println("client UCS 有问题");
        }


    }

}
