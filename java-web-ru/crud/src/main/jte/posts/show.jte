@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>${page.getPost().getName()}</title>
</head>

<body>
    <h1>${page.getPost().getName()}</h1>
    <p>${page.getPost().getBody()}</p>
    <p><a href="${NamedRoutes.postsPath()}">Вернуться к списку постов</a></p>
</body>

</html>