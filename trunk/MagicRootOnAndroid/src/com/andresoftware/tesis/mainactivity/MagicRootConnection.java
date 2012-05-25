package com.andresoftware.tesis.mainactivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Message;

public class MagicRootConnection implements Runnable {
	public Socket socket = null;
	public DataOutputStream dataOutputStream = null;
	public DataInputStream dataInputStream = null;
	private MagicRootActivity magicRootActivity;
	protected static final int MSG_ID = 01337;
	//----------------------------------------------------------------------------------
	public MagicRootConnection(MagicRootActivity magicRootActivity) {
		this.magicRootActivity=magicRootActivity;
	}
	//----------------------------------------------------------------------------------
	public void run() {

		try {
			socket = new Socket("10.0.2.2", 5557);
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataInputStream = new DataInputStream(socket.getInputStream());
			while(true){
				Message m = new Message();
                m.what = MSG_ID;
				String dataInput = dataInputStream.readUTF();
				magicRootActivity.setMessage(dataInput);
				magicRootActivity.getHandler().sendMessage(m);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally{
			if (socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dataOutputStream != null){
				try {
					dataOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dataInputStream != null){
				try {
					dataInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//----------------------------------------------------------------------------------
	public void sendCommandToServer(String outData) {

		try {
			dataOutputStream.writeUTF(outData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error enviando Comando!!");
		}
	}
}


