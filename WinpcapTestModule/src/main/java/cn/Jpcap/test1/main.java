package cn.Jpcap.test1;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

import java.io.IOException;

/**
 * Created by lenovo on 2017/12/3.
 */
public class main {
    public static void main(String[] args) throws IOException {
        // 获得网络设备列表
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        NetworkInterface ni = null;
        String str = "";
        JpcapCaptor jpcap;
        for (int i = 0; i < devices.length; i++) {
            ni = devices[i];
            for (int j = 0; j < ni.addresses.length; j++) {
                // 一个网卡可能有多个地址,获得本机IP地址
                str = ni.addresses[j].address.toString();
            }
            // 创建卡口上的抓取对象
            try {
                jpcap = JpcapCaptor.openDevice(ni, 65535, true, 20);
                // 创建对应的抓取线程并启动
                jpcap.loopPacket(-1, new getPacket(str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print(str);
    }
}
