package multi;

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
        extend(1);
        return this;
    }

    public Message build() {
        return message;
    }
}
