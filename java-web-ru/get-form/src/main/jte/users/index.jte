@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Пользователи</h1>
        <form class="input-group" action="/users">
            <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="term" value="${page.getTerm()}" />
            <input type="submit" class="btn btn-outline-primary" value="Search">
        </form>
        <table class="table table-striped">
            @for(var user : page.getUsers())
                <tr>
                    <td>
                        ${user.getId()}
                    </td>
                    <td>
                        ${user.getFirstName()}
                    </td>
                    <td>
                        ${user.getLastName()}
                    </td>
                </tr>
            @endfor
        </table>
    `
)
