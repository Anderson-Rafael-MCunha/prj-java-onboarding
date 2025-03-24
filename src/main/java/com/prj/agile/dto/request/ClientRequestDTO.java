package com.prj.agile.dto.request;

import com.prj.agile.dto.response.AddressDTO;
import com.prj.agile.dto.response.ClientTypeDTO;
import com.prj.agile.dto.response.PhoneDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    private String document;
    private String name;
    private Date birthDate;
    private Date createdAt;
    private Boolean pep;
    private String email;
    private String status;
    private ClientTypeRequestDTO clientType;
    private AddressRequestDTO clientAddress;
    private List<PhoneRequestDTO> phones;
}
