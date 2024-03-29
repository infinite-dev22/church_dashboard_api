package io.nomard.spring_boot_api_template_v1.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginModel {
    private String email;
    private String password;
}
