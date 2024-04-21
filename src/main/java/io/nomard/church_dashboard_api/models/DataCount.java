package io.nomard.church_dashboard_api.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataCount {
    private Date dateJoined;
    private Long count;

    //This constructor will be used by Spring Data JPA
    //for creating this class instances as per result set
    public DataCount(Date dateJoined, long count) {
        this.dateJoined = dateJoined;
        this.count = count;
    }
}