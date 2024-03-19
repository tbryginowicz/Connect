package com.aplikacja.ProjektTestowanie.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Photo {

    int albumId;
    int id;
    String title;
    String url;
    String thumbnailUrl;
}
