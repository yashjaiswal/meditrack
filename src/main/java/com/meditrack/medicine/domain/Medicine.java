package com.meditrack.medicine.domain;

import lombok.Data;

@Data
public class Medicine {

    private String name;
    private Integer strengthInMg;
    private String comments;
}
