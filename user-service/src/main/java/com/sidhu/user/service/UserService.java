package com.sidhu.user.service;

import com.sidhu.user.entity.User;
import com.sidhu.user.repository.UserRepository;
import com.sidhu.user.vo.AccountVO;
import com.sidhu.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public List<User> getAllUsers() {
        log.info("UserService: Get all users.");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("UserService: Get user by Id: " + id);
        return userRepository.getByUserId(id);
    }

    public User save(User user) {
        log.info("UserService: Save user.");
        return userRepository.save(user);
    }

    public UserVO getUserAccounts(Long id) {
        log.info("UserService: Get accounts for  User Id: " + id);
        User user = userRepository.getByUserId(id);
        UserVO userVO = new UserVO();
        if (user != null) {
            userVO.setUserId(user.getUserId());
            userVO.setFullName(user.getFirstName() + " " + user.getLastName());
            userVO.setEmail(user.getEmail());
            Boolean result = circuitBreakerFactory.create("account-service").run(() -> {
                ResponseEntity<AccountVO[]> response = restTemplate.getForEntity("http://ACCOUNT-SERVICE/accounts/user/" + id,
                        AccountVO[].class);
                userVO.setAccounts(List.of(response.getBody()));
                return true;
            }, throwable -> false);
            if (!result) {
                userVO.setError("Account Service is down");
            }
        }

        return userVO;
    }
}
