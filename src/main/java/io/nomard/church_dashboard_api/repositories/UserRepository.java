package io.nomard.church_dashboard_api.repositories;

import io.nomard.church_dashboard_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    List<User> searchAllByEmailContainingIgnoreCase(String email);

    User findUserByEmail(String email);
}
