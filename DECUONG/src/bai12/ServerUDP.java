/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai12;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Arrays;

/**
 *
 * @author TIEN
 */
public class ServerUDP {
    private DatagramSocket socket;
    private final static byte[] BUFFER = new byte[4*1024]; // bộ nhớ đệm đọc gói tin từ client
    
    public final static int PORT = 8080;
    
    public ServerUDP(){
        try {
            socket = new DatagramSocket(PORT);
            System.out.println("ServerUDP running in port " + PORT);
            while(true){
                Arrays.fill(BUFFER, (byte) 0); // default empty 
                DatagramPacket incomming = new DatagramPacket(BUFFER, BUFFER.length);
                socket.receive(incomming);
                SocketAddress address = incomming.getSocketAddress();
                String cmd = new String(incomming.getData()).trim();
                if(cmd.equalsIgnoreCase(Constant.ACL_SUM)){
                    String strN = recivedDataFromClient();
                    int n = Integer.parseInt(strN);
                    sendDataToClient(String.valueOf(Func.sum(n)).getBytes(), address);
                }else if(cmd.equalsIgnoreCase(Constant.ACL_FACTOR)){
                    String strN = recivedDataFromClient();
                    int n = Integer.parseInt(strN);
                    sendDataToClient(Func.factor(n).getBytes(), address);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String recivedDataFromClient(){
        try {
            Arrays.fill(BUFFER, (byte) 0); // default empty 
            DatagramPacket incomming = new DatagramPacket(BUFFER, BUFFER.length);
            // đợi gói tin từ client
            socket.receive(incomming);
            return new String(incomming.getData()).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void sendDataToClient(byte[] data, SocketAddress address){
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
        
    
    public static void main(String[] args) {
        new ServerUDP();
    }
}
