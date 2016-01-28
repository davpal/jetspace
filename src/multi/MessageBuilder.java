package multi;

import java.net.InetAddress;

class MessageBuilder {
    Message message;

    public MessageBuilder() {
        message = new Message();
    }

    public void extend(int amount) {
        message.setSize(message.getSize() + amount);
    }

    public MessageBuilder code(byte code) {
        message.setCode(code);
        message.setSize(1);
        return this;
    }

    public MessageBuilder pid(byte pid) {
        message.setPid(pid);
        extend(1);
        return this;
    }

    public MessageBuilder name(String name) {
        message.setName(name);
        extend(name.length());
        return this;
    }

    public MessageBuilder position(double x, double y) {
        message.setX((int)x);
        message.setY((int)y);
        extend(8);
        return this;
    }

    public MessageBuilder mousePosition(int x, int y) {
        message.setMouseX(x);
        message.setMouseY(y);
        extend(8);
        return this;
    }

    public MessageBuilder shifts(double dx, double dy) {
        message.setDx((int)dx);
        message.setDy((int)dy);
        extend(8);
        return this;
    }

    public MessageBuilder source(InetAddress address) {
        message.setSource(address);
        return this;
    }

    public Message build() {
        return message;
    }
}
