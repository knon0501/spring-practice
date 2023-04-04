package hello.hellospring.users;


import hello.hellospring.Photos.PhotoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserEntity> findAllUser(){
        System.out.println(1);
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public UserDto findUserbyid(@PathVariable("id") Long id){
        return userService.findUserbyid(id);
    }

    @PostMapping("")
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable("id") Long id){
        return userService.deletebyId(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id")  Long id ,@RequestBody UserDto userDto){
        return userService.updatebyId(id,userDto);
    }

    @PostMapping("/{id}")
    public UserDto addPhoto(@PathVariable("id") Long id, @RequestBody PhotoEntity photoEntity){
        return userService.addPhoto(id,photoEntity);
    }

    @DeleteMapping("/{id1}/{id2}")
    public UserDto deletePhoto(@PathVariable("id1") Long user_id,@PathVariable("id2")Long photo_id){
        return userService.deletePhoto(user_id,photo_id);
    }

}
