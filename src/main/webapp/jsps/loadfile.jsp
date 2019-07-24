<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <div clas ="fileuploadt_box">
    	<h1>File Upload Box </h1>
         <form method="POST" action="../upload" enctype="multipart/form-data" >
         	<label for="file">Select File:</label>
             <input type="file" name="file" id="file" /> <br/>
             <br/>
             <label for="sub_bu">Upload to DB:</label>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    </div>
        <style>
            body {
                  margin:0;
                  padding:0;
                  background-color: powderblue;
            	  background-position:center;
            	  font-family:sans-serif;
            	  text-align: center;
            }  
            
			label{
				font-family:sans-serif;
				font-size:12pt;
				width:100px;
				display:inline-block;
			}
		</style>        
    </body>
</html>