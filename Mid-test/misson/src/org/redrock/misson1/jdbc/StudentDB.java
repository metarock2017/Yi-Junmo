package org.redrock.misson1.jdbc;

import org.redrock.misson1.jdbc.resultset.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDB {
    private String url = "jdbc:mysql://localhost:3306/test_stu?useUnicode=true&characterEncoding=utf8";
    private String root = "root";
    private String passwd = "jxx970224";

    public void UpdateStu(Student student) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, passwd);
            pst = conn.prepareStatement("insert into student(stuId,name,gender,grade,college,major,class) values (?,?,?,?,?,?,?)");
            pst.setString(1, student.getStuId());
            pst.setString(2, student.getName());
            pst.setInt(3, student.getGender());
            pst.setInt(4, student.getGrade());
            pst.setString(5, student.getCollege());
            pst.setString(6, student.getMajor());
            pst.setString(7, student.getClasses());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Student GetaStu(Student student) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Student rstu = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, passwd);
            pst = conn.prepareStatement("select * from student where stuId = ?");
            pst.setString(1, student.getStuId());
            rs = pst.executeQuery();
            rstu = new Student();
            if(rs.next()){
                rstu.setId(rs.getInt("id"));
                rstu.setStuId(rs.getString("stuId"));
                rstu.setName(rs.getString("name"));
                rstu.setGender(rs.getInt("gender"));
                rstu.setGrade(rs.getInt("grade"));
                rstu.setCollege(rs.getString("college"));
                rstu.setMajor(rs.getString("major"));
                rstu.setClasses(rs.getString("class"));
            }
            return rstu;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rstu;
    }

    public Student DeleteStu(Student student) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Student rstu = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, passwd);
            pst = conn.prepareStatement("select * from student where stuId = ?");
            pst.setString(1, student.getStuId());
            rs = pst.executeQuery();
            rstu = new Student();
            if(rs.next()){
                rstu.setId(rs.getInt("id"));
                rstu.setStuId(rs.getString("stuId"));
                rstu.setName(rs.getString("name"));
                rstu.setGender(rs.getInt("gender"));
                rstu.setGrade(rs.getInt("grade"));
                rstu.setCollege(rs.getString("college"));
                rstu.setMajor(rs.getString("major"));
                rstu.setClasses(rs.getString("class"));
            }
            pst = conn.prepareStatement("delete from student where stuId = ?");
            pst.setString(1, student.getStuId());
            pst.executeUpdate();
            return rstu;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rstu;
    }

    public List<Student> SelectAllStu(Map<String,String> method) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Student rstu = null;
        List<Student> AllStu = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, passwd);
            pst = conn.prepareStatement("select * from student where name like ? and stuId like ? order by ? /?");
            pst.setString(1,method.get("name"));
            pst.setString(2,method.get("stuId"));
            pst.setString(3,method.get("sortby"));
            pst.setString(4,method.get("sort"));

            rs = pst.executeQuery();
            rstu = new Student();
            while(rs.next()){
                rstu.setId(rs.getInt("id"));
                rstu.setStuId(rs.getString("stuId"));
                rstu.setName(rs.getString("name"));
                rstu.setGender(rs.getInt("gender"));
                rstu.setGrade(rs.getInt("grade"));
                rstu.setCollege(rs.getString("college"));
                rstu.setMajor(rs.getString("major"));
                rstu.setClasses(rs.getString("class"));
                AllStu.add(rstu);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return AllStu;
    }
}
