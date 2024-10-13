@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Посты</title>
</head>

<body>
<h1>Посты:</h1>
@if(page.getPosts().isEmpty())
    <p>Still no posts</p>
@else
    @for(var post : page.getPosts())
        <div>
            <p><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></p>
        </div>
    @endfor
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            @if(page.getPageNumber() > 1)
                <li class="page-item"><a class="page-link" href="?page=${(page.getPageNumber() - 1)}">Previous</a></li>
            @endif
            <li class="page-item"><a class="page-link" href="?page=${(page.getPageNumber() + 1)}">Next</a></li>
        </ul>
    </nav>
@endif
</body>

</html>