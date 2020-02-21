package com.udd.udd.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="system_user_id")
    private SystemUser systemUser;

    @Column
    private Date paymentDay;

    @Column
    private Date expirationDate;

}
