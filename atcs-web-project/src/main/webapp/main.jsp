<jsp:useBean id="plane" class="com.atcs.objects.Plane" scope="session"/>
<jsp:setProperty name="plane" property="*"/>
<html>
   <head>
      <title>Using GET and POST Method to Read Form Data</title>
   </head>

   <body>
      <center>
      <h1>Using POST Method to Read Form Data</h1>

      <ul>
         <li><p><b>Id:</b>
            <%= plane.getId()%>
         </p></li>


      </ul>
      <br> Response code: <%= plane.getStatus() %>
      <jsp:forward page="index.jsp" />
   </body>
</html>