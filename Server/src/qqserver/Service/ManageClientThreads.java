package qqserver.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Karl Rules!
 * 2022/12/17
 * 管理客户端的线程
 */
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();
    public static void addClientThread(String userId,ServerConnectClientThread serverConnectClientThread){
        hm.put(userId,serverConnectClientThread);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hm.get(userId);
    }

    //
    public static String getOnlineUser(){
        //因为他前面写的是拿userId 当key 所以这里他也可以这么取，实际上标准的应该是取value里面的userId,然后去重
//        因为key不能用userId 不然一个user只能有一个Thread
        Set<String> strings = hm.keySet();
        StringBuilder onlineUserList = new StringBuilder();

        for (String string : strings) {
//            StringBuilder 的连加写法
            onlineUserList.append(string.toString()).append(" ");
        }
        return onlineUserList.toString();
    }
}
