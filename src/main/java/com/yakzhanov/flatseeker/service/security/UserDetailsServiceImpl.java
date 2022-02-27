package com.yakzhanov.flatseeker.service.security;

import com.yakzhanov.flatseeker.model.infrastructure.User;
import com.yakzhanov.flatseeker.model.infrastructure.UserDetailsImpl;
import com.yakzhanov.flatseeker.repository.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username)
		.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

		return UserDetailsImpl.build(user);
	}

}
