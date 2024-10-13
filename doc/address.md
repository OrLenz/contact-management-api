# Address API Spec

## Create Address

Endpoint : PUT /api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
    "street" : "Jalan Apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalCode" : "12345"
}
```

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "street" : "Jalan Apa",
        "city" : "Kota",
        "province" : "Provinsi",
        "country" : "Negara",
        "postalCode" : "12345"
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

## Update Address

Endpoint : POST /api/contacts/{idContact}/addresses/{idAdress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
    "street" : "Jalan Apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalCode" : "12345"
}
```

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "street" : "Jalan Apa",
        "city" : "Kota",
        "province" : "Provinsi",
        "country" : "Negara",
        "postalCode" : "12345"
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

## Get Address

Endpoint : GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
    "data" : {
        "id" : "random string",
        "street" : "Jalan Apa",
        "city" : "Kota",
        "province" : "Provinsi",
        "country" : "Negara",
        "postalCode" : "12345"
    },
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "address is not found",
    "status" : "failed"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses/{idAddress}

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
    "message" : "Address is not found",
    "status" : "failed"
}
```

## List Address

Endpoint : GET /api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
    "data" : [
        {
            "id" : "random string",
            "street" : "Jalan Apa",
            "city" : "Kota",
            "province" : "Provinsi",
            "country" : "Negara",
            "postalCode" : "12345"
        },
    ]
    "status" : "success"
}
```

Response Body (Failed) :
```json
{
    "message" : "address is not found",
    "status" : "failed"
}
```