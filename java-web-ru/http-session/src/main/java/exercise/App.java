package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/users", ctx -> {
            String pageParam = ctx.queryParam("page");
            String perParam = ctx.queryParam("per");

            int page = (pageParam == null || pageParam.isEmpty()) ? 1 : Integer.parseInt(pageParam);
            int per = (perParam == null || perParam.isEmpty()) ? 5 : Integer.parseInt(perParam);

            int start = (page - 1) * per;
            int end = Math.min(start + per, USERS.size());

            if (start >= USERS.size() || start < 0) {
                ctx.status(404).json("No users found for the requested page.");
                return;
            }

            List<Map<String, String>> pagedUsers = USERS.subList(start, end);
            ctx.json(pagedUsers);
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
