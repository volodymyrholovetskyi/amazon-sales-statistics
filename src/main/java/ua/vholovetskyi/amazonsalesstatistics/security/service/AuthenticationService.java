package ua.vholovetskyi.amazonsalesstatistics.security.service;

import ua.vholovetskyi.amazonsalesstatistics.security.controller.dto.JwtTokenDto;
import ua.vholovetskyi.amazonsalesstatistics.security.controller.dto.LoginUserDto;
import ua.vholovetskyi.amazonsalesstatistics.security.controller.dto.RegisterUserDto;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-02-24
 */
public interface AuthenticationService {

    JwtTokenDto login(LoginUserDto loginUserDto);

    JwtTokenDto register(RegisterUserDto registerUserDto);


}
