package io.nomard.church_dashboard_api.repositories;

import io.nomard.church_dashboard_api.entities.Member;
import io.nomard.church_dashboard_api.models.DataCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, JpaRepository<Member, Long> {
    List<Member> searchAllByEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrOtherNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(String email, String firstName, String lastName, String otherName, String phone);

    Member findUserByEmail(String email);

    @Query("select new io.nomard.church_dashboard_api.models.DataCount(m.dateJoined, count(m.id)) from Member m group by m.dateJoined order by m.dateJoined asc")
    List<DataCount> countOrderByDateJoined();
}
