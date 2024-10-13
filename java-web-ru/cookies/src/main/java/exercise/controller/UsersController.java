package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));

        var clientToken = ctx.cookie("token");
        var userToken = user.getToken();

        if (clientToken != null && clientToken.equals(userToken)) {
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }

    public static void create(Context ctx) throws Exception {
        var firstName = StringUtils.capitalize(ctx.formParam("firstName"));
        var lastName = StringUtils.capitalize(ctx.formParam("lastName"));
        var email = ctx.formParam("email").trim().toLowerCase();
        var password = Security.encrypt(ctx.formParam("password"));
        var token = Security.generateToken();

        var user = new User(
                firstName,
                lastName,
                email,
                password,
                token
        );

        UserRepository.save(user);

        ctx.cookie("token", token);

        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }
    // END
}