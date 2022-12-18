package qqclient.service;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class ManageClientConnectServerThread {
    //key是用户id, value是线程
    protected static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();

    //将某个线程加入集合
    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }

    //通过userId 得到对应线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }

}
