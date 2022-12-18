package qqserver.Service;

import commonClass.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Karl Rules!
 * 2022/12/17
 *
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    public String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("keep connecting whit "+userId+ " Client ");
            try {
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Message message = (Message) ois.readObject();


            } catch (Exception e) {
                System.out.println("ServerConnectClientThread ’‚¿Ô≥ˆ¥Ì");
            }
        }
    }
}
