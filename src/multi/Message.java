package multi;

import java.net.InetAddress;

/**
 * Created by winio_000 on 2015-11-29.
 */
public class Message {
    public static final byte JOIN = 11;
    public static final byte ACCEPT = 22;
    public static final byte SYNC = 33;
    public static final byte MOVE = 44;
    public static final byte SHOOT = 55;
    public static final byte HIT = 66;
    public static final byte DEAD = 77;
    public static final byte QUIT = 88;
    public static final byte PICK = 99;
    public static final byte STOP = 111;
    public static final int MAX_SIZE = 1024;

    private byte code;
    private byte pid;
    private String name;
    private int x, y, mouseX, mouseY, dx, dy;
    private int size;
    private InetAddress sourceAddress;

    Message() {

    }

    public void setCode(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setPid(byte pid) {
        this.pid = pid;
    }

    public byte getPid() {
        return pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setX(int  x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setMouseX(int x) {
        mouseX = x;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseY(int y) {
        mouseY = y;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setSource(InetAddress address) {
        sourceAddress = address;
    }

    public InetAddress getSource() {
        return sourceAddress;
    }

    public static class Builder {
        Message message;

        public Builder() {
            message = new Message();
        }

        public void extend(int amount) {
            message.setSize(message.getSize() + amount);
        }

        public Builder code(byte code) {
            message.setCode(code);
            message.setSize(1);
            return this;
        }

        public Builder pid(byte pid) {
            message.setPid(pid);
            extend(1);
            return this;
        }

        public Builder name(String name) {
            message.setName(name);
            extend(name.length());
            return this;
        }

        public Builder position(double x, double y) {
            message.setX((int)x);
            message.setY((int)y);
            extend(8);
            return this;
        }

        public Builder mousePosition(int x, int y) {
            message.setMouseX(x);
            message.setMouseY(y);
            extend(8);
            return this;
        }

        public Builder shifts(double dx, double dy) {
            message.setDx((int)dx);
            message.setDy((int)dy);
            extend(8);
            return this;
        }

        public Builder source(InetAddress address) {
            message.setSource(address);
            return this;
        }

        public Message build() {
            return message;
        }
    }
}
