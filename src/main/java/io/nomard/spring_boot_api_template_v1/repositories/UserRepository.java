package io.nomard.spring_boot_api_template_v1.repositories;

import io.nomard.spring_boot_api_template_v1.entities.User;
import io.nomard.spring_boot_api_template_v1.models.DataCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    List<User> searchAllByEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrOtherNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(String email, String firstName, String lastName, String otherName, String phone);

    User findUserByEmail(String email);

    @Query("select new io.nomard.spring_boot_api_template_v1.models.DataCount(u.dateJoined, count(u.id)) from User u group by u.dateJoined")
    List<DataCount> countOrderByDateJoined();
}
