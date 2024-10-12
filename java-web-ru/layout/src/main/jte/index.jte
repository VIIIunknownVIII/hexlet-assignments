@template("layout/page.jte")
@params(exercise.dto.users.UsersPage page)

<h1>Users List</h1>

<ul class="list-group">
    @for (var user : page.getUsers()) {
        <li class="list-group-item">
            <a href="/users/@{user.getId()}">
                @{user.getFirstName()} @{user.getLastName()}
            </a>
        </li>
    }
</ul>
