# Movies API

## how to test


### Testing the application

### Default Users
You can set new users through application properties but there are two default user on the system

To add new user
```text
application.security.users.{USERNAME}.password=${YOUR_PASSWORD}
application.security.users.{USERNAME}.roles[0]=${YOUR_ROLE}
```

Default Users

Admin
```text
username : admin
password : admin
roles : [ROLE_ADMIN]
```
Normal
```text
username : user
password : user
roles : [ROLE_USER]
```

### Description
* Database already includes some data (user,role,) placed in yerevan area, But you can add company and stations.
* I defined two users.
* We have allowing anonymous access on /login so that users can authenticate a response containing the JWT to be returned to the user.

## Documentation

* [Swagger](http://localhost:8080/swagger-ui/index.html#/) - Documentation & Testing