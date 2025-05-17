package com.smartbank.user_service.service;

import com.smartbank.user_service.dto.UserDto;
import com.smartbank.user_service.entity.Users;
import com.smartbank.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    // save the user
    public Users createUser(UserDto userDto) {
        try{
            Users user = new Users();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            return userRepository.save(user);
        } catch (Exception ignored) {
//            throw new RuntimeException("un know exception");//
            throw ignored;
        }
    }


    // fetch all users
    public List<Users> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        if(usersList.isEmpty()) {
            throw new RuntimeException("exception");
        }
        return usersList;
    }

    // find by id
    public Users findUserById(int id) {
        Users user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return user;
    }


    // update user
    public Users updateUser(int id, UserDto userDto) {
        Users existingUser = findUserById(id);
        if (userDto.getName() != null) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getPhone() != null) {
            existingUser.setPhone(userDto.getPhone());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        return userRepository.save(existingUser);
    }

    public String deleteUser(int id) {
        Users user = findUserById(id);
        userRepository.delete(user);
        return "User with id " + id + " is deleted..!";
    }
}
