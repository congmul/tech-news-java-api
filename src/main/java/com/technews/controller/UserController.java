package com.technews.controller;

import com.technews.model.Post;
import com.technews.model.User;
import com.technews.repository.UserRepository;
import com.technews.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Spring annotation
//@RestController will allow it to process JSON/XML responses
//and send JSON/XML objects via the API — which will be vital for the controller's functionality.
@RestController
public class UserController {
    // The @Autowired annotation tells Spring to scan the project for objects that will need to be instantiated for a class or method to run.
    // Unlike the new operator, which instantiates all objects before they're necessarily needed,
    // @Autowired informs Spring to only instantiate each object as needed by the program.
    // Then it can grab and inject the proper dependencies without having to manually wire anything in the XML.
    // This form of dependency injection improves efficiency and keeps the program light.
    @Autowired
    UserRepository repository;

    @Autowired
    VoteRepository voteRepository;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();
        for (User u : userList){
            List<Post> postList = u.getPosts();
            for(Post p : postList) {
                p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
            }
        }
        return userList;
    }

    @GetMapping("api/users/{id}")
    public User getUserById(@PathVariable Integer id){
        User returnUser = repository.getOne(id);
        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
        }

        return returnUser;
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody User user){
        // Encrypt password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repository.save(user);
        return user;
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        User tempUser = repository.getOne(id);

        if(!tempUser.equals(null)){
            user.setId(tempUser.getId());
            repository.save(user);
        }

        return user;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

}
