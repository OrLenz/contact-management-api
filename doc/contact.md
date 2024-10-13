# Contact API Spec

## Create Contact

Endpoint : POST /api/contacts

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
    "firstName" : "Lenando Respati",
    "lastName" : "Saldinbuana",
    "email" : "lenando.test@gmail.com",
    "phone" : "081234567654"
}
```

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "firstName" : "Lenando Respati",
        "lastName" : "Saldinbuana",
        "email" : "lenando.test@gmail.com",
        "phone" : "081234567654"
    },
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "Email format invalid, phone format invalid",
    "status" : "failed"
}
```

## Update Contact

Endpoint : PUT /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
    "firstName" : "Lenando Respati",
    "lastName" : "Saldinbuana",
    "email" : "lenando.test@gmail.com",
    "phone" : "081234567654"
}
```

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "firstName" : "Lenando Respati",
        "lastName" : "Saldinbuana",
        "email" : "lenando.test@gmail.com",
        "phone" : "081234567654"
    },
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "Email format invalid, phone format invalid",
    "status" : "failed"
}
```

## Get Contact

Endpoint : GET /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "firstName" : "Lenando Respati",
        "lastName" : "Saldinbuana",
        "email" : "lenando.test@gmail.com",
        "phone" : "081234567654"
    },
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "contact is not found",
    "status" : "failed"
}
```

## Search Contact

Endpoint : GET /api/contacts

Request Header :
- X-API-TOKEN : Token (Mandatory)

Query Param :
- name : String, contact first or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- paging : Integer, start from 0, default 0
- size : Integer, default 10

Response Body (Success) :
```json
{
    "data" : [
        {
            "id" : "random string",
            "firstName" : "Lenando Respati",
            "lastName" : "Saldinbuana",
            "email" : "lenando.test@gmail.com",
            "phone" : "081234567654"
        },
    ],
    "paging" : {
        "currentPage" : 0,
        "totalPage" : 10,
        "size" : 10
    }
}
```

Response Body (Failed) :

## Remove Contact

Endpoint : DELETE /api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
    "data" : "OK",
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "contact is not found",
    "status" : "failed"
}
```