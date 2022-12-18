package qqclient.service;

import commonClass.Message;
import commonClass.MessageType;
import commonClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    //到服务器验证
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
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            ccst.start();
            //线程池，但是他没讲，所以这里用集合
//            放到一个线程管理的hashmap去
            ManageClientConnectServerThread.addClientConnectServerThread(userId,ccst);

            b=true;//登录成功把返回值改成true 最好是所有的都成功了再设置为true

        }else {
            socket.close();
            //登录失败把socket关闭
        }


        return b;

    }
}
