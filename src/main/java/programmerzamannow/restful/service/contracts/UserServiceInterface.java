package programmerzamannow.restful.service.contracts;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.LoginUserRequest;
import programmerzamannow.restful.model.RegisterUserRequest;
import programmerzamannow.restful.model.TokenResponse;
import programmerzamannow.restful.model.UserResponse;

public interface UserServiceInterface {

    void register(RegisterUserRequest  request);

    UserResponse get(User user);
}
