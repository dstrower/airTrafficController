<jsp:useBean id = "plane" class = "com.atcs.action.RemovePlaneFromQueue" />
<html>
   <head>
      <title>Plane Removed From Queue</title>
            <style>
            table, th, td {
              border: 1px solid black;
            }
            </style>
   </head>

   <body>
      <center>
      <h1>This plane was removed from the queue</h1>
        <%= plane.getPlane() %>
       <table style="width:100%">
           <tr>
             <th>Id</th>
             <th>Type</th>
             <th>Size</th>
           </tr>
           <tr>
              <td> <%= plane.getId() %> </td>
              <td> <%= plane.getType() %> </td>
              <td> <%= plane.getSize() %> </td>
       </table>


      <a href="index.jsp">Home Page</a>

   </body>
</html>