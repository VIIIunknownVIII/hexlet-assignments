@{! layout }
@{title = "Users"}

<h1>Users List</h1>
<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        @foreach (user : users) {
            <tr>
                <td>@{user.id}</td>
                <td>@{user.firstName}</td>
                <td>@{user.lastName}</td>
                <td>@{user.email}</td>
                <td><a href="/users/@{user.id}" class="btn btn-primary">View</a></td>
            </tr>
        }
    </tbody>
</table>
