package com.bridgelabz.serviceimplementation;

import java.sql.SQLException;

import com.bridgelabz.service.UserService;
import com.bridgelabz.utility.Utility;

public class UserServiceImplementation implements UserService
{
	DatabaceAccessObject databaceAccessObject=new DatabaceAccessObject();
		@Override
		public void login()
		{
			databaceAccessObject.connection();
			System.out.println("Enter your email id");
			String emailId=Utility.retNext();
			System.out.println("Enter your password");
			String password=Utility.retNext();
			checkExistOrNot(emailId,password);
		}

		private void checkExistOrNot(String emailId, String password)
		{
			try 
			{
				databaceAccessObject.pstmt1.setString(1,emailId);
				databaceAccessObject.pstmt1.setString(2,password);
				databaceAccessObject.rs=databaceAccessObject.pstmt1.executeQuery(databaceAccessObject.selectQuery);
				if(databaceAccessObject.rs.next())
				{
					System.out.println("Your details are:");
					String nm=databaceAccessObject.rs.getString(1);
					int age=databaceAccessObject.rs.getInt(2);
					long phNum=databaceAccessObject.rs.getLong(3);
					String mail=databaceAccessObject.rs.getString(4);
					String pwd=databaceAccessObject.rs.getString(5);
					System.out.println("name="+nm);
					System.out.println("age="+age);
					System.out.println("phone number="+phNum);
					System.out.println("mail id="+mail);
					System.out.println("password="+pwd);
					databaceAccessObject.closeResources();

				}
				else
				{
					System.out.println("You have not registered yet...");
					System.out.println("Do you want to register?");
					System.out.println("1.Yes 2.No");
					int option=Utility.retInt();
					if(option==1)
					{
						register();
					}
					else
					{
						databaceAccessObject.closeResources();
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void register() 
		{
			databaceAccessObject.connection();
			System.out.println("Enter your name");
			String name=Utility.retNext();
			System.out.println("Enter your age");
			int age=Utility.retInt();
			System.out.println("Enter your phone number");
			long phoneNumber=Utility.retLong();
			System.out.println("Enter your email id");
			String emailId=Utility.retNext();
			System.out.println("Enter your password");
			String password=Utility.retNext();
			addIntoDataBaseTable(name,age,phoneNumber,emailId,password);
		}

		private void addIntoDataBaseTable(String name, int age, long phoneNumber, String emailId, String password) 
		{
			try 
			{
				databaceAccessObject.pstmt2.setString(1,name);
				databaceAccessObject.pstmt2.setInt(2,age);
				databaceAccessObject.pstmt2.setLong(3,phoneNumber);
				databaceAccessObject.pstmt2.setString(4,emailId);
				databaceAccessObject.pstmt2.setString(5,password);
				databaceAccessObject.pstmt2.executeUpdate(databaceAccessObject.insertQuery);
				databaceAccessObject.closeResources();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}

}
