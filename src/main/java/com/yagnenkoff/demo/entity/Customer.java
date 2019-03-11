package com.yagnenkoff.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "create")
public class Customer{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @NonNull
    @Getter
    @Setter
    @Size(min=2, max=30)
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NonNull
    @Getter
    @Setter
    @Size(min=2, max=30)
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

}
