@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(content = @`
<html lang="ru">
<head>
    <meta charset="utf-8" />
</head>

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            Название статьи
            <input type="text" required name="title" value="${page.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            Текст статьи
            <input type="text" required name="content" value="${page.getContent()}" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>
</html>
`)