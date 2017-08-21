package org.redrock.misson1.jdbc;

import org.redrock.misson1.jdbc.resultset.User;
import org.redrock.misson1.util.B64Util;

import java.sql.*;

public class Connect {
    private String url = "jdbc:mysql://localhost:3306/test_stu";
    private String root = "root";
    private String passwd = "jxx970224";
    public void InsertUser(User user) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,root,passwd);
            pst = conn.prepareStatement("insert into user(username,passwd,answer) values (?,?,?)");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPasswd());
            pst.setString(3, user.getAnswer());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pst != null) {
                    pst.close();
                    pst = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public  User SelectUser(User user) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User ruser = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,root,passwd);
            pst = conn.prepareStatement("select * from user where username=?");
            pst.setString(1, user.getUsername());
            rs = pst.executeQuery();
            ruser = new User();
            if(rs.next()){
                ruser.setId(rs.getInt("id"));
                ruser.setUsername(rs.getString("username"));
                byte[] dec = B64Util.decode(rs.getString("passwd"));
                ruser.setPasswd(B64Util.byteArrayToStr(dec));
                ruser.setAnswer(rs.getString("answer"));
            }
            return ruser;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pst != null) {
                    pst.close();
                    pst = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ruser;
    }

    public void UpdateUser(User user) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,root,passwd);
            pst = conn.prepareStatement("update user set passwd = ? where username = ?");
            pst.setString(1, user.getPasswd());
            pst.setString(2,user.getUsername());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pst != null) {
                    pst.close();
                    pst = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
