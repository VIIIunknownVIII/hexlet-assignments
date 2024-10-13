@import exercise.dto.users.UsersPage
@param UsersPage page

<div class="container">
    <h1>User List</h1>

    <!-- Форма для фильтрации -->
    <form method="GET" action="/users">
        <div class="mb-3">
            <label for="term" class="form-label">Search by first name:</label>
            <input type="text" class="form-control" id="term" name="term" value="${page.getSearchTerm() != null ? page.getSearchTerm() : ""}">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Проверяем, есть ли пользователи -->
    <ul class="mt-4">
        @if(page.getUsers().isEmpty()) {
            <p>No users found</p>
        } else {
            @for(User user : page.getUsers()) {
                <li>${user.getFirstName()} ${user.getLastName()} - ${user.getEmail()}</li>
            }
        }
    </ul>
</div>
