

package com.example.customercrud.controller;


import com.example.customercrud.dtos.JwtResponse;
import com.example.customercrud.dtos.LoginDto;
import com.example.customercrud.exception.BadApiRequestException;
import com.example.customercrud.security.JwtHelper;
import com.example.customercrud.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    Logger logger= LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ModelMapper mapper;



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login (@RequestBody LoginDto loginDto){
        this.doAuthenticate(loginDto.getEmail(),loginDto.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(loginDto.getEmail());
        String token =this.jwtHelper.generateToken(userDetails);
        JwtResponse response= JwtResponse.builder()
                .jwtToken(token)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    private void doAuthenticate(String email, String password){
        logger.info("Attempting authentication for email: {}", email);

        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(email,password);

        try{
            authenticationManager.authenticate(authenticationToken);
            logger.info("Authentication successful for email: {}", email);

        }
        catch(BadCredentialsException e){
            logger.error("Authentication failed for email: {}", email, e);

            throw new BadApiRequestException("invalid username and password !!");

        }
    }

    @GetMapping("/current")
    public ResponseEntity<LoginDto> getCurrent(Principal principal){
        String name= principal.getName();
        return new ResponseEntity<>(mapper.map(userDetailsService.loadUserByUsername(name), LoginDto.class), HttpStatus.OK);
    }






}

