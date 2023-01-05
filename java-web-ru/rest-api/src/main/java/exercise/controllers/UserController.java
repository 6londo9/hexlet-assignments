package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(it -> it.getFirstName().length() > 0, "Имя не должно быть пустым")
                .check(it -> it.getLastName().length() > 0, "Фамилия не должна быть пустой")
                .check(it -> EmailValidator.getInstance().isValid(it.getEmail()), "Email содержит ошибку")
                .check(it -> it.getPassword().length() > 0, "Пароль должен быть длиннее 4-х символов")
                .check(it -> StringUtils.isNumeric(it.getPassword()), "Пароль должен состоять только из цифр")
                .get();

        user.save();
        String validJson = DB.json().toJson(user);
        ctx.json(validJson);
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.eq(Integer.parseInt(id))
                .findOne();
        user.delete();
        // END
    };
}
