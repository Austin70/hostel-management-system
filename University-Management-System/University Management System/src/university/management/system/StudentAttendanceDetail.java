/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.management.system;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentAttendanceDetail extends JFrame implements ActionListener{
  
    JTable j1;
    JButton b1,b2;
    String h[]={"Roll Number","Date Time","First Half","Second Half"};
    String d[][]=new String[15][4];  
    int i=0,j=0;
    
    StudentAttendanceDetail(){
        super("View Students Attendance");
        setSize(1250,350);
        setLocation(200,150);
        setLayout(null);

        
        b1=new JButton("Print");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(300, 280, 100 ,30);
        add(b1);
        b2 = new JButton("Delete");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(580, 280, 100 ,30);
        add(b2);
        
        
        try{
            String q="select * from attendance_student";
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next()){
                d[i][j++]=rs.getString("rollno");
                d[i][j++]=rs.getString("Date");
                d[i][j++]=rs.getString("first");
                d[i][j++]=rs.getString("second");
                i++;
                j=0;
            }
        
            j1 =new JTable(d,h);

        }catch(Exception e){}
       // JScrollPane s1=new JScrollPane(j1);
        //add(s1);
        JScrollPane s1 = new JScrollPane(j1);
        s1.setBounds(20,20,1200,250);
        add(s1);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
       // getContentPane().setBackground(Color.WHITE);
    
    }
    public void actionPerformed(ActionEvent ae){
         conn c1 = new conn();
    
        if(ae.getSource() == b2){
            try{
                
                String q = "delete from attendance_student";
                c1.s.executeUpdate(q);
                this.setVisible(false);
                new StudentAttendanceDetail().setVisible(true);
            }catch(Exception e){}
    
        }else if(ae.getSource() == b1){
            try{
            j1.print();
        }catch(Exception e){}
       
        }
    
        
    }
    
    public static void main(String[] args){
        new StudentAttendanceDetail().setVisible(true);
    }
}