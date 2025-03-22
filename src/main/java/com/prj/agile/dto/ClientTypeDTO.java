package com.prj.agile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prj.agile.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientTypeDTO {

    @JsonIgnore
    private Integer id;
    private String description;

}
