package com.cjour.mediscreen.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {

    private String firstname;
    private String name;
    private Date birthdate;
    private String genre;
    private String address;
    private String phoneNumber;

}


