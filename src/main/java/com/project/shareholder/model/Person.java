package com.project.shareholder.model;

import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})
        }
)

public class Person extends CommonSerialize {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Column(name = "personal_id", unique = true)
    private String personalId;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "total_stock")
    private double totalStock;

    @Column(name = "total_profit")
    private double totalProfit;

    @Column(name = "referer_id")
    private UUID refererId;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PersonProfit> personProfits;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PersonQuarter> personQuarters;

    // Getter and setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public UUID getRefererId() {
        return refererId;
    }

    public void setRefererId(UUID refererId) {
        this.refererId = refererId;
    }

    public List<PersonProfit> getPersonProfits() {
        return personProfits;
    }

    public void setPersonProfits(List<PersonProfit> personProfits) {
        this.personProfits = personProfits;
    }

    public List<PersonQuarter> getPersonQuarters() {
        return personQuarters;
    }

    public void setPersonQuarters(List<PersonQuarter> personQuarters) {
        this.personQuarters = personQuarters;
    }
}

