<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form method="POST" action="../upload" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
            <input type="submit" value="UploadToDataBase" name="upload" id="upload" />
        </form>
        
    </body>
</html>