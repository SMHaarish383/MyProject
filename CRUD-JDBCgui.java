package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JDBCgui {

	private JFrame frame;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfDegree;
	private JTextField tfAge;
	private JTable DataTable;


	private String url="jdbc:mysql://localhost:3306/haarish";
	private String Username="root";
	private String Password="Muhammadu#7";
	
	private Connection con=null;
	private PreparedStatement st;
	private ResultSet rs=null;
	private int row=0;
	private JTable table;

	
	public JDBCgui() {
		initialize();
		DataTable.setModel(loadData());
	}
	private void insert() {
		try {
			
		String id=tfID.getText();
		String Name=tfName.getText();
		String Degree=tfDegree.getText();
		String Age=tfAge.getText();
		
		String query="insert into container values (?,?,?,?);";
		con=DriverManager.getConnection(url,Username,Password);
		st=con.prepareStatement(query);
		
		st.setString(1,id);
		st.setString(2,Name);
		st.setString(3,Degree);
		st.setString(4,Age);
		
		row =st.executeUpdate();
		JOptionPane.showMessageDialog(null,String.valueOf("Data Saved"+row),"Popup",JOptionPane.PLAIN_MESSAGE);
		DataTable.setModel(loadData());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,String.valueOf(e),"Popup",JOptionPane.PLAIN_MESSAGE);

		}
		
	}
	public void Modify() {
		
//		String url="jdbc:mysql://localhost:3306/haarish";
//		String Username="root";
//		String Password="Muhammadu#7";
		
//		Scanner sc=new Scanner(System.in);
//		System.out.println("Which id do you want to change?");
//		System.out.print("Enter id ");
		try {
			
		String id=tfID.getText();
		String Name=tfName.getText();
		String Degree=tfDegree.getText();
		String Age=tfAge.getText();
		
		
 		String Query="update Container set Name=?,Degree=?,Age=? where id=?;";
		
		Connection con=DriverManager.getConnection(url,Username,Password);
		st=con.prepareStatement(Query);
		st.setString(1,Name);
		st.setString(2,Degree);
		st.setString(3,Age);
		st.setString(4,id);
		int row =st.executeUpdate();
		JOptionPane.showMessageDialog(null,String.valueOf("Data Saved"+row),"Popup",JOptionPane.PLAIN_MESSAGE);
		
//		System.out.println("Number of Affected "+row);
		Clear();
		DataTable.setModel(loadData());
		
		con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,String.valueOf(e),"Popup",JOptionPane.PLAIN_MESSAGE);
			
		}
	}
	
	public void delete() {
		
		try {
			
		String id=tfID.getText();
//		String Name=tfName.getText();
//		String Degree=tfDegree.getText();
//		String Age=tfAge.getText();
		
		String Query="Delete from Container where id = "+id;
		
		con=DriverManager.getConnection(url,Username,Password);
		st=con.prepareStatement(Query);
		int row =st.executeUpdate();
		
		
		JOptionPane.showMessageDialog(null,String.valueOf("Data Saved"+row),"Popup",JOptionPane.PLAIN_MESSAGE);
		DataTable.setModel(loadData());
		con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,String.valueOf(e),"Popup",JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}
	public void Clear() {
		try {
			tfID.setText("");
			tfName.setText("");
			tfDegree.setText("");
			tfAge.setText("");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private DefaultTableModel loadData() {
		try {
			String query="Select*from Container;";
			con=DriverManager.getConnection(url,Username,Password);
			
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			int col=metaData.getColumnCount();
			Vector<String> columnName=new Vector<String>();
			
			for(int column=0;column<col;column++) {
				columnName.add(metaData.getColumnLabel(column+1));
			}
			
			DefaultTableModel model=(DefaultTableModel)DataTable.getModel();
			model.setColumnIdentifiers(columnName);
			
			Vector<Vector<Object>> rows=new Vector<>();
			
			while(rs.next()) {
				Vector<Object> newRow=new Vector<Object>();
				for(int i=1;i<=col;i++) {
					newRow.addElement(rs.getObject(i));
				}
				rows.addElement(newRow);	
			}
		return new DefaultTableModel(rows,columnName);//
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Myanmar Text", Font.BOLD, 18));
		frame.setBounds(100, 100, 903, 799);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(250, 10, 371, 325);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		lbID.setBounds(43, 93, 76, 26);
		panel_1.add(lbID);
		
		JLabel lbName = new JLabel("Name");
		lbName.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		lbName.setBounds(43, 129, 89, 26);
		panel_1.add(lbName);
		
		JLabel lbDegree = new JLabel("Degree");
		lbDegree.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		lbDegree.setBounds(43, 165, 106, 26);
		panel_1.add(lbDegree);
		
		JLabel lbAge = new JLabel("Age");
		lbAge.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		lbAge.setBounds(43, 201, 76, 26);
		panel_1.add(lbAge);
		
		tfID = new JTextField();
		tfID.setFont(new Font("Myanmar Text", Font.BOLD, 11));
		tfID.setBounds(171, 98, 171, 17);
		panel_1.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfName.setColumns(10);
		tfName.setBounds(171, 130, 171, 17);
		panel_1.add(tfName);
		
		tfDegree = new JTextField();
		tfDegree.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfDegree.setColumns(10);
		tfDegree.setBounds(171, 166, 171, 17);
		panel_1.add(tfDegree);
		
		tfAge = new JTextField();
		tfAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfAge.setColumns(10);
		tfAge.setBounds(171, 199, 171, 17);
		panel_1.add(tfAge);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Data");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblNewLabel_1.setBounds(135, 21, 89, 41);
		panel_1.add(lblNewLabel_1);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnInsert.setBounds(22, 276, 64, 26);
		panel_1.add(btnInsert);
		btnInsert.addActionListener(a -> insert());
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUpdate.setBounds(105, 276, 73, 26);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(b-> Modify());
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDelete.setBounds(188, 276, 70, 26);
		panel_1.add(btnDelete);
		btnDelete.addActionListener(c-> delete());
		
		JButton Clearbtn = new JButton("Clear");
		Clearbtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Clearbtn.setBounds(273, 276, 76, 26);
		panel_1.add(Clearbtn);
		Clearbtn.addActionListener(d -> Clear());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 345, 869, 407);
		frame.getContentPane().add(scrollPane);
		
		DataTable = new JTable();
		scrollPane.setViewportView(DataTable);
		
		DataTable.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel) DataTable.getModel();
				int  index=DataTable.getSelectedRow();
				tfID.setText(model.getValueAt(index, 0).toString());
				tfName.setText(model.getValueAt(index, 1).toString());
				tfDegree.setText(model.getValueAt(index, 2).toString());
				tfAge.setText(model.getValueAt(index, 3).toString());
				
			}
		});
			
		
		
		
	
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JDBCgui window = new JDBCgui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
