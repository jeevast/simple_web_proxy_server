package testPrint;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class WebServerJava {
	
	
		
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket();
		//int socketPort = Integer.parseInt(args[0]);
		serverSocket.bind(new InetSocketAddress(4366));
		
		for(;;)
		{
			try(Socket m = serverSocket.accept())
			{
				byte request[] = new byte[65535];
				
				
				
			
					int size = 65535;
					
					byte a[] = new byte[size];
					
					int s = 0, q = size, start = 0, l = 0;
					
					if(s == -1)
					{
						l = 0;
					}
					
					l = s;
					
					q = q + l;
					
					byte[] theRequest = new byte[q];
					
					
					try
					{
					
				
				 while(s!=-1 )
				 {
					s = m.getInputStream().read(a, 0, a.length);
					theRequest = Arrays.copyOfRange(a, start, a.length);
					for(int i = 0; i < theRequest.length; i++)
						System.out.print((char)theRequest[i]);
					start = start + s;
					
					
				 }
					 
					
					
				}
				
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}