import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class EmpPass extends JFrame implements ActionListener
{
	JLabel oldPassLabel, newPassLabel;
	JPasswordField oldPassTF, newPassTF;
	JButton changeBtn, logoutBtn;
	JPanel panel;
	String userId;
	
	public EmpPass(String userId)
	{
		super(" Change Password ");
		
		userId = userId;
		setSize(950, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		 panel.setBackground(new Color(241-222-132));
		panel.setLayout(null);
		
		logoutBtn = new JButton("Back");
		logoutBtn.setBounds(400, 300, 120, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		oldPassLabel = new JLabel("Old Password : ");
		oldPassLabel.setBounds(150, 150, 120, 30);
		panel.add(oldPassLabel);
		
		oldPassTF = new JPasswordField();
		oldPassTF.setBounds(300, 150, 120, 30);
		oldPassTF.setEchoChar('*');
		panel.add(oldPassTF);
		
		
		newPassLabel = new JLabel("New Password : ");
		newPassLabel.setBounds(150, 200, 120, 30);
		
		panel.add(newPassLabel);
		
		newPassTF = new JPasswordField();
		newPassTF.setBounds(300, 200, 120, 30);
		newPassTF.setEchoChar('*');
		panel.add(newPassTF);
		
		changeBtn = new JButton("Change");
		changeBtn.setBounds(150, 300, 120, 30);
		changeBtn.addActionListener(this);
		panel.add(changeBtn);
		
		
		this.add(panel);
	}
	
		public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		
		 if(text.equals(logoutBtn.getText()))
		{
			MyProfile hg = new MyProfile(userId);
			hg.setVisible(true);
			this.setVisible(false);
		}
		 else if(text.equals(changeBtn.getText()))
		{
			insertIntoDB();
		}
		else{}
	}
	public void insertIntoDB()
	{
	String newPass = newPassTF.getText();
		//String userId= userId;
		//String = password;
		String password = newPassTF.getText();
		
		String query = "UPDATE login SET password='"+password+"' WHERE userId='"+userId+"'";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			st = con.createStatement();//create statement
			st.executeUpdate(query);
			st.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Oops !!!");
		}
	}
	
}
	
