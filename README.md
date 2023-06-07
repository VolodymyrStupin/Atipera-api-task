## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Endpoints](#endpoints)
* [First request](#first-request)
* [Second request](#second-request)
* [Third request](#third-request)

## General info
This project is simple example gitHub rest api.

## Technologies
Project is created with:
* Java 17
* Spring Boot 3.1
* JUnit
* Mockito 

## Setup
To run this project, first install it locally using gh:

```
$ gh repo clone VolodymyrStupin/Car-serwice-and-wash-with-angular-test
```

next navigate on the project files to com.stupin do a right-click on AtiperaApiTaskApplication.java and choose "Run 'AtiperaApiTaskApplication'" 
## Endpoints

### There is only endpoint http://localhost:8080/repositories/{userName} where userName is login to gitHub account


### First request

```
$ /repositories/{username}, with header “Accept: application/json”
```

### Response with status 200

```
[
    {
        "name": "Car-serwice-and-wash",
        "owner": {
            "login": "VolodymyrStupin"
        },
        "branches": [
            {
                "name": "main",
                "commit": {
                    "sha": "c46b38b2e611170a02ded8d61e4d7810517140a8"
                }
            },
            {
                "name": "new-branch",
                "commit": {
                    "sha": "060063813cb2c9306a831080bd3c7c831a567856"
                }
            }
        ],
        "fork": false
    },
    {
        "name": "Car-serwice-and-wash-with-angular-test",
        "owner": {
            "login": "VolodymyrStupin"
        },
        "branches": [
            {
                "name": "main",
                "commit": {
                    "sha": "d06320e13b23defa3ddb4f18e7c4bed8fa22b0ae"
                }
            }
        ],
        "fork": false
    },
    {
        "name": "jenkins",
        "owner": {
            "login": "VolodymyrStupin"
        },
        "branches": [
            {
                "name": "main",
                "commit": {
                    "sha": "52cb492a1664478d6d55c4d99ad3b13c2c2db60e"
                }
            }
        ],
        "fork": false
    },
    {
        "name": "Library-with-spring-database-migration",
        "owner": {
            "login": "VolodymyrStupin"
        },
        "branches": [
            {
                "name": "main",
                "commit": {
                    "sha": "39b02b1086129facb2e344ca70112ddfc9e47074"
                }
            }
        ],
        "fork": false
    },
    {
        "name": "Statistics_centre_with_liquibase_migration_and_hibernate",
        "owner": {
            "login": "VolodymyrStupin"
        },
        "branches": [
            {
                "name": "main",
                "commit": {
                    "sha": "5b016d0955656c053fd33a9b166659df4c8df585"
                }
            }
        ],
        "fork": false
    }
]
```
### Second request
```
$ /repositories/{username}, with given not existing github user”
```
### Response with status 404
```
{
    "statusCode": 404,
    "errorMessage": "User with given username not found"
}
```

### Third request
```
$ /repositories/{username}, given header “Accept: application/xml””
```
### Response with status 406
```
{
    "statusCode": 406,
    "errorMessage": "Unsupported 'Media type'. Must accept 'application/json'"
}
```
