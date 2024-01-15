package programmerzamannow.restful.service.contracts;

import programmerzamannow.restful.model.LoginUserRequest;
import programmerzamannow.restful.model.TokenResponse;

public interface AuthServiceInterface {

    TokenResponse login(LoginUserRequest request);

}
