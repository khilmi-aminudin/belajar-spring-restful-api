package programmerzamannow.restful.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.RegisterUserRequest;
import programmerzamannow.restful.model.UserResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;
import programmerzamannow.restful.service.contracts.UserServiceInterface;

import java.util.Set;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public void register(RegisterUserRequest request) {

        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    @Override
    public UserResponse get(User user) {
        return UserResponse
                .builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

}
