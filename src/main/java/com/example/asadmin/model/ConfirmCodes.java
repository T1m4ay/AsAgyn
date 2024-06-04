package com.example.asadmin.model;

import com.example.asadmin.enumeration.CodeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "confirm_codes")
@Getter
@Setter
public class ConfirmCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "code_type")
    private CodeType codeType;
}
