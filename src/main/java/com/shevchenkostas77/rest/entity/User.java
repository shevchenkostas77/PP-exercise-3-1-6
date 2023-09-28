package com.shevchenkostas77.rest.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}