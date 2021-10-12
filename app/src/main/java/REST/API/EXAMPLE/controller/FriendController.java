package REST.API.EXAMPLE.controller;

import REST.API.EXAMPLE.model.Friend;
import REST.API.EXAMPLE.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam("first") String firstName,
                                 @RequestParam("last") String lastName) {
        return friendService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    @PutMapping("/friend")
    Friend update(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }
}
