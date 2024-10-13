package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;

import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = Security.encrypt(ctx.formParam("password"));

        try {
            var user = UsersRepository.findByName(name)
                    .orElseThrow(() -> new NotFoundResponse("Wrong username or password"));

            if (user.getPassword().equals(password)) {
                ctx.sessionAttribute("currentUser", name);
                ctx.redirect("/");
            } else {
                throw new NotFoundResponse("Wrong username or password");
            }
        } catch (NotFoundResponse e) {
            var page = new LoginPage(name, e.getMessage());
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}