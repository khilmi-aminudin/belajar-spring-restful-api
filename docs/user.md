# User API Spec

## Register User API

Endpoint :  POST /api/users

Request Body :

```json
{
  "username" : "pzn",
  "password" : "rahasia",
  "name" : "Programmer Zaman Now"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username must not blank"
}
```

## Login User API

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "pzn",
  "password" : "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "unique-token",
    "expiredAt": 3534657567 // milisecond
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Username or password wrong"
}
```

## Update User API

Endpoint : PATCH /api/users/current

Headers :
- X-API-TOKEN : token (mandatory)

Request Body :

```json
{
  "name" : "Programmer Zaman Now Lagi", // if only want to update name
  "password" : "new password" // if only want to update name
}
```

Response Body Success :

```json
{
  "data" : {
    "username" : "pzn",
    "name" : "Programmer Zaman Now Lagi"
  }
}
```

Response Body Error :

```json
{
  "errors" : "Name length max 100"
}
```

## Get User API

Endpoint : GET /api/users/current

Headers :
- X-API-TOKEN : token (mandatory)

Response Body (Success):

```json
{
  "data" : {
    "username" : "pzn",
    "name" : "Programmer Zaman Now"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User API

Endpoint : DELETE /api/users/logout

Headers :
- Authorization : token (mandatory)

Response Body Success :

```json
{
  "data" : "OK"
}
```

Response Body Error :

```json
{
  "errors" : "Unauthorized"
}
```