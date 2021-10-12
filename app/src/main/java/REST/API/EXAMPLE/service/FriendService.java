package REST.API.EXAMPLE.service;

import REST.API.EXAMPLE.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Integer> {
}
