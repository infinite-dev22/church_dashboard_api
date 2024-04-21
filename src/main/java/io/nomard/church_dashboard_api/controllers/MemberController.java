package io.nomard.church_dashboard_api.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.church_dashboard_api.entities.Member;
import io.nomard.church_dashboard_api.models.DataCount;
import io.nomard.church_dashboard_api.models.SearchModel;
import io.nomard.church_dashboard_api.models.UserModel;
import io.nomard.church_dashboard_api.services.implementations.MemberServiceImpl;
import io.nomard.church_dashboard_api.errors.NotFoundException;
import io.nomard.church_dashboard_api.models.FindModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberServiceImpl userService;

    @GetMapping("/all")
    public List<Member> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "50") Integer pageSize) {
        return userService.getAll(pageNo, pageSize);
    }

    @GetMapping("/count")
    public Long countAll() {
        return userService.countAll();
    }

    @GetMapping("/count/date_joined")
    public List<DataCount> countGroupByDateJoined() {
        return userService.countAllByDateJoined();
    }

    @GetMapping("/single")
    public Member getById(@RequestBody FindModel findModel) throws NotFoundException {
        return userService.getById(findModel.getId());
    }

    @GetMapping("/search")
    public List<Member> getByContains(@RequestBody SearchModel searchModel) {
        return userService.getByContains(searchModel.getSearch());
    }

    @PostMapping("/add")
    public ObjectNode save(@Valid @RequestBody UserModel user) throws NotFoundException {
        return userService.add(user).getBody();
    }

    @PutMapping("/update")
    public ResponseEntity<ObjectNode> update(@Valid @RequestBody UserModel user) throws NotFoundException {
        return userService.update(user);
    }

    @DeleteMapping("/single/delete")
    public ResponseEntity<ObjectNode> delete(@RequestBody FindModel findModel) {
        return userService.delete(findModel.getId());
    }
}
