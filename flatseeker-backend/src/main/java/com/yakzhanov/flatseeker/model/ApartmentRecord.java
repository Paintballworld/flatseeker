package com.yakzhanov.flatseeker.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private AnimalsStatus animalsStatus;
    @Enumerated(EnumType.STRING)
    private BathroomStatus bathroomStatus;

    private String location;

    private ApartmentType type;

    private Date createdAt;

    private String mainImageUrl;

    // Service
    @Enumerated(EnumType.STRING)
    private LocationStatus locationStatus;
    private String link;
    private String platformName;
    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus;
    private Date insertedAt;
    private boolean viewed;
    private boolean removed;

}

