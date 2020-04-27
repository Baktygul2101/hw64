<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lesson 61. Javascript 101</title>
        <link rel="stylesheet" href="/css/styles.css">
    </head>
    <body>
        <h1>Содержимое страницы</h1>

        <form id="form" name="form" enctype="multipart/form-data" action="/candidate" method="post">
            <input type="text" placeholder="Candidate Name" name="name" id="name">
            <input type="file" name="photo" id="photo">
            <button type="button" id="save-candidate">Save</button>
        </form>
    </body>
</html>

<script src="/js/script.js"></script>
