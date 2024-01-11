# Contact API Spec

## Create Contact API

Endpoint : POST /api/contacts

Headers :
- X-API-TOKEN : token (mandatory)

Request Body :

```json
{
  "firstName" : "Eko",
  "lastName" : "Khannedy",
  "email" : "eko@pzn.com",
  "phone" : "32423423434"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random string",
    "firstName" : "Eko",
    "lastName" : "Khannedy",
    "email" : "eko@pzn.com",
    "phone" : "32423423434"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Email format is not valid "
}
```

## Update Contact API

Endpoint : PUT /api/contacts/{idC0ntact}

Headers :
- X-API-TOKEN : token (mandatory)

Request Body :

```json
{
  "firstName" : "Eko",
  "lastName" : "Khannedy",
  "email" : "eko@pzn.com",
  "phone" : "32423423434"
}
```

Response Body Success :

```json
{
  "data" : {
    "id" : 1,
    "firstName" : "Eko",
    "lastName" : "Khannedy",
    "email" : "eko@pzn.com",
    "phone" : "32423423434"
  }
}
```

Response Body Error :

```json
{
  "errors" : "Email is not valid format"
}
```

## Get Contact API

Endpoint : GET /api/contacts/{idContact}

Headers :
- X-API-TOKEN : token (mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : 1,
    "firstName" : "Eko",
    "lastName" : "Khannedy",
    "email" : "eko@pzn.com",
    "phone" : "32423423434"
  }
}
```

Response Body (Failed, 404)" :

```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact API

Endpoint : GET /api/contacts

Headers :
- X-API-TOKEN : token (mandatory)

Query params :
- **name** : Search by first_name or last_name, using like, optional
- **email** : Search by email using like, optional
- **phone** : Search by phone using like, optional
- **page** : number of page, default 1
- **size** : size per page, default 10

Response Body Success :

```json
{
  "data" : [
    {
      "id" : 1,
      "firstName" : "Eko",
      "lastName" : "Khannedy",
      "email" : "eko@pzn.com",
      "phone" : "32423423434"
    },
    {
      "id" : 2,
      "firstName" : "Eko",
      "lastName" : "Khannedy",
      "email" : "eko@pzn.com",
      "phone" : "32423423434"
    }
  ],
  "paging" : {
    "currentPage" : 1,
    "totalPage" : 3,
    "totalItem" : 30
  }
}
```

Response Body Error :
```json
{
  "errors": "unauthorize"
}
```

## Remove Contact API

Endpoint : DELETE /api/contacts/{idContact}

Headers :
- Authorization : token

Response Body Success :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Contact is not found"
}
```