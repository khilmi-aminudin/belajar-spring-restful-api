package programmerzamannow.restful.service.contracts;

import programmerzamannow.restful.model.LoginUserRequest;
import programmerzamannow.restful.model.RegisterUserRequest;
import programmerzamannow.restful.model.TokenResponse;

public interface UserServiceInterface {

    void register(RegisterUserRequest  request);
}
