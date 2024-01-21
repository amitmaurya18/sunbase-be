

package com.example.customercrud.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization

        String requestHeader =request.getHeader("Authorization");
        String username=null;
        String token =null;
        if(requestHeader!=null && requestHeader.startsWith("Bearer")){
            //looking goog7

            token = requestHeader.substring(7);

            try{
                username=this.jwtHelper.getUsernameFromToken(token);
            }
            catch(IllegalArgumentException e){

                e.printStackTrace();

            }
            catch(ExpiredJwtException e) {
                e.printStackTrace();
            }
            catch(MalformedJwtException e) {
                e.printStackTrace();
            }

            }
        else {

            System.out.println("invalid header value !!");

        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails  userDetails= this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token,userDetails);
            if(validateToken) {

                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            else{
                System.out.println("validation fails");
            }



        }

        filterChain.doFilter(request,response);
        }
    }



