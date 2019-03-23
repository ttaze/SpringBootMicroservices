package com.example.rentalHome.Controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.JSONResponse;
import com.example.rentalHome.Domains.UserData;
import com.example.rentalHome.Exceptions.MyResourceNotFoundException;
import com.example.rentalHome.Services.UserDataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "UsersOperation",description="User Controller That Can Provide Resource Endpoints")
public class UserHomeController {

    @Autowired
    private UserDataService userDataService;

    @ApiOperation(value = "UserSingUp",position = 1,notes="User can singup with the self credentials",response=UserCredentials.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Successfully Register"),
            @ApiResponse(code = 404,message = "Resource that not full fill The Request payload")
    })
    @PostMapping("/api/singup/v1.0")
    public ResponseEntity<Resource<UserCredentials>> userSingInMethod(@ApiParam(value = "User can singUp UserCredentials Object",required = true) @Valid @RequestBody UserCredentials userCredentials)
    {
        UserCredentials inputUserCredentials = userDataService.userSingIn(userCredentials);
        Resource<UserCredentials> jsonResponseResource = new Resource<UserCredentials>(inputUserCredentials);
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).userSingInMethod(userCredentials)).withSelfRel().withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).userLogInMethod(userCredentials)).withRel("UserLogin").withType("POST"));
        return new ResponseEntity<Resource<UserCredentials>>(jsonResponseResource,HttpStatus.CREATED);
    }

    @ApiOperation(value = "UserLogIn",position = 5,notes = "User can login",response=UserCredentials.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User can Login Successfully"),
            @ApiResponse(code = 404,message = "This User Not Found")
    })
    @PostMapping("/api/login/v1.0")
    public ResponseEntity<Resource<UserData>> userLogInMethod(@ApiParam(value = "User Can Login In System") @Valid @RequestBody UserCredentials userCredentials)
    {
        UserData userData = userDataService.userLogIn(userCredentials);
        Resource<UserData> jsonResponseResource = new Resource<UserData>(userData);
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).userSingInMethod(userCredentials)).withRel("UserSingIn").withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).userLogInMethod(userCredentials)).withSelfRel().withType("POST"));
        return new ResponseEntity<Resource<UserData>>(jsonResponseResource,HttpStatus.OK);
    }


    @ApiOperation(value = "ShowAllUser",position = 4,response = UserData.class)
    @GetMapping("/api/show_all_users/v1.0")
    @ApiResponses(value ={
            @ApiResponse(code = 200,message = "All User are Found"),
            @ApiResponse(code = 404,message = "Not User Found")
    }
    )
    public ResponseEntity<Collection<Resource<UserData>>> getAllUser() {

        List<UserData> userData = userDataService.showAllUser();

        if(userData.isEmpty())
        {
          return  new ResponseEntity<Collection<Resource<UserData>>>(HttpStatus.NOT_FOUND);
        }

        List<Resource<UserData>> resources = new ArrayList<Resource<UserData>>();
               for(UserData userData1 : userData)
               {
                   resources.add(getUserDataResource(userData1));
               }

        return new ResponseEntity<Collection<Resource<UserData>>>(resources,HttpStatus.OK);
    }

    private Resource<UserData> getUserDataResource(UserData userData)  {
        Resource<UserData> resource = new Resource<UserData>(userData);
        resource.add(linkTo(methodOn(UserHomeController.class).getAllUser()).withSelfRel().withType("GET"));
        resource.add(linkTo(methodOn(UserHomeController.class).updateUser(null)).withRel("UpdateUser").withType("PUT"));
        resource.add(linkTo(methodOn(UserHomeController.class).deleteUser(null)).withRel("DeleteUser").withType("DELETE"));
        resource.add(linkTo(methodOn(UserHomeController.class).searchUser(null)).withRel("SearchUser").withType("POST"));
        resource.add(linkTo(methodOn(UserHomeController.class).createUser(null)).withRel("CreateUser").withType("POST"));
        return resource;
    }

    @ApiOperation(value = "CreateUser",position = 2,notes="User can fill the Qwn Details",response = UserData.class)
    @ApiResponses({
            @ApiResponse(code = 201,message="User Data Successfully Inserted"),
            @ApiResponse(code =404,message="Resource that not full fill The Request payload")
    })
    @PostMapping("api/create_user/v1.0")
    public ResponseEntity<Resource<UserData>> createUser(@ApiParam(value = "UserData Object Resource",required = true) @Valid @RequestBody UserData user)  {
        UserData userData = userDataService.createUser(user);
        Resource<UserData> jsonResponseResource = new Resource<UserData>(userData);
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).createUser(user)).withSelfRel().withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).getAllUser()).withRel("ShowAllUser").withType("GET"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).searchUser(null)).withRel("SearchUser").withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).deleteUser(null)).withRel("DleteUser").withType("DELETE"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).updateUser(null)).withRel("UpdateUser").withType("PUT"));
        return new ResponseEntity<Resource<UserData>>(jsonResponseResource,HttpStatus.CREATED);
    }

    @ApiOperation(value = "SearchUser",position = 3,notes="Admin can Search Paticular User By UserId",response = UserData.class)
    @ApiResponses({
            @ApiResponse(code = 200,message = "User can find Successfully"),
            @ApiResponse(code = 404,message = "User is Not Found")
    })
    @PostMapping("/api/search_user/v1.0")
    public ResponseEntity<Resource<UserData>> searchUser(@ApiParam(value = "UserData Object Resource By Id",required = true) @Valid @RequestBody UserData user) {
        UserData userData  = userDataService.searchUser(user.getUserId());
        Resource<UserData> jsonResponseResource = new Resource<UserData>(userData);
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).searchUser(user)).withSelfRel().withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).getAllUser()).withRel("ShowAllUser").withType("GET"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).createUser(null)).withRel("CreateUser").withType("GET"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).deleteUser(null)).withRel("DleteUser").withType("DELETE"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).updateUser(null)).withRel("UpdateUser").withType("PUT"));
        return new ResponseEntity<Resource<UserData>>(jsonResponseResource,HttpStatus.OK);
    }

    @ApiOperation(value = "DeleteUser",position = 7,response = UserData.class)
    @DeleteMapping("/api/deleteuser/v1.0")
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "User Are Delted Successfully"),
            @ApiResponse(code = 404,message = "User Not Found")
    })
    public ResponseEntity<JSONResponse> deleteUser(@ApiParam(value = "User Data Resource are Deleted",required = true)@Valid @RequestBody UserData user)
    {
        JSONResponse jsonResponse = new JSONResponse();
        userDataService.deleteUser(user.getUserId());
        jsonResponse.setData(null);
        jsonResponse.setResult(JSONResponse.SUCCESS);
        jsonResponse.setMessage("Data Deleted");

        return new ResponseEntity<>(jsonResponse,HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "UpdateUser",position = 6,notes = "User Detiles can Be Update",response = UserData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User Detiles Update Successfully"),
            @ApiResponse(code = 404,message = "This User Not Found")
    })
    @PutMapping("/api/updateuser/v1.0")
    public ResponseEntity<Resource<UserData>> updateUser(@ApiParam(value = "UserData Object Resource Update",required = true) @Valid @RequestBody UserData user) {
        UserData userData  = userDataService.updateUser(user);
        Resource<UserData> jsonResponseResource = new Resource<UserData>(userData);
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).getAllUser()).withRel("ShowAllUser").withType("GET"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).searchUser(null)).withRel("SearchUser").withType("GET"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).deleteUser(null)).withRel("DleteUser").withType("DELETE"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).createUser(null)).withRel("CrateUser").withType("POST"));
        jsonResponseResource.add(linkTo(methodOn(UserHomeController.class).updateUser(user)).withSelfRel().withType("PUT"));
        return new ResponseEntity<Resource<UserData>>(jsonResponseResource,HttpStatus.OK);
    }
}
