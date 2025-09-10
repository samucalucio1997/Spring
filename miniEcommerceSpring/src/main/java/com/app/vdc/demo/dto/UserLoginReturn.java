package com.app.vdc.demo.dto;

import com.app.vdc.demo.Model.User;
import lombok.Builder;

@Builder
public record UserLoginReturn(User user, String token) {

}
