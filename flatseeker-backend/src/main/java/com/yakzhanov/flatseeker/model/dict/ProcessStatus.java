package com.yakzhanov.flatseeker.model.dict;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ProcessStatus extends DictParent {

    private String color;
    private boolean active;

}
