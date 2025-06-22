package com.project.app.auth.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.app.auth.client.UserServiceClient;
import com.project.app.auth.dto.AuthDto;
import com.project.app.auth.dto.RoleActionFunctionDto;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration implements UserDetailsService  {

	private final UserServiceClient userServiceClient;

	@Bean
	public UserDetailsService userDetailsService() {
		return this::loadUserByUsername;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		AuthDto authDto = userServiceClient.getUserByUsername(username).getBody();

		if (authDto == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}

		Type listType = new TypeToken<List<RoleActionFunctionDto>>() {}.getType();
		List<RoleActionFunctionDto> functions = gson.fromJson(authDto.getRoles(), listType);

		Set<GrantedAuthority> authorities = functions.stream()
				.filter(menu -> menu.getActions() != null || menu.getItems() != null)
				.flatMap(menu -> {
					Stream<GrantedAuthority> actionStream = menu.getActions().entrySet().stream()
							.filter(entry -> Boolean.TRUE.equals(entry.getValue()))
							.map(entry -> new SimpleGrantedAuthority(entry.getKey() + "_" + menu.getCode()));

					Stream<GrantedAuthority> itemStream = menu.getItems().stream()
							.flatMap(item -> item.getActions().entrySet().stream()
									.filter(entry -> Boolean.TRUE.equals(entry.getValue()))
									.map(entry -> new SimpleGrantedAuthority(entry.getKey() + "_" + item.getCode())));

					return Stream.concat(actionStream, itemStream);
				})
				.collect(Collectors.toSet());

		return new CustomUserDetails(authDto, authorities);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
