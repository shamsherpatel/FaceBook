import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class FDemo extends JFrame implements ActionListener
{
	Container cn=getContentPane();
	CardLayout card;
	LogIn login;
	CreateNewAccount reg;
	ForgettenAccount faccount;
	HomePage home;
	FDemo()
    {
		card=new CardLayout();
		setLayout(card);
		
		login=new LogIn(this);
		add(login,"login");
		
		reg=new CreateNewAccount(this);
		add(reg,"reg");
		
		faccount=new ForgettenAccount(this);
		add(faccount,"faccount");
		
		home=new HomePage(this);
		add(home,"home");
	}
	public void actionPerformed(ActionEvent e)
	{
		//login
		if(e.getSource()==login.b1)
		{	
	      String s1=login.tx1.getText();
		  String s2=login.tx2.getText();
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String db_url="jdbc:mysql://localhost:3306/facebook?useSSL=false";
				String db_un="root";
				String db_up="root";
				
				Connection con=DriverManager.getConnection(db_url,db_un,db_up);
				Statement st=con.createStatement();
				String q="select * from login where UNAME='"+s1+"' AND UPASS='"+s2+"'";
				ResultSet rs=st.executeQuery(q);
				
				if(rs.next())
				{
					card.show(cn,"home");
				}
				else
			    {
				 JOptionPane.showMessageDialog(null,"Invalid username or Password");
			    }
			    con.close();
			 }
			    catch(Exception e1)
			    {
			   	 System.out.println(e1);
			    }
		   }
		if(e.getSource()==login.b2)
		{
			card.show(cn,"reg");
		}
		if(e.getSource()==login.b3)
		{
			card.show(cn,"faccount");
		}
		
		//CreateNewAccount
		if(e.getSource()==reg.b1)
		{
			
	     String name= reg.tx1.getText(); 
		 String last_name=reg.tx2.getText(); 
		 String number= reg.tx3.getText(); 
		 String password=reg.tp1.getText(); 
	
	     String s1=reg.c1.getSelectedItem();
         String s2=reg.c2.getSelectedItem();
         String s3=reg.c3.getSelectedItem();
		
		 String dob=""+s1+"-"+s2+"-"+s3;
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String db_url="jdbc:mysql://localhost:3306/facebook?useSSL=false";
				String db_un="root";
				String db_up="root";
				
				Connection con=DriverManager.getConnection(db_url,db_un,db_up);
				Statement st=con.createStatement();
				String q="select * from login where UNAME='"+name+"' AND ULASTNAME='"+last_name+"' AND UNUMBER='"+number+"' AND UPASS='"+password+"' AND UDOB='"+dob+"'";
				ResultSet rs=st.executeQuery(q);
				
				if(rs.next())
				{
					card.show(cn,"home");
				}
				else
			    {
				 JOptionPane.showMessageDialog(null,"fill all compalsury");
			    }
			    con.close();
			 }
			    catch(Exception e1)
			    {
			   	 System.out.println(e1);
			    }
		   
			// card.show(cn,"home");
		}
		if(e.getSource()==reg.b2)
		{
			card.previous(cn);
		}
		
		//forget account
		if(e.getSource()==faccount.b2)
		{
			card.show(cn,"login");
		}
	}
}
	class Card
	{
		public static void main(String ar[])
		{
			FDemo f=new FDemo();
			f.setVisible(true);
			f.setBounds(00,00,1350,900);
			f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		}
	}