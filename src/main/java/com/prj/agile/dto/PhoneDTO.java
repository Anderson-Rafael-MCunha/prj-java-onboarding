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
public class PhoneDTO {

    @JsonIgnore
    private Integer id;
    private String area;
    private String ddd;
    private String number;
    private String phoneType;
    private String phoneStatus;

}
