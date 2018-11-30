package org.book.exchange.config.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthToken {

  private String token;
  private String username;

  public AuthToken() {}

  public AuthToken(String token, String username) {
    this.token = token;
    this.username = username;
  }

  public AuthToken(String token) {
    this.token = token;
  }
}
