package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;

public final class App {

    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users", ctx -> {
            // Получаем параметр term, если он присутствует, или пустую строку, если его нет
            String searchTerm = ctx.queryParam("term");
            if (searchTerm == null) {
                searchTerm = "";
            }

            // Фильтруем пользователей по началу имени (без учета регистра)
            List<User> filteredUsers = USERS.stream()
                    .filter(user -> StringUtils.startsWithIgnoreCase(user.getFirstName(), searchTerm))
                    .collect(Collectors.toList());

            // Передаем список пользователей и текущий поисковый запрос в шаблон
            ctx.render("users/index.jte", model("page", new UsersPage(filteredUsers, searchTerm)));
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
