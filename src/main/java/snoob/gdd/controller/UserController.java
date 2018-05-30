package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.util.RandomString;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    @PostMapping("/register")
    public Object register(@RequestBody User user){
        user.setId(RandomString.getUuidStr());
        return user;
    }
}
