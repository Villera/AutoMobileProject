package com.villera.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "AutoRoles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JWT_VILLERA_AUTOROLES_SEQ")
    @SequenceGenerator(sequenceName = "jwt_villera_autoroles_seq", initialValue = 1, allocationSize = 1, name = "JWT_VILLERA_AUTOROLES_SEQ")
   private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}