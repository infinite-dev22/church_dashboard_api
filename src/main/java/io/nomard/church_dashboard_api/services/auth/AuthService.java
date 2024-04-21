package io.nomard.church_dashboard_api.services.auth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.church_dashboard_api.entities.User;
import io.nomard.church_dashboard_api.errors.NotFoundException;
import io.nomard.church_dashboard_api.models.LoginModel;
import io.nomard.church_dashboard_api.models.SignUpModel;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ObjectNode> register(SignUpModel signUpDetails) throws NotFoundException;

    ResponseEntity<ObjectNode> login(LoginModel loginDetails) throws NotFoundException;

    User authUser();
}
