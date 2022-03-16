package com.yakzhanov.flatseeker.model.dict;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class DictParent {

    @Id
    @GeneratedValue
    private Long id;
    protected String title;
    protected String key;
}
