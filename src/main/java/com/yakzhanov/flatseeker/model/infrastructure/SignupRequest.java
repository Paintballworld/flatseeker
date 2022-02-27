package com.yakzhanov.flatseeker.model.infrastructure;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.yakzhanov.flatseeker.conf.Constants;
import lombok.Data;

@Data
public class SignupRequest {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;
}
