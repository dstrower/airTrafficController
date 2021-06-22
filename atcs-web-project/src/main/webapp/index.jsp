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
         <h2> Add to Queue </h2>
              <form action = "main.jsp" method = "POST" >
              <table>
                 <tr><td>Plane Id </td>
                 <td><input type = "text" name = "id"></td>
                 </tr>
                  <tr>
                    <td>Type </td>
                    <td><input type = "text" name = "type" /> </td>
                  </tr>
                  <tr>
                    <td>Size </td>
                    <td><input type = "text" name = "size" /> </td>
                  </tr>
              </table>
               <input type = "submit" value = "Submit" />
               </form>

   </body>
</html>
