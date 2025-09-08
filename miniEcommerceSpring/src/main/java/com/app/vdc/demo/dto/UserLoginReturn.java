package com.app.vdc.demo.dto;

import com.app.vdc.demo.Model.User;

public record UserLoginReturn(User user, String token) {

}
