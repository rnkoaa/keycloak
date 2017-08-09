package com.richard.spring.cloud.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 7/17/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
  private String userId;
  private String username;
  private String firstName;
  private String lastName;
  private String password;
}
