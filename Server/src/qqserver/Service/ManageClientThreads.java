package qqserver.Service;

import java.util.HashMap;

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
}
