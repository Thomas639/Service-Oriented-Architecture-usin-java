import java.io.*;
import java.net.*;
import java.util.*;

class GenerateRandom
{
	public int createRandom()
	{
		int min = 1;
		int max = 100;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		return random_int;
	}
}
class serviceProvider2
{
	public static void main(String args[]) throws Exception
	{
		ServerSocket ss = new ServerSocket(4002);
		System.out.println("Service Provider 2 Started at port number : 4002");
		while(true)
			{
				try
				{
					Socket s = ss.accept();
					System.out.println("Connection established with Service Requester");
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
					GenerateRandom ran = new GenerateRandom();
					int k = ran.createRandom();
					dos.writeBytes("Random number = "+k+"\n");
					dos.close();
					br.close();
					kb.close();
					s.close();
				}
				catch (Exception e)
				{
					System.out.println("\nSocket connection was interrupted with the Requester\n");
				}
			}
	}
}

