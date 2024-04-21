package io.nomard.church_dashboard_api.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpModel {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String password;
    private String password2;
}
