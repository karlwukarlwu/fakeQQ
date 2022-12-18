package commonClass;

import java.io.Serial;
import java.io.Serializable;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID= 1031313L;

    private String sender;
    private String getter;
    private String content;
    private String sendTime;
    private String mesType;//消息类型

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
