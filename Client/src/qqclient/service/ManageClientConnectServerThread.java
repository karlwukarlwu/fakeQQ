package qqclient.service;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class ManageClientConnectServerThread {
    //key���û�id, value���߳�
    protected static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();

    //��ĳ���̼߳��뼯��
    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }

    //ͨ��userId �õ���Ӧ�߳�
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }

}
