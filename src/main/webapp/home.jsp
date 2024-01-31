<%@ page import="java.sql.*, java.io.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <title>JDBC with JSP</title>
</head>
<body>
<%
 String url= "jdbc:oracle:thin:@localhost:1521:xe";
 String user = "infomerica";
 String password= "infomerica";

    Connection con= null;
    Statement s = null;
    ResultSet rs = null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, user, password);
        s = con.createStatement();
        String sql = "select *from college";  // Fix: Use lowercase "FROM"
        rs = s.executeQuery(sql);
        while (rs.next()) {
            out.println("ID: " + rs.getInt(1) + "<br>");
            out.println("Branch: " + rs.getString(2) + "<br>");
            // Add other columns as needed
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (s != null) s.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

</body>
</html>
