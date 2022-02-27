package com.yakzhanov.flatseeker.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.yakzhanov.flatseeker.model.infrastructure.JwtResponse;
import com.yakzhanov.flatseeker.model.infrastructure.LoginRequest;
import com.yakzhanov.flatseeker.model.infrastructure.MessageResponse;
import com.yakzhanov.flatseeker.model.infrastructure.SignupRequest;
import com.yakzhanov.flatseeker.model.infrastructure.User;
import com.yakzhanov.flatseeker.model.infrastructure.UserDetailsImpl;
import com.yakzhanov.flatseeker.repository.infrastructure.AuthorityRepository;
import com.yakzhanov.flatseeker.repository.infrastructure.UserRepository;
import com.yakzhanov.flatseeker.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthorityRepository authorityRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
          userDetails.getId(),
          userDetails.getUsername(),
          userDetails.getEmail(),
          roles));
    }

    @PostMapping("/create_account")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.getUsername())) {
            return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
          signUpRequest.getEmail(),
          encoder.encode(signUpRequest.getPassword()));

        user.setCreatedBy("SELF");

        var authority = authorityRepository.loadDefaultUserAuthority();
        user.setAuthorities(Collections.singleton(authority));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
