# Project: disney-challenge
# CHALLENGE BACKEND - Java Spring Boot (API)

This was a challenge by [Alkemy](https://www.alkemy.org/) solved by [Tomás Sánchez](https://github.com/tomasanchez)

Disny-Challenge consist of a REST API using a fictional set of Characters and Content (Movies, Series, etc). It provides endpoints for retrieving characters, movies, filter them, and make CRUD operations.

As you need a bearer token for using these endpoints, also provides an authentication endpoint for registering and log-in.

## Getting Started

To start sending request, make sure the server is running. Then you can either register, or use the dummy account to log in.

You *must* log in to obtain a bearer token. Once obtained, set up the `Authorization` with the bearer `token`
# 📁 Collection: auth 


## End-point: Sign Up
For signing-up you will have to provide a valid email and a password.
Don't worry, your password will be encrypted.
### Method: POST
>```
>{{baseUrl}}/auth/register
>```
### Body (**raw**)

```json
{
    "mail": "notARealMail_butValid@not.real"
}
```

### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|


### Response: 202
```json
{
    "id": 2,
    "mail": "notARealMail_butValid@not.real",
    "password": "$2a$10$ruVHXyMUht/juhvak1VvM.TC0pU05NBXBBtYdwH0zsdMw4hd9bTD.",
    "isVerified": false,
    "roles": [
        "USER"
    ]
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Sign In
### Method: POST
>```
>{{baseUrl}}/auth/login
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization||


### Response: 200
```json
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhbGtlbXkub3JnIiwicm9sZXMiOlsiQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvbG9naW4iLCJleHAiOjE2NDEzMzEzMjN9.eAR_Vbm8AIuPjBSfoPuUvbneGtfga2nHWT2sHildrG4",
    "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhbGtlbXkub3JnIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvbG9naW4iLCJleHAiOjE2NDEzMzMxMjN9.r6-0zEPqjuYgBoa_m14EInPQ-tX47_8vPMf875my62o"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: characters 


## End-point: Characters Set
### Method: GET
>```
>{{baseUrl}}/characters
>```
### Response: 200
```json
[
    {
        "image": "",
        "name": "Tony",
        "id": 1
    },
    {
        "image": "",
        "name": "Strange",
        "id": 2
    },
    {
        "image": "",
        "name": "Peter",
        "id": 3
    },
    {
        "image": "",
        "name": "Thor",
        "id": 4
    },
    {
        "image": "",
        "name": "Vision",
        "id": 5
    },
    {
        "image": "",
        "name": "Ultron",
        "id": 6
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Character Detail
### Method: GET
>```
>{{baseUrl}}/characters/1
>```
### Response: 200
```json
{
    "id": 1,
    "image": "",
    "name": "Tony",
    "age": 40,
    "weight": 0,
    "story": null,
    "appearances": [
        {
            "id": 1,
            "image": "",
            "title": "Tony's Solo Movie",
            "releaseDate": "2001-05-04",
            "rating": 2.9277146,
            "genre": {
                "id": 1,
                "name": "Action",
                "image": ""
            }
        }
    ]
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Character
### Method: POST
>```
>http://localhost:8080/characters
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name": "Thanos",
    "appearances": [1, 2, 3]
}
```

### Response: 201
```json
{
    "id": 7,
    "image": null,
    "name": "Thanos",
    "age": 0,
    "weight": 0,
    "story": null,
    "appearances": [
        {
            "id": 1,
            "image": "",
            "title": "Tony's Solo Movie",
            "releaseDate": "2001-05-04",
            "rating": 2.9277146,
            "genre": {
                "id": 1,
                "name": "Action",
                "image": ""
            }
        },
        {
            "id": 3,
            "image": "",
            "title": "Peter's Solo Movie",
            "releaseDate": "2016-12-06",
            "rating": 1.0210049,
            "genre": 1
        },
        {
            "id": 2,
            "image": "",
            "title": "Strange's Solo Movie",
            "releaseDate": "2008-01-28",
            "rating": 0.8304292,
            "genre": 1
        }
    ]
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Character
### Method: PUT
>```
>http://localhost:8080/characters/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name":"Tony",
    "age": 40
}
```

### Response: 200
```json
{
    "id": 1,
    "image": "",
    "name": "Anthony",
    "age": 40,
    "weight": 0,
    "story": null,
    "appearances": []
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Character
### Method: DELETE
>```
>http://localhost:8080/characters/7
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Body (**raw**)

```json
{
    "name":"Tony",
    "age": 40
}
```

### Response: 404
```json
{
    "error": "No Fictional character found with id: 0"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: movies 


## End-point: Movies Set
### Method: GET
>```
>{{baseUrl}}/movies?order=DESC
>```
### Query Params

|Param|value|
|---|---|
|order|DESC|


### Response: 200
```json
[
    {
        "image": "",
        "releaseDate": "2011-07-15",
        "id": 1,
        "title": "Tony's Solo Movie"
    },
    {
        "image": "",
        "releaseDate": "2019-01-02",
        "id": 2,
        "title": "Strange's Solo Movie"
    },
    {
        "image": "",
        "releaseDate": "2020-03-06",
        "id": 3,
        "title": "Peter's Solo Movie"
    },
    {
        "image": "",
        "releaseDate": "2004-06-10",
        "id": 4,
        "title": "Thor's Solo Movie"
    },
    {
        "image": "",
        "releaseDate": "2013-10-26",
        "id": 5,
        "title": "Vision's Solo Movie"
    },
    {
        "image": "",
        "releaseDate": "2018-10-29",
        "id": 6,
        "title": "Ultron's Solo Movie"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Movie Details
### Method: GET
>```
>{{baseUrl}}/movies/3
>```
### Response: 401
```json
{"error_message":"The Token has expired on Tue Jan 04 18:22:03 ART 2022."}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Movie
### Method: POST
>```
>{{baseUrl}}/movies
>```
### Body (**raw**)

```json
{
    "title": "Avengers",
    "releaseDate": "2019-01-01",
    "genre": 1,
    "rating": 4.5,
    "characters": [1, 2, 3, 4, 5]
}
```

### Response: 201
```json
{
    "id": 10,
    "image": "",
    "title": "Avengers",
    "releaseDate": "2019-01-01",
    "rating": 4.5,
    "genre": {
        "id": 1,
        "name": "Action",
        "image": ""
    }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Movie
### Method: PUT
>```
>{{baseUrl}}/movies/3
>```
### Body (**raw**)

```json
{
    "title": "Spider Man: Home Coming",
    "releaseDate": "2015-10-10",
    "characters": [1]
}
```

### Response: 200
```json
{
    "id": 3,
    "image": "",
    "title": "Spider Man: Home Coming",
    "releaseDate": "2015-10-10",
    "rating": 0.14227271,
    "genre": {
        "id": 1,
        "name": "Action",
        "image": ""
    }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Movie
### Method: DELETE
>```
>{{baseUrl}}/movies/97
>```
### Response: 404
```json
{
    "error": "No Appearance found with id: 97"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
