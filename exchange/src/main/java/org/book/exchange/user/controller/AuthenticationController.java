package org.book.exchange.user.controller;

import org.book.exchange.common.ApiResponse;
import org.book.exchange.config.security.AuthToken;
import org.book.exchange.user.dto.LoginUser;
import org.book.exchange.config.security.util.JwtTokenUtil;
import org.book.exchange.user.domain.User;
import org.book.exchange.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtTokenUtil jwtTokenUtil;

  @Autowired private UserService userService;

  @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
  public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser)
      throws AuthenticationException {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
    final User user = userService.getByEmail(loginUser.getUsername());
    final String token = jwtTokenUtil.generateToken(user);
    return new ApiResponse<>(200, "success", new AuthToken(token, user.getUsername()));
  }
}
