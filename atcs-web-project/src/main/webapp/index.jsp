<html>
   <head>
      <title>Using JavaBeans in JSP</title>
      <style>
      table, th, td {
        border: 1px solid black;
      }
      </style>
   </head>
   <body>
      <center>
         <h2>Air Traffic Control</h2>

         <jsp:useBean id = "queue" class = "com.atcs.action.ReadPlaneQueue" />

         <h2> Plane Queue </h2>
         <table style="width:100%">
           <tr>
             <th>Id</th>
             <th>Type</th>
             <th>Size</th>
           </tr>
            <jsp:getProperty name = "queue" property = "queue" />
         </table>
      </center>
   </body>
</html>
