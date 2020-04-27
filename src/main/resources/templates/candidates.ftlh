<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Vote for us!</title>
    <link rel="stylesheet" href="/css/candidates.css">
</head>

<body>
    <div class="container flex flex-col align-center">
      <h1>Vote for us</h1>
      <main class="flex flex-wrap align-evenly" id="candidate-list">
        <#list candidates as person>
        <div class="card">
          <form action="/vote" method="post" class="flex flex-col align-center">
            <img src="/image/${person.photo}">
            <p>${person.name}</p>
            <input name="candidateId" type="hidden" value="${person.id}">
            <button type="submit" class="vote flex align-center">Vote</button>
          </form>
        </div>
        </#list>
      </main>
      <p>-------------------------------------------------------</p>
      <a class="back flex align-center" href="/votes">Show total votes</a>
    </div>
</body>
<script src="/js/script.js"></script>
</html>