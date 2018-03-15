package org.hbhk.aili.route.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class IpMonitor {

	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean isHostConnectable(String host) {
		Socket socket = new Socket();
		try {
			String[] arr = host.split(":");
			socket.connect(new InetSocketAddress(arr[0], Integer
					.parseInt(arr[1])));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	public static boolean isHostReachable(String host, Integer timeOut) {
        try {
            return InetAddress.getByName(host).isReachable(timeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
	public static void main(String[] args) {
		System.out.println(isHostConnectable("10.10.0.11", 8080));
		System.out.println(isHostConnectable("10.10.0.11:8080"));
	}
}
