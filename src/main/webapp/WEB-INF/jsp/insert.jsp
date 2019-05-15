<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Hello ${name}!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<!--Main Navigation-->
<header>

    <nav class="navbar navbar-expand-lg navbar-dark default-color">
        <a class="navbar-brand" href="#"><strong>Online Music Store</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"><i class="fas fa-user"></i> <span class="clearfix d-none d-sm-inline-block">${user_name.name}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">logout</a>
                </li>
            </ul>

        </div>
    </nav>
</header>
<!--Main Navigation-->
<div class="container">
    <form class="text-center border border-light p-5" id="fileUploadForm">
        <div class="btn-group-vertical" role="group" aria-label="Vertical button group">

            <div class="md-form form-lg">
                <input type="text" id="filename" class="form-control form-control-lg">
                <label for="filename">Song Name</label>
            </div>

            <div class="md-form form-lg">
                <input type="text" id="artist" class="form-control form-control-lg">
                <label for="artist">Artist</label>
            </div>

            <div class="md-form form-lg">
                <input type="text" id="genre" class="form-control form-control-lg">
                <label for="genre">Genre</label>
            </div>

            <div class="md-form form-lg">
                <input type="text" id="year" class="form-control form-control-lg">
                <label for="year">Year</label>
            </div>


            <div class="input-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="songFile"
                           aria-describedby="inputGroupFileAddon01">
                    <label class="custom-file-label" for="songFile">Choose Song File</label>
                </div>

            </div>
            <div class="input-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="songImage"
                           aria-describedby="songImage">
                    <label class="custom-file-label" for="songImage">Choose Image file</label>
                </div>

            </div>
            <div class="btn-group-vertical" role="group" aria-label="Vertical button group">
                <button type="button" class="btn btn-default btn-rounded btn-lg" id="uploadButton">Submit</button>
            </div>
        </div>
    </form>
    <div class="upload-response">
        <div id="uploadError"></div>
        <div id="uploadSuccess"></div>
    </div>
</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.js"></script>

<script>

    var fileUploadForm = document.getElementById("fileUploadForm");
    var uploadButton = document.getElementById("uploadButton");
    var uploadSuccess = document.getElementById("uploadSuccess");
    var uploadError = document.getElementById("uploadError");
    function uploadSingleFile(songFile, imageFile, name, artist, genre, year) {
        var formData = new FormData();
        formData.append("file", songFile);
        formData.append("songImage", imageFile);
        formData.append("filename", name);
        formData.append("artist", artist);
        formData.append("genre", genre);
        formData.append("year", year);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/file/uploadFile");

        xhr.onload = function() {
            console.log(xhr.responseText);
            var response = JSON.parse(xhr.responseText);
            if(xhr.status == 200) {
                uploadError.style.display = "none";
                uploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
                uploadSuccess.style.display = "block";
            } else {
                uploadSuccess.style.display = "none";
                uploadError.innerHTML = (response && response.message) || "Some Error Occurred";
            }
        }

        xhr.send(formData);
    }

    uploadButton.addEventListener('click', function(event){
        console.log("Submitting");
        var songFile = document.getElementById("songFile").files;
        var imageFile = document.getElementById("songImage").files;
        var artist = document.getElementById("artist").value;
        var genre = document.getElementById("genre").value;
        var year = document.getElementById("year").value;
        var name = document.getElementById("filename").value;
        if(songFile.length === 0) {
            console.log("no song file")
        }
        else if(imageFile.length ===0){
            console.log("no Image Selected");
        }
        uploadSingleFile(songFile[0], imageFile[0], name, artist, genre, year);
        event.preventDefault();
    }, true);
</script>

</body>
</html>