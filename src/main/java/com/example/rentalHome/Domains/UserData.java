package com.example.rentalHome.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ApiModel(description="This Resource Model Delegate To UserData")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The Auto Generated User Id")
	private Long userId;

	@Size(min=12, message="Name should have atleast 12 characters")
	@ApiModelProperty(notes = "User Name")
	private String name;

	@Size(max=10, message="Mobile No should have atleast 10 characters")
	@ApiModelProperty(notes = "User Contact")
    private String contact;

	@Email(message = "Please Insert Valid Email Id")
	@Column(name = "email_id")
	@ApiModelProperty(notes = "User EmailId")
    private String email;

	@ApiModelProperty(notes = "User Address")
    private String address;

	@ApiModelProperty(notes = "User DOB")
    private String dob;

	@ApiModelProperty(notes = "User Gender")
    private String gender;

	@ApiModelProperty(notes = "User ProfilePicture")
    private String profilePicture;

}

