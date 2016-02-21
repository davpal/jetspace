package multi;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkInterfacePool {
    static ArrayList<InetAddress> interfaces = new ArrayList<>();

    private NetworkInterfacePool() {}

    public static ArrayList<InetAddress> getInterfaces(){
        if(interfaces.isEmpty())
            retrieveInterfaces();
        return interfaces;
    }

    private static void retrieveInterfaces(){
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
            for (InetAddress addr : Collections.list(addresses)) {
                if(addr instanceof Inet4Address) interfaces.add(addr);
            }
        }
    }
}
