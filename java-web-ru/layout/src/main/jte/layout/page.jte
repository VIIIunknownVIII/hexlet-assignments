@{! layout }

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@{title}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <header class="bg-light p-3">
        <nav class="container">
            <a class="navbar-brand" href="/">Home</a>
            <a class="nav-link" href="/users">Users</a>
        </nav>
    </header>

    <main class="container mt-4">
        @{! content }
    </main>

    <footer class="bg-light text-center p-3 mt-4">
        <p>© 2024 Your Name - <a href="https://gitlab.com/yourprofile">My GitLab Profile</a></p>
    </footer>
</body>
</html>
