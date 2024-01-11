package programmerzamannow.restful.controller;

import programmerzamannow.restful.model.RegisterUserRequest;
import programmerzamannow.restful.model.WebResponse;

public interface UserControllerInterface {

    WebResponse<String> register(RegisterUserRequest request);
}
