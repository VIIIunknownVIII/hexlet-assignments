@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!-- sessions/build.jte -->
<!-- Нужно не забыть добавить маршрут в NamedRoutes -->
@template.layout.page(
content = @`
@if(page.getName() == null)
    <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
@else
    <p>Добро пожаловать, ${page.getName().toString()}</p>
<form action="${NamedRoutes.logoutPath()}" method="post">
    <input type="submit" value="Выйти" />
</form>
@endif
`)