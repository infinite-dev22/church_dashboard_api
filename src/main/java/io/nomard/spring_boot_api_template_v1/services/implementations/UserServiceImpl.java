package io.nomard.spring_boot_api_template_v1.services.implementations;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.spring_boot_api_template_v1.entities.User;
import io.nomard.spring_boot_api_template_v1.errors.NotFoundException;
import io.nomard.spring_boot_api_template_v1.models.DataCount;
import io.nomard.spring_boot_api_template_v1.models.UserModel;
import io.nomard.spring_boot_api_template_v1.repositories.UserRepository;
import io.nomard.spring_boot_api_template_v1.responses.SpotyResponseImpl;
import io.nomard.spring_boot_api_template_v1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SpotyResponseImpl spotyResponseImpl;

    @Override
    public List<User> getAll(int pageNo, int pageSize) {
        //create page request object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        //pass it to repos
        Page<User> page = userRepo.findAll(pageRequest);
        //page.hasContent(); -- to check pages are there or not
        return page.getContent();
    }

    @Override
    public Long countAll() {
        return userRepo.count();
    }

    @Override
    public List<DataCount> countAllByDateJoined() {
        return userRepo.countOrderByDateJoined();
    }

    @Override
    public User getById(Long id) throws NotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public List<User> getByContains(String search) {
        return userRepo.searchAllByEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrOtherNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                search.toLowerCase(),
                search.toLowerCase(),
                search.toLowerCase(),
                search.toLowerCase(),
                search.toLowerCase());
    }

    @Override
    public ResponseEntity<ObjectNode> update(UserModel data) throws NotFoundException {
        var opt = userRepo.findById(data.getId());
        if (opt.isEmpty()) {
            throw new NotFoundException();
        }

        var user = opt.get();

        if (Objects.nonNull(data.getFirstName()) && !"".equalsIgnoreCase(data.getFirstName())) {
            user.setFirstName(data.getFirstName());
        }

        if (Objects.nonNull(data.getFirstName()) && !"".equalsIgnoreCase(data.getFirstName())) {
            user.setFirstName(data.getFirstName());
        }

        if (Objects.nonNull(data.getLastName()) && !"".equalsIgnoreCase(data.getLastName())) {
            user.setLastName(data.getLastName());
        }

        if (Objects.nonNull(data.getOtherName()) && !"".equalsIgnoreCase(data.getOtherName())) {
            user.setOtherName(data.getOtherName());
        }

        if (Objects.nonNull(data.getEmail()) && !"".equalsIgnoreCase(data.getEmail())) {
            user.setEmail(data.getEmail());
        }

        if (Objects.nonNull(data.getPhone()) && !"".equalsIgnoreCase(data.getPhone())) {
            user.setPhone(data.getPhone());
        }

        if (Objects.nonNull(data.getAddress()) && !"".equalsIgnoreCase(data.getAddress())) {
            user.setAddress(data.getAddress());
        }

        user.setUpdatedAt(new Date());

        try {
            userRepo.saveAndFlush(user);

            return spotyResponseImpl.ok();
        } catch (Exception e) {
            return spotyResponseImpl.error(e);
        }
    }

    @Override
    public ResponseEntity<ObjectNode> delete(Long id) {
        try {
            userRepo.deleteById(id);

            return spotyResponseImpl.ok();
        } catch (Exception e) {
            return spotyResponseImpl.error(e);
        }
    }

    @Override
    public ResponseEntity<ObjectNode> add(UserModel data) throws NotFoundException {
        User existingUser = userRepo.findUserByEmail(data.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            return spotyResponseImpl.taken();
        }

        User user = new User();
        user.setFirstName(data.getFirstName());
        user.setOtherName(data.getOtherName());
        user.setLastName(data.getLastName());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setAddress(data.getAddress());
        user.setDateJoined(data.getDateJoined());
        user.setReceivedBy(data.getReceivedBy());
        user.setActive(data.isActive());
        user.setCreatedAt(new Date());

        try {
            userRepo.save(user);

            return spotyResponseImpl.created();
        } catch (Exception e) {
            return spotyResponseImpl.error(e);
        }
    }
}
