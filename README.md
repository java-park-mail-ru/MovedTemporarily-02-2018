# MovedTemporarily-02-2018
Java course 02.2018. Project: MovedTemporarily

## REST API
### Sign up
#### URL: `/api/user/signup`
#### Method: `POST`
#### Params
```
{
    "login": string,
    "email": string,
    "password": string,
}
```

### Login
#### URL: `/api/user/login`
#### Method: `POST`
#### Params
```
{
    "login": string,
    "password": string,
}
```

### Log out
#### URL: `/api/user/logout`
#### Method: `GET`

### Change email
#### URL: /api/user/changeEmail`
#### Method: `POST`
#### Params
```
{
    "newEmail": string,
}
```

### Change password
#### URL: /api/user/changePass`
#### Method: `POST`
#### Params
```
{
    "oldPassword": string,
    "newPassword": string,
}
```

### User Info
#### URL: `/api/user/info`
#### Method: `GET`
