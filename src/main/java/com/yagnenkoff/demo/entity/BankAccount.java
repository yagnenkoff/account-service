package com.yagnenkoff.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "create")
public class BankAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NonNull
    private Long uid;
    @Getter
    @Setter
    @NonNull
    @PositiveOrZero
    private Double balance;
}
