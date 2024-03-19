package com.aplikacja.ProjektTestowanie.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Adress {

    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;
}
