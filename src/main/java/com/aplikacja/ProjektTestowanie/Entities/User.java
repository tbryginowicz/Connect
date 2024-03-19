package com.aplikacja.ProjektTestowanie.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    int id;
    String name;
    String username;
    String email;
    Adress adress;
}
