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
    public static final int MAX_SIZE = 1024;

    private byte code = 0;
    private int X = 0;
    private int Y = 0;
    private double angle = 0.0;

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

    public Message() {

    }

    public Message(byte code, int X, int Y, double angle) {
        this.code = code;
        this.X = X;
        this.Y = Y;
        this.angle = angle;
    }


    public Message parsePacket(DatagramPacket packet) {
        if (packet.getData() != null) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(packet.getData());
            getDataFromPacket(byteBuffer);
        }
        return new Message(code, X, Y, angle);
    }

    private void getDataFromPacket(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining())
            code = byteBuffer.get();
        if (byteBuffer.hasRemaining())
            X = byteBuffer.getInt();
        if (byteBuffer.hasRemaining())
            Y = byteBuffer.getInt();
        if (byteBuffer.hasRemaining())
            angle = byteBuffer.getDouble();
    }

}
