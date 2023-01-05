package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testPostNewUserWithValidData() {

        String firstName = "Ilya";
        String lastName = "Ostapenko";
        String email = "yahoo@gmail.com";
        String password = "123456";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asEmpty();

        assertThat(response.getStatus()).isEqualTo(302);

        User actual = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getEmail()).isEqualTo(email);
        assertThat(actual.getPassword()).isEqualTo(password);
    }

    @Test
    void testPostWithInvalidName() {

        String firstName = "";
        String lastName = "Herome";
        String email = "horom@gmail.com";
        String password = "123456";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        String content = response.getBody();
        assertThat(content).contains(lastName);
        assertThat(content).contains(email);
        assertThat(content).contains(password);
    }

    @Test
    void testPostWithInvalidLastName() {

        String firstName = "Horoom";
        String lastName = "";
        String email = "horom@gmail.com";
        String password = "123456";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        String content = response.getBody();
        assertThat(content).contains(firstName);
        assertThat(content).contains(email);
        assertThat(content).contains(password);
    }

    @Test
    void testPostWithInvalidEmail() {

        String firstName = "Horom";
        String lastName = "Herome";
        String email = "horom.com";
        String password = "123456";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        String content = response.getBody();
        assertThat(content).contains(lastName);
        assertThat(content).contains(email);
        assertThat(content).contains(password);
    }
    // END
}
