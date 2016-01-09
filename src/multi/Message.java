package multi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    private static InetAddress BROADCAST;

    private byte code = 0;
    private int X = 0;
    private int Y = 0;
    private double angle = 0.0;
    private static ByteBuffer byteBuffer;

    public Message() {

    }

    public Message(byte code, int X, int Y, double angle) {
        this.code = code;
        this.X = X;
        this.Y = Y;
        this.angle = angle;
        try {
            BROADCAST = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    public static Message parsePacket(DatagramPacket packet) {
        byte code = 0;
        int X = 0, Y = 0;
        double angle = 0.0;
        if (packet.getData() != null) {
            byteBuffer = ByteBuffer.wrap(packet.getData());
            if (byteBuffer.hasRemaining())
                code = byteBuffer.get();
            if (byteBuffer.hasRemaining())
                X = byteBuffer.getInt();
            if (byteBuffer.hasRemaining())
                Y = byteBuffer.getInt();
            if (byteBuffer.hasRemaining())
                angle = byteBuffer.getDouble();
        }
        return new Message(code, X, Y, angle);
    }

    public DatagramPacket toPacket() {
        byteBuffer = ByteBuffer.allocate(MAX_SIZE);
        DatagramPacket datagramPacket = getDataFromMessage(byteBuffer);
        return datagramPacket;
    }

    private static void getDataFromPacket(ByteBuffer byteBuffer) {

    }

    private DatagramPacket getDataFromMessage(ByteBuffer byteBuffer) {
        byteBuffer.put(code);
        byteBuffer.putShort((short)X);
        byteBuffer.putShort((short)Y);
        byteBuffer.putDouble(angle);
        return new DatagramPacket(byteBuffer.array(), byteBuffer.capacity(),
            BROADCAST, MultiplayerConfiguration.SEND_PORT);
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
