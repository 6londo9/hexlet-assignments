package exercise.controller;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Predicate;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping("")
    public Iterable<User> getUsers(String firstName,
                                   String lastName) {

        if (firstName != null) {
            if (lastName != null) {
                return userRepository.findAll(
                        QUser.user.firstName.containsIgnoreCase(firstName)
                                .and(
                                        QUser.user.lastName.containsIgnoreCase(lastName)
                                )
                );
            }
            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        } else if (lastName != null) {
            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        } else {
            return userRepository.findAll();
        }
    }
    // END
}

