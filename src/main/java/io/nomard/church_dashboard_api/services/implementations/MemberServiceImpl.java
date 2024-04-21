package io.nomard.church_dashboard_api.services.implementations;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.church_dashboard_api.entities.Member;
import io.nomard.church_dashboard_api.repositories.MemberRepository;
import io.nomard.church_dashboard_api.errors.NotFoundException;
import io.nomard.church_dashboard_api.models.DataCount;
import io.nomard.church_dashboard_api.models.UserModel;
import io.nomard.church_dashboard_api.responses.SpotyResponseImpl;
import io.nomard.church_dashboard_api.services.interfaces.MemberService;
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
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository userRepo;
    @Autowired
    private SpotyResponseImpl spotyResponseImpl;

    @Override
    public List<Member> getAll(int pageNo, int pageSize) {
        //create page request object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        //pass it to repos
        Page<Member> page = userRepo.findAll(pageRequest);
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
    public Member getById(Long id) throws NotFoundException {
        Optional<Member> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    @Override
    public Member getByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public List<Member> getByContains(String search) {
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
        Member existingMember = userRepo.findUserByEmail(data.getEmail());

        if (existingMember != null && existingMember.getEmail() != null && !existingMember.getEmail().isEmpty()) {
            return spotyResponseImpl.taken();
        }

        Member member = new Member();
        member.setFirstName(data.getFirstName());
        member.setOtherName(data.getOtherName());
        member.setLastName(data.getLastName());
        member.setPhone(data.getPhone());
        member.setEmail(data.getEmail());
        member.setAddress(data.getAddress());
        member.setDateJoined(data.getDateJoined());
        member.setReceivedBy(data.getReceivedBy());
        member.setActive(data.isActive());
        member.setCreatedAt(new Date());

        try {
            userRepo.save(member);

            return spotyResponseImpl.created();
        } catch (Exception e) {
            return spotyResponseImpl.error(e);
        }
    }
}
