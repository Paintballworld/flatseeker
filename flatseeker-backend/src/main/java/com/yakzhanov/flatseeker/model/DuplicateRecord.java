package com.yakzhanov.flatseeker.model;

import java.util.Date;
import java.util.UUID;
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
@Data()
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class DuplicateRecord {

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

    private Date insertedAt;
    private String originalRecordId;

    public static DuplicateRecord duplicate(ApartmentRecord apartmentRecord) {
        return DuplicateRecord.builder()
          .id(UUID.randomUUID().toString())
          .title(apartmentRecord.getTitle())
          .description(apartmentRecord.getDescription())
          .rentPrice(apartmentRecord.getRentPrice())
          .feePrice(apartmentRecord.getFeePrice())
          .deposit(apartmentRecord.getDeposit())
          .area(apartmentRecord.getArea())
          .conditioner(apartmentRecord.getConditioner())
          .animalsStatus(apartmentRecord.getAnimalsStatus())
          .bathroomStatus(apartmentRecord.getBathroomStatus())
          .location(apartmentRecord.getLocation())
          .type(apartmentRecord.getType())
          .createdAt(apartmentRecord.getCreatedAt())
          .mainImageUrl(apartmentRecord.getMainImageUrl())
          .locationStatus(apartmentRecord.getLocationStatus())
          .link(apartmentRecord.getLink())
          .platformName(apartmentRecord.getPlatformName())
          .insertedAt(apartmentRecord.getInsertedAt())
          .originalRecordId(apartmentRecord.getId())
          .build();
    }
}

