package com.example.customercrud.service.impl;
import com.example.customercrud.model.Customer;
import com.example.customercrud.model.Login;
import com.example.customercrud.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with given email not found !!"));
        return login;
    }
}


