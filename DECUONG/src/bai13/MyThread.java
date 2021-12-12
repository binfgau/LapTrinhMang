/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author TIEN
 */
public class MyThread implements Runnable{
    
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public MyThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    
    @Override
    public void run() {
        try {
            while(true){
            String cmd = in.readUTF();
            if(cmd.equalsIgnoreCase(Constant.ACL_UOCSO)){
                int n = in.readInt();
                out.writeUTF(Func.uocso(n));
                out.flush();
                
            }else if(cmd.equalsIgnoreCase(Constant.ACL_UOCSOLASONGUYENTO)){
                int n = in.readInt();
                out.writeUTF(Func.uocsolasonguyento(n));
                out.flush();
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
