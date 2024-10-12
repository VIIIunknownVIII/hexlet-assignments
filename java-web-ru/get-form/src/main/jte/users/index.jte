@import exercise.dto.users.UsersPage
@param UsersPage page

<div class="container">
    <h1 class="mb-4">User List</h1>

    <!-- Форма для фильтрации пользователей -->
    <form action="/users" method="get" class="mb-4">
        <div class="mb-3">
            <label for="term" class="form-label">Search by first name:</label>
            <input type="text" class="form-control" id="term" name="term" value="${page.getSearchTerm()}">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Если есть пользователи, выводим их список -->
    @if(page.getUsers().isEmpty()) {
        <p>No users found</p>
    } else {
        <ul class="list-group">
            @for(user : page.getUsers()) {
                <li class="list-group-item">
                    <strong>${user.getFirstName()} ${user.getLastName()}</strong>
                    <br />
                    <a href="mailto:${user.getEmail()}">${user.getEmail()}</a>
                </li>
            }
        </ul>
    }
</div>
