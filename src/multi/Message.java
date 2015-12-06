package multi;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

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

    private static byte code = 0;
    private static int X = 0;
    private static int Y = 0;
    private static double angle = 0.0;
    private static ByteBuffer byteBuffer;

    public Message() {

    }

    public Message(byte code, int X, int Y, double angle) {
        this.code = code;
        this.X = X;
        this.Y = Y;
        this.angle = angle;
    }

    public static Message parsePacket(DatagramPacket packet) {
        if (packet.getData() != null) {
            byteBuffer = ByteBuffer.wrap(packet.getData());
            getDataFromPacket(byteBuffer);
        }
        return new Message(code, X, Y, angle);
    }

    public DatagramPacket toPacket() {
        byteBuffer = ByteBuffer.allocate(MAX_SIZE);
        DatagramPacket datagramPacket = getDataFromMessage(byteBuffer);
        return datagramPacket;
    }

    private static void getDataFromPacket(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining())
            code = byteBuffer.get();
        if (byteBuffer.hasRemaining())
            X = byteBuffer.getInt();
        if (byteBuffer.hasRemaining())
            Y = byteBuffer.getInt();
        if (byteBuffer.hasRemaining())
            angle = byteBuffer.getDouble();
    }

    private DatagramPacket getDataFromMessage(ByteBuffer byteBuffer) {
        byteBuffer.put(code);
        byteBuffer.putShort((short)X);
        byteBuffer.putShort((short)Y);
        byteBuffer.putShort((short)angle);
        return new DatagramPacket(byteBuffer.array(), byteBuffer.capacity());
    }

    public byte getCode() {
        return code;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public double getAngle() {
        return angle;
    }
}
