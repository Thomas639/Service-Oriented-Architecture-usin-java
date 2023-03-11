import java.io.*;
import java.net.*;
import java.util.*;


class serviceProvider
{
	public static void main(String args[]) throws Exception
	{
		try
		{
			Socket s = new Socket("localhost", 3000);
			System.out.println("Connection established with the Broker at \nIP address : localhost and port number : 3000");
			while(true)
			{
				System.out.print("Which option do you need\n1. Registration at Broker side\n2. Add Service\n3. Remove Service\nPlease enter the option : ");
				int n;
				BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				try
				{
					n = Integer.parseInt(kb.readLine());
					if (n==2)
					{
						dos.writeBytes((n-1)+"\n");
						int port;
						String ip,name,func,ID,password;
						System.out.print("Please enter Username : ");
						ID = kb.readLine();
						dos.writeBytes(ID + "\n");
						System.out.print("Please enter Password : ");
						password = kb.readLine();
						dos.writeBytes(password + "\n");
						int z=Integer.parseInt(br.readLine());
						if(z==1)
						{
							int otp = Integer.parseInt(br.readLine());
							System.out.println("\nOTP received from Broker : " + otp);
							System.out.print("Please enter receievd OTP : ");
							int otp1 = Integer.parseInt(kb.readLine());
							dos.writeBytes(otp1+"\n");
							String res=br.readLine();
							if (res.equals("20"))
							{
								System.out.println("Authentication failed.\nWrong OTP entered\n");
								continue;
							}
							else
							{
								System.out.println(res);
								System.out.print("Please enter IP address : ");
								ip = kb.readLine();
								dos.writeBytes(ip + "\n");
								System.out.print("Please enter Port Number : ");
								port = Integer.parseInt(kb.readLine());
								dos.writeBytes(port + "\n");
								System.out.print("Please enter Name : ");
								name = kb.readLine();
								dos.writeBytes(name + "\n");
								System.out.print("Please enter Functionality : ");
								func = kb.readLine();
								dos.writeBytes(func + "\n");
								String str = br.readLine();
								System.out.println(str);
							}
						}
						else
						{
							System.out.println("Authentication failed.\nWrong Username/Password entered\n");
							continue;
						}
					}
					else
					{
						if (n==3)
						{
							dos.writeBytes((n-1)+"\n");
							int port;
							String ip,name,ID,password;
							System.out.print("Please enter Username : ");
							ID = kb.readLine();
							dos.writeBytes(ID + "\n");
							System.out.print("Please enter Password : ");
							password = kb.readLine();
							dos.writeBytes(password + "\n");
							int z=Integer.parseInt(br.readLine());
							if(z==1)
							{
								int otp = Integer.parseInt(br.readLine());
								System.out.println("\nOTP received from Broker : " + otp);
								System.out.print("Please enter receievd OTP : ");
								int otp1 = Integer.parseInt(kb.readLine());
								dos.writeBytes(otp1+"\n");
								String res=br.readLine();
								if (res.equals("20"))
								{
									System.out.println("Authentication failed.\nWrong OTP entered\n");
									continue;
								}
								else
								{
									System.out.println(res);
									System.out.print("Please enter IP address : ");
									ip = kb.readLine();
									dos.writeBytes(ip + "\n");
									System.out.print("Please enter Port Number : ");
									port = Integer.parseInt(kb.readLine());
									dos.writeBytes(port + "\n");
									System.out.print("Please enter Name : ");
									name = kb.readLine();
									dos.writeBytes(name + "\n");
									String str = br.readLine();
									System.out.println(str);
								}
							}
							else
							{
								System.out.println("Authentication failed.\nWrong Username/Password entered\n");
								continue;
							}
						}
						else
						{
							if(n==1)
							{
								System.out.println("\nRegister yourself at the Broker side to add services to the Broker list\n");
								System.out.print("Please enter desired USERNAME : ");
								String username,password1,password2;
								username=kb.readLine();
								System.out.print("Please enter desired password : ");
								password1=kb.readLine();
								System.out.print("Please enter your password again to confirm : ");
								password2=kb.readLine();
								if (password1.equals(password2))
								{
									n=n+3;
									dos.writeBytes(n+"\n");
									dos.writeBytes(username+"\n");
									dos.writeBytes(password1+"\n");
									String res=br.readLine();
									System.out.println(res);
								}
								else
								{
									System.out.println("\nPasswords did not match.\nRegistration Failed.\n");
								}
							}
							else
							{
								System.out.println("\nPlease enter correct option\n");
							}
						}
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("\nPlease enter only numbers\n");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("\nSocket connection was interrupted with the Broker\n");
		}
	}
}
