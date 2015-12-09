package multi;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class NetworkInterfacePool {
    static ArrayList<InetAddress> interfaces = new ArrayList<>();

    public static ArrayList<InetAddress> getInterfaces(){
        if(interfaces.isEmpty())
            retriveInterfaces();
        return interfaces;
    }

    private static void retriveInterfaces(){
        Enumeration<NetworkInterface> nics = null;
        try {
            nics = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
        }

        while (nics.hasMoreElements()) {
            NetworkInterface nic = nics.nextElement();
            try {
                if (nic.isLoopback()) {
                    continue;
                }
            } catch (Exception e) {

            }
            Enumeration<InetAddress> addresses = nic.getInetAddresses();
            if (!addresses.hasMoreElements()) {
                continue;
            }
            InetAddress address = addresses.nextElement();
            if (!(address instanceof Inet4Address)) {
                continue;
            }
            interfaces.add(address);
        }
    }
}
