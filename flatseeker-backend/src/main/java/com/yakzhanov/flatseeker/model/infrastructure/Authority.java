package com.yakzhanov.flatseeker.model.infrastructure;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "app_authority")
public class Authority implements Serializable {

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;
}
