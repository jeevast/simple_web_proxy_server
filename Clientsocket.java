package testPrint;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Arrays;

public class Clientsocket {
	
	
	
	
	
	
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		try
		{
	
		System.out.println("socket is connected");
		
		InetAddress lookup[] = InetAddress.getAllByName("www.njit.edu");
		for(int h = 0; h < lookup.length; h++)
			System.out.println(lookup[h]);
		Socket example = new Socket(lookup[0], 80);
		
		example.getOutputStream().write(("GET " +"/ " + "HTTP/1.1\r\n" +  "Host: " + "www.njit.edu" + "\r\n" +  "Connection: close\r\n\r\n").getBytes("UTF-8"));
		
		int size = 500;
		byte storer[] = new byte[size];
		int start = 0;
		int end = size;
		boolean checker = true;
		int s = 0;
		int r = 0;
		
		while(s!=-1)
		{
			example.getInputStream().skip(start);
			
			 s = example.getInputStream().read(storer,0 , size);
			 for(int i = 0; i < s; i++)
			 System.out.print((char)storer[i]);
			 
			 start = start+s;
		}
		
	
	
		
	}
	 
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
		
		
		
		
		
		
		
		
			
               
                
               
	}

}
