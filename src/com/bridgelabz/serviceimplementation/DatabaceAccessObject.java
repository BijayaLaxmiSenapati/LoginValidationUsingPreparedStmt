package com.bridgelabz.serviceimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bridgelabz.utility.Utility;

public class DatabaceAccessObject 
{
	Connection con=null;
	PreparedStatement pstmt1=null;
	PreparedStatement pstmt2=null;
	ResultSet rs=null;
	String selectQuery=null;
	String insertQuery=null;
	public void connection() 
	{
		try 
		{
			selectQuery="select * from Emp_Information.Employee where email_id= ? and password= ? ";
			insertQuery="insert into Emp_Information.Employee values(?,?,?,?,?)";
			Class.forName(Utility.getProperty("DRIVER"));
			con=DriverManager.getConnection(Utility.getProperty("URL"),Utility.getProperty("USERNAME"),Utility.getProperty("PASSWORD"));
			pstmt1=con.prepareStatement(selectQuery);
			pstmt2=con.prepareStatement(insertQuery);

		} 
		catch (ClassNotFoundException |SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void closeResources()
	{
		if(rs!=null)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(pstmt1!=null)
		{
			try
			{
				pstmt1.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(pstmt2!=null)
		{
			try
			{
				pstmt2.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
