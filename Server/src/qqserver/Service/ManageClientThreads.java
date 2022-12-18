package qqserver.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Karl Rules!
 * 2022/12/17
 * ����ͻ��˵��߳�
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
        //��Ϊ��ǰ��д������userId ��key ����������Ҳ������ôȡ��ʵ���ϱ�׼��Ӧ����ȡvalue�����userId,Ȼ��ȥ��
//        ��Ϊkey������userId ��Ȼһ��userֻ����һ��Thread
        Set<String> strings = hm.keySet();
        StringBuilder onlineUserList = new StringBuilder();

        for (String string : strings) {
//            StringBuilder ������д��
            onlineUserList.append(string.toString()).append(" ");
        }
        return onlineUserList.toString();
    }
}
