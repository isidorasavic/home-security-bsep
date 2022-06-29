package com.ftn.MyHousebackend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "failed_logins")
public class UserFailedLogins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    @Column(name="failed_logins", nullable = false)
    private int failedLogins;

}
