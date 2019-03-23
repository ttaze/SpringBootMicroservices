package com.example.rentalHome.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ApiModel(description = "This Resource Model Delegate To UserCredentials")
public class UserCredentials {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @JsonIgnore
 @ApiModelProperty(notes = "The Auto Generated User Id")
 private Long id;

 @Column(unique = true)
 @NotNull
 @Email(message = "Please Insert Valid Email Id")
 @ApiModelProperty(notes = "The User Email Id to register in the system")
 private String emailId;

 @NotNull
 @Size(min = 8, message = "Please Password is More Than 8 Characters")
 @ApiModelProperty(notes = "User Password")
 private String password;

 @Size(min = 10, message = "Please Mobile No is More Than 10 Characters")
 @ApiModelProperty(notes = "User Mobile Number ")
 private String mobile;

 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "e_id", referencedColumnName = "email_id")
 @JsonIgnore
 private UserData userData;

 public UserCredentials(Long id, String emailId, String password, String mobile) {
  this.id = id;
  this.emailId = emailId;
  this.password = password;
  this.mobile = mobile;
 }

 public UserCredentials() {
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getEmailId() {
  return emailId;
 }

 public void setEmailId(String emailId) {
  this.emailId = emailId;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getMobile() {
  return mobile;
 }

 public void setMobile(String mobile) {
  this.mobile = mobile;
 }

}
