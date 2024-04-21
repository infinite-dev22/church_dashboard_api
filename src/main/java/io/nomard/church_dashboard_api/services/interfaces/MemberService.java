package io.nomard.church_dashboard_api.services.interfaces;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.church_dashboard_api.entities.Member;
import io.nomard.church_dashboard_api.models.DataCount;
import io.nomard.church_dashboard_api.models.UserModel;
import io.nomard.church_dashboard_api.errors.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {
    List<Member> getAll(int pageNo, int pageSize);

    Member getById(Long id) throws NotFoundException;

    Member getByEmail(String email) throws NotFoundException;

    List<Member> getByContains(String search);

    ResponseEntity<ObjectNode> add(UserModel user) throws NotFoundException;

    ResponseEntity<ObjectNode> update(UserModel user) throws NotFoundException;

    ResponseEntity<ObjectNode> delete(Long id);

    Long countAll();
    List<DataCount> countAllByDateJoined();
}
