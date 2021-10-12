package REST.API.EXAMPLE.controller;

import REST.API.EXAMPLE.model.Friend;
import REST.API.EXAMPLE.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend) throws ValidationException {
        if (friend.getId() == 0 && friend.getFirstName() != null && friend.getLastName() != null) {
            return friendService.save(friend);
        }
        else throw new ValidationException("Friend cannot be created");
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<String> exceptionHandler(ValidationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value ="first", required = false) String firstName,
                                 @RequestParam(value = "last", required = false) String lastName)
    {
        if (firstName != null && lastName != null)
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if (firstName != null)
            return friendService.findByFirstName(firstName);
        else if (lastName != null)
            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();
    }

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    //incase is updated with wrong id will return bad request/400
    @PutMapping("/friend")
    ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent()) {
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        }
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }
}
