package com.prj.agile.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
    private String complement;
    private String zipCode;

}
