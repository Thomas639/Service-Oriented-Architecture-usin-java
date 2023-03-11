import java.io.*;
import java.net.*;
import java.util.*;

class values
{
    public String ip_address;
    public int port_number;
    public String name;
    public String function;
	public String ID;
	public String password;
	public values(String ip_address,int port_number,String name,String function,String ID,String password)
    {
        this.ip_address=ip_address;
        this.port_number=port_number;
        this.name=name;
        this.function=function;
		this.ID=ID;
		this.password=password;
    }
}

class id
{
	public String ID;
	public String password;
	public id(String ID, String password)
	{
		this.ID=ID;
		this.password=password;
	}
}

class RandomOTP
{
	public int createRandom()
	{
		int min = 100000;
		int max = 999999;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		return random_int;
	}
}

class serviceBroker
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(3000);
		Socket s=null;
        System.out.println("Broker Started at port number : 3000");
		List<values> details = new ArrayList<values>();
		List<id> auth = new ArrayList<id>();
		while(true)
		{
			try
			{
				s=ss.accept();
				System.out.println("\nConnection established\n");
				BrokerThread st = new BrokerThread(s,details,auth);
				st.start();
			}
			catch (Exception e)
			{
				System.out.println("\nSocket connection was interrupted\n");
			}
		}
	}
}

class BrokerThread extends Thread
{
	Socket s=null;
	DataOutputStream dos = null;
	BufferedReader br = null;
	BufferedReader kb = null;
	List<values> details = null;
	List<id> auth = null;
	public BrokerThread(Socket s,List<values> details,List<id> auth)
	{
		this.s=s;
		this.details=details;
		this.auth=auth;
	}
	public void run()
	{
		try
		{
			dos = new DataOutputStream(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			kb = new BufferedReader(new InputStreamReader(System.in));
		}
		catch(IOException e)
		{
			System.out.println("IO error");
		}
		RandomOTP rotp = new RandomOTP();
		while(true)
		{
			try
			{
				int i;
				try
				{
					String x=br.readLine();
					i=Integer.parseInt(x);
				}
				catch (NumberFormatException e)
				{
					continue;
				}
				if (i==1)
				{
					int port,z=0;
					String ip,name,func,ID,password;
					ID=br.readLine();
					password=br.readLine();
					for (id pass : auth)
						{
							if(ID.equals(pass.ID) && password.equals(pass.password))
								{
									z=1;
									dos.writeBytes(z+"\n");
									int otp=rotp.createRandom();
									dos.writeBytes(otp + "\n");
									int otp1=Integer.parseInt(br.readLine());
									if (otp == otp1)
									{
										dos.writeBytes("Welcome " + ID + "\n");
										ip = br.readLine();
										port = Integer.parseInt(br.readLine());
										name = br.readLine();
										func = br.readLine();
										details.add(new values(ip,port,name,func,ID,password));
										dos.writeBytes("Service added successfully at Broker side\n");
									}
									else
									{
										dos.writeBytes("20"+"\n");
									}
								}
						}
					if (z==0)
						{
							dos.writeBytes("10" + "\n");
						}
				}
				else if (i==2)
				{
					int port,index=-1,k,z=0;
					String ip,name,ID,password;
					ID=br.readLine();
					password=br.readLine();
					for (id pass : auth)
					{
						if(ID.equals(pass.ID) && password.equals(pass.password))
						{
							z=1;
							dos.writeBytes(z+"\n");
							int otp=rotp.createRandom();
							dos.writeBytes(otp + "\n");
							int otp1=Integer.parseInt(br.readLine());
							if (otp == otp1)
							{
								dos.writeBytes("Welcome " + ID + "\n");
								ip = br.readLine();
								port = Integer.parseInt(br.readLine());
								name = br.readLine();
								for(values value : details)
								{
									
									if (name.equals(value.name) && ip.equals(value.ip_address) && port==value.port_number && ID.equals(value.ID) && password.equals(value.password))
									{
										index=details.indexOf(value);
										break;
									}
								}
								if (index==-1)
								{
									dos.writeBytes("No Service Found at Broker side\n");
								}
								else
								{
									details.remove(index);
									dos.writeBytes("Service removed successfully at Broker side\n");
								}
							}
							else
							{
								dos.writeBytes("20"+"\n");
							}
						}
					}
					if (z==0)
					{
						dos.writeBytes("10" + "\n");
					}
				}
				else if (i==3)
				{
					int y;
					int size = details.size();
					dos.writeBytes(size + "\n");
					if (size==0)
					{
						continue;
					}
					else
					{
						for(int g=0;g<size;g++)
							{
								values value=details.get(g);
								dos.writeBytes(value.name+"\n");
							}
						y=Integer.parseInt(br.readLine());
						y=y-1;
						values value = details.get(y);
						dos.writeBytes(value.port_number+"\n");
						dos.writeBytes(value.ip_address+"\n");
					}
				}
				else if (i==4)
				{
					String username,password;
					int z=0;
					username=br.readLine();
					password=br.readLine();
					for (id pass : auth)
						{
							if(username.equals(pass.ID))
								{
									z=1;
									dos.writeBytes("Registration Failed because Username already in use. Try other usernames.\n"+"\n");
								}
						}
					if (z==0)
						{
							auth.add(new id(username,password));
							dos.writeBytes("Provider with the username : \""+username+"\" is registered successfully" + "\n");
						}
				}
				else
				{
					continue;
				}
			}
			catch(Exception e)
			{
				continue;
			}
		}
	}
}
