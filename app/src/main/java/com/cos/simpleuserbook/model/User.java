package com.cos.simpleuserbook.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private String name;
    private String tel;
    private String homePage;
}
