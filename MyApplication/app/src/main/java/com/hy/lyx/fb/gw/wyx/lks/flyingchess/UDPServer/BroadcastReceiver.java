package com.hy.lyx.fb.gw.wyx.lks.flyingchess.UDPServer;

import com.hy.lyx.fb.gw.wyx.lks.flyingchess.dataPack.*;

import java.net.DatagramSocket;

/**
 * Created by BingF on 2016/5/15.
 */
public class BroadcastReceiver implements Runnable{
    private DataPackUdpSocket receiveSocket = null;
    private boolean isRunning = true;
    private final static int port = 6667;
    private UDPServer parent = null;

    public BroadcastReceiver(UDPServer parent){
        try{
            this.parent = parent;
            this.receiveSocket = new DataPackUdpSocket(new DatagramSocket(port));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        while (isRunning){
            try{
                if(this.receiveSocket == null)
                    continue;
                DataPack dataPack = this.receiveSocket.receive();
                parent.dataPackReceived(dataPack);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void close(){
        isRunning = false;
    }

}