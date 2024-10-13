package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.User;
import exercise.dto.users.UsersPage;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users", ctx -> {
            // Получаем параметр term для фильтрации
            String term = ctx.queryParam("term");

            // Фильтруем пользователей по началу имени без учета регистра
            List<User> filteredUsers = USERS.stream()
                    .filter(user -> term == null || StringUtils.startsWithIgnoreCase(user.getFirstName(), term))
                    .collect(Collectors.toList());

            // Передаем данные на шаблон
            ctx.render("users/index.jte", model("page", new UsersPage(filteredUsers, term)));
        });

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
