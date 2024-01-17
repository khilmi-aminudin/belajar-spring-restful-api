package programmerzamannow.restful.service.contracts;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.*;

public interface UserServiceInterface {

    void register(RegisterUserRequest  request);

    UserResponse get(User user);

    UserResponse update(User user, UpdateUserRequest request);
}
