# Part 2 MySQL Day 2
This example is a custom example the follows the Students data scenario. 


## Update 



#### Import the following

Post
```curl
curl --location 'http://localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "Luke",
  "lastName": "Matheis",
  "email": "luke.matheis@indainhills.edu",
  "eContact": "123-456-7890",
  "student_id": 12345
}
'
```

Get
```curl
curl --location 'http://localhost:8080/adll'
```

PUT
```
curl --location --request PUT 'http://localhost:8080/update/2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "billy2.bob2@indainhills.edu",
  "eContact": "123-456-3423"
}
'
```

DEL
```
curl --location --request DELETE 'http://localhost:8080/delete/20'
```

Get by Last name
``
###
GET http://localhost:8080/findByName/Matheis
``
