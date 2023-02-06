package exercise.controller;

import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
//    @GetMapping("")
//    public Iterable<User> getUsers(@RequestParam(required = false) String firstName,
//                                   @RequestParam(required = false) String lastName) {
//
//        if (firstName == null && lastName == null) {
//            return userRepository.findAll();
//        } else if (firstName == null) {
//            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
//        } else if (lastName == null) {
//            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
//        } else {
//            return userRepository.findAll(
//                    QUser.user.firstName.containsIgnoreCase(firstName)
//                            .and(
//                                    QUser.user.lastName.containsIgnoreCase(lastName)
//                            )
//            );
//        }
//    }

    @Operation(summary = "Get list of all users with or w/o filters by:" +
            "firstName, lastName, Email, Profession, Gender")
    @ApiResponse(responseCode = "200", description = "List of all users that match filter params")
    @GetMapping("")
    public Iterable<User> getUser(@QuerydslPredicate(root = User.class) Predicate predicate) {
        if (predicate == null) {
            return userRepository.findAll();
        }
        return userRepository.findAll(predicate);
    }
    // END
}

