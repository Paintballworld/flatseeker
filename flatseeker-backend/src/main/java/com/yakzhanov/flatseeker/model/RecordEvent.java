package com.yakzhanov.flatseeker.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RecordEvent {

    private static enum EventType {
        STATUS_UPDATE,
        COMMENT,
    }

    @Id
    private String id;

    private String apartmentRecordId;

    private Date createdAt;

    private String details;

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    public static RecordEvent forNewStatus(String apartmentRecordId, ProcessStatus newStatus) {
        RecordEvent event = new RecordEvent();
        event.id = UUID.randomUUID().toString();
        event.setApartmentRecordId(apartmentRecordId);
        event.setDetails(String.format("Статус изменен на '%s'", newStatus.getTitle()));
        event.setCreatedAt(new Date());
        return event;
    }

    public static RecordEvent forComment(String apartmentRecordId, String comment) {
        RecordEvent event = new RecordEvent();
        event.id = UUID.randomUUID().toString();
        event.setApartmentRecordId(apartmentRecordId);
        event.setDetails(comment);
        event.setCreatedAt(new Date());
        return event;
    }

}
