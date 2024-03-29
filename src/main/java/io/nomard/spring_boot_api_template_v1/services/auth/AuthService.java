package io.nomard.spring_boot_api_template_v1.services.auth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.spring_boot_api_template_v1.entities.User;
import io.nomard.spring_boot_api_template_v1.errors.NotFoundException;
import io.nomard.spring_boot_api_template_v1.models.LoginModel;
import io.nomard.spring_boot_api_template_v1.models.SignUpModel;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ObjectNode> register(SignUpModel signUpDetails) throws NotFoundException;

    ResponseEntity<ObjectNode> login(LoginModel loginDetails) throws NotFoundException;

    User authUser();
}
