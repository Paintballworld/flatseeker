package com.yakzhanov.flatseeker.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.yakzhanov.flatseeker.model.dict.AnimalStatus;
import com.yakzhanov.flatseeker.model.dict.ApartmentType;
import com.yakzhanov.flatseeker.model.dict.BathroomStatus;
import com.yakzhanov.flatseeker.model.dict.LocationStatus;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
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

    @OneToOne(fetch = FetchType.EAGER)
    private AnimalStatus animalStatus;

    @OneToOne(fetch = FetchType.EAGER)
    private BathroomStatus bathroomStatus;

    private String location;

    @OneToOne(fetch = FetchType.EAGER)
    private ApartmentType apartmentType;

    private Date createdAt;

    private String mainImageUrl;

    // Service
    @OneToOne(fetch = FetchType.EAGER)
    private LocationStatus locationStatus;

    private String link;

    private String platformName;

    @OneToOne(fetch = FetchType.EAGER)
    private ProcessStatus processStatus;

    private Date insertedAt;

    private boolean viewed;

    private boolean removed;

}

