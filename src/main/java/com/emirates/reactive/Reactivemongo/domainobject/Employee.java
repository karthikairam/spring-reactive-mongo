package com.emirates.reactive.Reactivemongo.domainobject;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {

    @Id
    private String id;
    private String name;
    private Double salary;
}
