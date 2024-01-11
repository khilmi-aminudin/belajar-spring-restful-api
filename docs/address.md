# Address API Spec

## Create Address API

Endpoint : POST /api/contacts/{contactId}/addresses

Headers :
- X-API-TOKEN : token (mandatory)

Request Body :

```json
{
  "street" : "Jalan apa",
  "city" : "Kota apa",
  "province" : "Provinsi apa",
  "country" : "Negara apa",
  "postalCode" : "Kode pos"
}
```

Response Body Success :

```json
{
  "data" : {
    "id" : "random-string",
    "street" : "Jalan apa",
    "city" : "Kota apa",
    "province" : "Provinsi apa",
    "country" : "Negara apa",
    "postalCode" : "Kode pos"
  }
}
```

Response Body Error :

```json
{
  "errors" : "Country is required" 
}
```

## Update Address API

Endpoint : PUT /api/contacts/{contactId}/addresses/{addressId}

Headers :
- X-API-TOKEN : token (mandatory)

Request Body :

```json
{
  "street" : "Jalan apa",
  "city" : "Kota apa",
  "province" : "Provinsi apa",
  "country" : "Negara apa",
  "postalCode" : "Kode pos"
}
```

Response Body Success :

```json
{
  "data" : {
    "id" : 1,
    "street" : "Jalan apa",
    "city" : "Kota apa",
    "province" : "Provinsi apa",
    "country" : "Negara apa",
    "postalCode" : "Kode pos"
  }
}
```

Response Body Error :

```json
{
  "errors" : "Address is not found"
}
```

## Get Address API

Endpoint : GET /api/contacts/{contactId}/addresses/{addressId}

Headers :
- X-API-TOKEN : token (mandatory)

Response Body Success :

```json
{
  "data" : {
    "id" : 1,
    "street" : "Jalan apa",
    "city" : "Kota apa",
    "province" : "Provinsi apa",
    "country" : "Negara apa",
    "postalCode" : "Kode pos"
  }
}
```

Response Body Error :

```json
{
  "errors" : "contact is not found"
}
```

## List Addresses API

Endpoint : GET /api/contacts/{contactId}/addresses

Headers :
- X-API-TOKEN : token (mandatory)

Response Body Success :

```json 
{
  "data" : [
    {
      "id" : 1,
      "street" : "Jalan apa",
      "city" : "Kota apa",
      "province" : "Provinsi apa",
      "country" : "Negara apa",
      "postalCode" : "Kode pos"
    },
    {
      "id" : 1,
      "street" : "Jalan apa",
      "city" : "Kota apa",
      "province" : "Provinsi apa",
      "country" : "Negara apa",
      "postal_code" : "Kode pos"
    }
  ]
}
```

Response Body Error :

```json
{
  "errors" : "contact is not found"
}
```

## Remove Address API

Endpoint : DELETE /api/contacts/{contactId}/addresses/{addressId}

Headers :
- X-API-TOKEN : token (mandatory)

Response Body Success :

```json
{
  "data" : "OK"
}
```

Response Body Error :

```json
{
  "errors" : "address is not found"
}
```