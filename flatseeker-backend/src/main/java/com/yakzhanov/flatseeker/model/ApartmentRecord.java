package com.yakzhanov.flatseeker.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class ApartmentRecord {

    @Id
    private String id;

    @ToString.Include
    private String title;
    private String description;

    private Integer rentPrice;
    private Integer feePrice;

    private Integer deposit;

    private Integer area;

    private Boolean conditioner;

    private AnimalsStatus animalsStatus;
    private BathroomStatus bathroomStatus;

    private String location;

    private ApartmentType type;

    private Date createdAt;

    private String mainImageUrl;


    // Service
    private LocationStatus locationStatus;
    private String link;
    private String platformName;
    private ProcessStatus processStatus;
    private String comment;
    private Date insertedAt;


}

