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

//    ConcurrentHashMap<String,User> 线程安全的hashMap 但是没讲
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
//            其实可以把端口写在配置文件里
            ss = new ServerSocket(9999);
            while (true) {
                //和某个客户端连接扣，继续监听
                Socket accept = ss.accept();//如果没有 socket过来，就会阻塞在这里
                //得到socket 关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());

                //因为要返回 创造输出流
                OutputStream outputStream = accept.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                User u = (User) ois.readObject();
                //创建一个Message 回复客户端
                Message message = new Message();
                //假定 id="100" 密码="123456" 改成hashmap了
                if (checkUser(u.getUserId(), u.getPasswd())) {
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //将message 回复
                    oos.writeObject(message);
                    //这里的意思是已经回复登录成功了，接下来开始做对应的线程
                    ServerConnectClientThread scct = new ServerConnectClientThread(accept, u.getUserId());
                    //启动这个线程
                    scct.start();
                    //放入对应的线程hashMap
//                    他这里写的有问题 他如果这么写 包括client 他这么写的后果就是一个主机只能有一个thread 因为id相同
//                    hashmap会去重
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
            //服务器退出 关闭资源
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
