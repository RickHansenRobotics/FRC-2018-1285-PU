package org.usfirst.frc.team1285.robot.utilities;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.json.JSONObject;
//import org.json.JSONObject;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * This class handles the data collected from the phone over the Universal Datagram Protocol
 * 
 * @author RickHansenRobotics
 * @since 2017-10-02
 *
 */


/**
 *
 */
public class UDP extends Thread{
	
	private static final int DATA_PORT = 5808;
	private static final int STREAM_PORT = 5809;
	
	private static String data = "-1";
	private static Mat frame = new Mat(640, 480, CvType.CV_8UC3, new Scalar(0,0,0));
	private boolean serverON = true;
	byte[] message = new byte[1500];
	DatagramPacket p;
	DatagramSocket s;
	DatagramPacket imagePacket;
	DatagramSocket imageSocket;
	DatagramPacket testPD;
	DatagramSocket testSD;
	
	byte[] messageByte = new byte[1000];
	boolean end = false;
	public static String dataString = "";
	private static JSONObject json;
    
    public UDP() throws IOException{
    	p = new DatagramPacket(message, message.length);
    	s = new DatagramSocket(DATA_PORT);
    	imagePacket = new DatagramPacket(message, message.length);
    	imageSocket = new DatagramSocket(STREAM_PORT);
    	testPD = new DatagramPacket(message, message.length);
    	testSD = new DatagramSocket(5807);
    }
    
    public void run(){
    	while(serverON){
	    	try { //tracking
				s.receive(p);
//				imageSocket.receive(imagePacket);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	data = new String(message, 0, p.getLength());
	    	
	    	json = new JSONObject(data);
//	    	frame.put(0, 0, p.getData());
    	}
    	s.close();
    	imageSocket.close();
    }
    
    public static String getData(){
    	return data;
    }
    
    public static double getNumber(String key){
    	if(json != null)
    		if(json.has(key))
    			return json.getDouble(key);
    		else
    			return -2542056;
    	else 
    		return -2542056;
    }
    
    public static String getDataString(){
    	return dataString;
    }
    
    public static Mat getFrame(){
    	return frame;
    }
    
    
}



