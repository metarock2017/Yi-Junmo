import java.sql.*;
public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/bilibili";
        String user = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,password);
            stmt=conn.createStatement();
            String sql;
            sql="insert into user(username) values('666')";
            stmt.executeUpdate(sql);
            sql="update user set username='admin1' where uid='1000001'";
            stmt.executeUpdate(sql);
            sql="delete from user where username='6666'";
            stmt.executeUpdate(sql);
            sql="select * from user";
            rs = stmt.executeQuery(sql);
            System.out.println("学号" + "\t\t\t" + " 姓名");
            String name = null;
            while(rs.next())
            {
                name = rs.getString("username");
                System.out.println(rs.getString("uid") + "\t\t\t" + name);
            }

        }
        catch (ClassNotFoundException e1)
        {
            System.out.println("Not found");
        }
        catch (SQLException e2)
        {
            System.out.println("数据库异常");
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if(conn!=null)
                    conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.toString());
            }
        }
    }
}
