package com.example.springdemo.service;

import com.example.springdemo.model.UsersModel;
import com.example.springdemo.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    public UsersModel registerUser( String login, String password, String email){
        if(login == null && password == null){
            return null;
        }
        else{
            if(usersRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}