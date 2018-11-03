package testPrint;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Echo_Server {
	
	public static int port=80;
	
	
	private static void dnsLookup(byte[] host,byte[] path, Socket m, int thePort) throws IOException  //dns_lookup for host.
	{
		InetAddress temp = null; 
		try
		{
		
			InetAddress lookup[] = InetAddress.getAllByName(new String(host));
			temp = lookup[0];
			clientSocket(temp, m, path, host, thePort); 
		}
		catch(Exception e)
		{
			m.getOutputStream().write(" INVALID WEB PAGE HAS BEEN REQUESTED\r\n".getBytes("UTF-8"));
			
			m.getOutputStream().close();
			m.close();
			
		}
			
					}
	
	
	
	
	
	private static void clientSocket(InetAddress host_name, Socket m, byte[] path_name, byte[] url, int thePort) throws IOException
	{
		Socket example = null;
		try
		{
		example= new Socket(host_name, port);
		//System.out.println("testttttt--->"+port);
		//System.out.println("urllllllllllllllllllll--->"+"GET " +new String(path_name) + "HTTP/1.1\r\n" +  "Host: " +new String(url)+":"+String.valueOf(thePort)  + "\r\n" +  "Connection: close\r\n\r\n");
		
		if(port==80)
		{
			example.getOutputStream().write(("GET " +new String(path_name) + "HTTP/1.1\r\n" +  "Host: " +new String(url)  + "\r\n" +  "Connection: close\r\n\r\n").getBytes("UTF-8"));
		}
		else
		{
		example.getOutputStream().write(("GET " +new String(path_name) + "HTTP/1.1\r\n" +  "Host: " +new String(url)+":"+String.valueOf(port)  + "\r\n" +  "Connection: close\r\n\r\n").getBytes("UTF-8"));
		
		}
		

		
		int byte_reader = 0;
		 byte reader[] = new byte[1400];
		
		
		while(byte_reader!=-1)
		{
			
			byte_reader = example.getInputStream().read(reader, 0, reader.length);
			System.out.println("+++++++++++++++++++++"+byte_reader);
			if(byte_reader == -1)
			{
				continue;
			}
			else {
				m.getOutputStream().write(reader, 0, byte_reader);
				for(int i = 0; i < reader.length; i++)
					System.out.print((char)reader[i]);
			}
			
			
		}
		
		
		}
		catch(Exception E)
		{
			//m.getOutputStream().write("HTTP/1.1 403 Forbidden".getBytes());
			
			m.getOutputStream().write("HTTP/1.1 400 Bad Request\r\n Content-type: text/plain\r\n\r\n UNKNOWN HOST HAS BEEN REQUESTED\r\n".getBytes());
			
			E.printStackTrace();
		}
	}
	
	
	
	
	
		   
	private static byte[] path_parser(byte[] h, int s, int counter)
	{
		byte path_name[] = new byte[s];
		boolean checker = false;
		
		int a  = counter+10;
		
		int k = 0;
		int m = 0;
		for(; (a<s); a++)
		{
			
			 if(h[a] == '/' ) 
			{
				
				while(!checker)
				{
					path_name[k] = h[a];
					
					if(h[a] == ' ')
						checker = true;
					
					
					k++;
					a++;
					m++;
								
				}
				
			}
			 else
			 {
				 continue;
			 }
			
			{
			
			}
			
		}
		byte[] path_parser_dup = Arrays.copyOf(path_name, m);
		
		return path_parser_dup;
	}
	
	
	
	
	
	
	private static byte[] doParser(byte[] h , int s)
	{
		  int r = 0;
			 int y = 0;
			 boolean checker = true;
			 boolean flag = true;
				
		byte[] dns = new byte[s]; 		
				for(r = 0; (r<s)&&(checker); r++)
				{
					if(h[r] == '\r' && h[r+1] == '\n' && h[r+2] == 'H')
					{
						r = r+8;
						
						while(flag)
						{
						if(h[r]=='\r')
						{
							 
							checker = false;
							flag = false;
						}
						else
						{
							dns[y] = h[r];
							
							y++;
							
							r++;
						}
							}
						} 				
				}
				
				byte[] dns1 = Arrays.copyOf(dns, y);
				byte[] b=null;
				for(int i=0;i<dns1.length;i++)
				{
					
					if((char) dns1[i]==':')
					{
						 b=Arrays.copyOfRange(dns1, i+1, dns1.length);
						 dns1=Arrays.copyOfRange(dns1, 0, i);
						 break;
					}
					
				}
				
			if(b!=null)
			{
				port=Integer.parseInt(new String(b));
			}
			else
			{
				port=80;
			}
				
				return dns1;
				
				
	}
				
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException
	{
		
		//int Clientport = Integer.parseInt(args[0]);
		//if(Clientport < 0 || Clientport > 65535)
			//System.exit(1);
		
		
		ServerSocket r = new ServerSocket();
		r.bind(new InetSocketAddress(4367));
		System.out.print("stage 1 program by(djn26) listening on port: " );
		for(;;)
		{
			
		try(Socket m = r.accept())
		{
			
			
			
		
		
				int size = 1;
				byte b[] = new byte[65535];
				byte a[] = new byte[size];
				
				int s = 0;
				
				try
				{
				
			 s =	m.getInputStream().read(b, 0, 65535);
			 
			 if(s == -1)
			 {
				 s = 0;
			 }
			 
			 
 int thePort = port;
 byte[] parser_array = doParser(b,s);
			 
			 
			 
			
			 
			
			 
			 
		 
		 
		 
		
			
		System.out.println(new String(parser_array) + new String(path_parser(b, s, parser_array.length)));
		
		byte[] path_parser_array = path_parser(b,s, parser_array.length);
		
		dnsLookup(parser_array, path_parser_array, m, thePort);
		
		
		
		
				
		
		
		}
			
			catch(Exception e)
			{
				 
				e.printStackTrace();
			}
					
		}
		
				
		} 
		}
			
	}
		
	


