<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function disableButton(btn){
                document.getElementById(btn.id).disabled=true;
                alert("button has been disabled.")
            }
            </script>
    </head>
    <body>
        <form action="login.html" method="post">
        <div class="upper">
            <input type="submit" value="Logout"></button>
        </div>
        </form>
        <%
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
                String sql = "SELECT * FROM information;";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
        %>
        <div class="container">
            <form action="DeleteInfo" >
               
                <fieldset>
                    <legend><% out.print(rs.getString("Donor_Name")); %></legend>
                    created on <% out.print(rs.getString("Prepare_date")); %> &nbsp;&nbsp;&nbsp;
                    <input type="hidden" name="delval" value="<% out.print(rs.getString("id")); %>">
                    Food Quality: <% out.print(rs.getString("Food_Quality")); %><br>
<!--                    Item: <% out.print(rs.getString("Item")); %><br>-->
                    <% out.print(rs.getString("Description")); %><br>
                    Contact:<% out.print(rs.getString("ContactNo"));%>
                    <input type="submit" value="Book" id="btn" ">
                </fieldset>
            </form>
        </div>
        <%
                }
            } catch (Exception e) {
                out.print("Error: "+e.getMessage());
            }
        %>
    </body>
</html>