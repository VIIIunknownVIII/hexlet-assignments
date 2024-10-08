package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            ctx.render("users/index.jte", model("users", new UsersPage(USERS)));
        });

        app.get("/users/{id}", ctx -> {
            String id = ctx.pathParam("id");

            User user = USERS.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                // Возвращаем 404, если пользователь не найден
                throw new NotFoundResponse("User not found");
            }

            ctx.render("users/show.jte", model("user", new UserPage(user)));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
