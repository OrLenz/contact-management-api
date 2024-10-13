# User API Spec

## Register User 

Endpoint : POST /api/users

Request Body : 
``` json
{
    "username" : "len",
    "password" : "myp4ssword",
    "name" : "Lenando Respati Saldin Buana"
}
```

Reponse Body (Success) :
``` json
{
    "data" : "CREATED",
    "status" : "success"
}
```

Reponse Body (Failed) :
``` json
{
    "message" : "Username should not empty or blank",
    "status" : "failed"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body : 
``` json
{
    "username" : "len",
    "password" : "myp4ssword",
}
```

Reponse Body (Success) :
``` json
{
    "data" : {
        "token" : "exp_token",
        "expiredAt" : 17223455122334 // Miliseconds
    },
    "status" : "success"
}
```

Reponse Body (Failed 401) :
``` json
{
    "message" : "Username or Password invalid",
    "status" : "failed"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header : 
- X-API-TOKEN : Token (MANDATORY)

Reponse Body (Success) :
``` json
{
    "data" : "OK",
    "status" : "success"
}
```

Reponse Body (Failed) :
``` json
{
    "message" : "Username or Password invalid",
    "status" : "failed"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header : 
- X-API-TOKEN : Token (MANDATORY)

Reponse Body (Success) :
``` json
{
    "data" : {
        "username" : "len",
        "name" : "Lenando Respati Saldin Buana"
    },
    "status" : "success"
}
```

Reponse Body (Failed) :
``` json
{
    "message" : "Unauthorized",
    "status" : "failed"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header : 
- X-API-TOKEN : Token (MANDATORY)

Request Body : 
``` json
{
    "name" : "Lenando Respati", // Put if only want to update
    "password" : "myp4ssword" // Put if only want to update
}
```

Reponse Body (Success) :
``` json
{
    "data" : {
        "name" : "Lenando Respati", 
    "password" : "myp4ssword"
    },
    "status" : "success"
}
```

Reponse Body (Failed) :
``` json
{
    "message" : "Username or Password invalid",
    "status" : "failed"
}
```