import java.io.*;
import java.net.*;
import java.util.*;


class serviceRequester
{
	public static void main(String args[]) throws Exception
	{
		try
		{
			Socket s = new Socket("localhost", 3000);
			System.out.println("Connection established with the Broker");
			BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			int n;
			while(true)
			{
				System.out.print("\n\nWhich option do you need\n1. Discover Service\n2. Exit\nPlease enter the option : ");
				try
				{
					n = Integer.parseInt(kb.readLine());
					if (n==1)
					{
						int j=n+2,size=0;
						dos.writeBytes(j+"\n");
						size = Integer.parseInt(br.readLine());
						if (size == 0)
						{
							System.out.println("\nNo Services have been added yet at the Broker side\n");
							continue;
						}
						else
						{
							System.out.print("\nPlease select from the following set of Services:\n");
							for(int g=0;g<size;g++)
							{
								String ser=br.readLine();
								System.out.println((g+1)+". "+ser);
							}
							System.out.print("Please enter the option : ");
							int y=Integer.parseInt(kb.readLine());
							if(y>0 && y<=size)
							{
								dos.writeBytes(y+"\n");
								int port = Integer.parseInt(br.readLine());
								String ip_address = br.readLine();
								System.out.println("\nIt is present at\nIP Address : " + ip_address + "\nPort Number : " + port + "\n");
								try
								{
									Socket soc = new Socket(ip_address,port);
									System.out.println("\nConnection established with the Service Provider\n");
									BufferedReader br1 = new BufferedReader(new InputStreamReader(soc.getInputStream()));
									DataOutputStream dos1 = new DataOutputStream(soc.getOutputStream());
									if (port==4001)
									{
										System.out.print("Enter Text : ");
										String s1,s2;
										s1=kb.readLine();
										System.out.print("Enter Method : ");
										s2=kb.readLine();
										dos1.writeBytes(s1+"\n");
										dos1.writeBytes(s2+"\n");
									}
									String ans = br1.readLine();
									System.out.println(ans);
									dos1.close();
									br1.close();
									soc.close();
								}
								catch (Exception e)
								{
									System.out.println("\nSocket connection interrupted with the provider\n");
								}
							}
							else
							{
								System.out.println("\nInvalid selection.\nPlease enter option from the above list.\n");
								continue;
							}
						}
					}
					else
					{
						if (n==2)
						{
							System.out.println("Thank you.\nHave a good day\n");
							break;
						}
						else
						{
							System.out.println("Please enter correct option");
							continue;
						}
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("\nPlease enter only numbers\n");
				}
				catch (Exception e)
				{
					continue;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("\nSocket connection was interrupted with the broker\n");
		}
	}
}
