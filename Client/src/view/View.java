package view;

import qqclient.service.UserClientService;
import utils.Utility;

import java.io.IOException;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class View {

    private boolean loop = true;
    private String key = "";
    private UserClientService userClientService = new UserClientService();//用于登录服务器，注册

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new View().mainMenu();
        //运行多个 在edit configuration 里面的 modify option 选择allow multiple instances
    }

    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("\t\t   welcome to the first menu");
            System.out.println("\t\t 1 login");
            System.out.println("\t\t 9 exit");
            System.out.println("your choice");

            key = Utility.readString(1);

            switch (key) {
                case "1":
//                    System.out.println("login");
                    System.out.println("id?");
                    String userId = Utility.readString(50);
                    System.out.println("pwd");
                    String pwd = Utility.readString(50);
//                    下面验证是否合法
                    if (userClientService.checkUser(userId,pwd)) {
                        System.out.println("\t\t   welcome, user " + userId+" to login");

                        while (loop) {
                            System.out.println("welcome to the second menu user" + userId);
                            System.out.println("\t\t 1 all user online");
                            System.out.println("\t\t 2 send messages to all users");
                            System.out.println("\t\t 3 a private message");
                            System.out.println("\t\t 4 send a file");
                            System.out.println("\t\t 9 exit");
                            System.out.println("\t\t your choice: ");
                            key = Utility.readString(1);

                            switch (key) {
                                case "1":
                                    System.out.println("all user online");
                                    break;
                                case "2":
                                    System.out.println("send messages to all users");
                                    break;
                                case "3":
                                    System.out.println("a private message");
                                    break;
                                case "4":
                                    System.out.println("send a file");
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                            }

                        }

                    } else {
                        System.out.println("wrong input");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }

        }

    }
}
