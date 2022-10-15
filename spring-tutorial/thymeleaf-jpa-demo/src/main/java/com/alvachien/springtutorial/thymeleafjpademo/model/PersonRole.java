package com.alvachien.springtutorial.thymeleafjpademo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Convert;
import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;
 
@Validated
@Entity
@Table(name = "person_role_def")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id;

    @Column(name="role_value", nullable = false, columnDefinition = "INT")
    @Convert(converter = PersonRoleEnumConverter.class)
    private PersonRoleEnum roleValue;

    @Column(name="role_name", nullable = true, length = 50)
    private String roleName;

    public PersonRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonRoleEnum getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(PersonRoleEnum roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
