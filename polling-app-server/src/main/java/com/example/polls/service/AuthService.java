package com.example.polls.service;

import com.example.polls.exception.AppException;
import com.example.polls.models.entity.Role;
import com.example.polls.models.entity.RoleName;
import com.example.polls.models.entity.User;
import com.example.polls.models.payload.ApiResponse;
import com.example.polls.models.payload.JwtAuthenticationResponse;
import com.example.polls.models.payload.LoginRequest;
import com.example.polls.models.payload.SignUpRequest;
import com.example.polls.repository.RoleRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager ;
    private final JwtService jwtService ;
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;
    public JwtAuthenticationResponse signingUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail() ,
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication) ;
        return new JwtAuthenticationResponse(jwt) ;

    }
    public ResponseEntity<?> registerUser(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false,"Username is already taken!"),
                    HttpStatus.BAD_REQUEST) ;
        }
        if (userRepository.existsByUsername(request.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false ,"Email Adress already in use"),
                    HttpStatus.BAD_REQUEST) ;
        }
        User user = new User(request.getName(), request.getUsername(),
                request.getEmail(), request.getPassword()) ;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(()-> new AppException("User Role not set")) ;
        user.setRoles(Collections.singleton(userRole));
        User resault = userRepository.save(user) ;
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(request.getUsername()).toUri() ;
        return ResponseEntity.created(location).body(new ApiResponse(true , "User registered successfully"));
    }
}
