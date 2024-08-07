package com.casava.library.service;

import com.casava.library.domain.User;
import com.casava.library.enumeration.ResponseType;
import com.casava.library.model.UserEntity;
import com.casava.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private User save(User user){
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    public User create(User user){
        if(existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already in use");
        }
        return save(user);
    }

    public User update(String id, User user){
        User oldRecord = findById(id);
        if(oldRecord == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        if(!oldRecord.getEmail().equalsIgnoreCase(user.getEmail()) && existsByEmail(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already in use");
        }
        user.setId(id);
        return save(user);
    }

    public ResponseType deleteById(String id){
        if(!existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Record not found");
        userRepository.deleteById(id);
        return ResponseType.SUCCESS;
    }



    public User findById(String id){
        return userRepository.findById(id).map(entity ->
                modelMapper.map(entity,User.class)).orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll().stream().map(entity ->
                modelMapper.map(entity,User.class)).toList();
    }

    public boolean existsById(String id){
        return userRepository.existsById(id);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
