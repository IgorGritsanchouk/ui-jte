package com.ui.model;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

import static org.springframework.boot.ansi.AnsiOutput.getEnabled;

@Data
@Table("users")
public class User {
    @Id
    @NotBlank(message= "The user name should not be blank.")
    @Column("username")
    private String userName;

    //@Id
    @NotBlank(message= "The email should not be blank.")
//    @NotNull(message = "Email address must not be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message= "The password should not be blank.")
    private String password;

    private boolean enabled;

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(isEnabled(), user.isEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail(), getPassword(), getEmail(), isEnabled());
    }
}

//  old version
//@Data
//@Table("users")
//public class User {
//    @Id
//    private Long id;
//    @NotBlank(message= "The first name should not be blank.")
//    private String firstName;
//    @NotBlank(message= "The last name should not be blank.")
//    private String lastName;
//
//    @NotNull(message = "Email address must not be null")
//    @Email(message = "Email should be valid")
//    private String email;
//    private String country;
//    private String streetAddress;
//    private String city;
//    private String region;
//    private String postalCode;
//
//    public User(){}
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", country='" + country + '\'' +
//                ", streetAddress='" + streetAddress + '\'' +
//                ", city='" + city + '\'' +
//                ", region='" + region + '\'' +
//                ", postalCode='" + postalCode + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User user)) return false;
//        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getCountry(), user.getCountry()) && Objects.equals(getStreetAddress(), user.getStreetAddress()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getRegion(), user.getRegion()) && Objects.equals(getPostalCode(), user.getPostalCode());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getCountry(), getStreetAddress(), getCity(), getRegion(), getPostalCode());
//    }
//}
