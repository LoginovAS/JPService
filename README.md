# JPService

JP Service helps to decide of sending Christmas presents to children depending on them behavior.

### Present request API
Person can send POST-request to the system:
e.g: http://localhost:8080/api/request

with his name and present type.
The information should be provided in JSON:
e.g
{
    "firstName": "Dmitry",
    "lastName": "Petrov",
    "presentType": "Car"
}

The system tries to find person by "firstName" and "lastName" and present type by "presentType" . In case of person or present type were not found system returns 404 NOT_FOUND code.
If person found system checks his behavior. 
If behavior is bad system returns exception:
{
    "timestamp": "2020-09-17T06:51:43.832+0000",
    "status": 200,
    "error": "OK",
    "message": "Dmitry Petrov cannot receive the present because of bad behavior",
    "path": "/api/request"
}

If behavior is good system returns message:
e.g. "Dmitry Petrov received Car because of good behavior"

### Request production
There is PFSystem application used to produce addition presents. 
https://github.com/LoginovAS/PFSystem
It should be started for the supply scenario to work correctly.

When specified PresentType quantity is less then 3, the JPService calls PFSystem production API:
http://localhost:8190/api/produce
and sends JSON:
 {
     "presentType": "Car",
     "quantity": 5
 }
 
In its turn PFSystem calls JPService's supply API.

### Supply API
External system can add some number of specified presents.
 - System calls supply API with POST request:
 e.g.: http://localhost:8080/api/supply
 
 and sends JSON:
 e.g.:
 
 {
     "presentType": "Car",
     "quantity": 5
 }
 
 where "presentType" - type of presents which exists in JPService system.
 "quantity" - integer positive value.
 
 In case of success system will return 200 OK code with "Presents have been added" message.
 
 ### The future improvements:
  - Code should be covered by unit tests.
  - Communication between services should be async. Apply any queue broker like Kafka. 
  - Remove any hardcode like URL. Move them to param file.
  - Check again and rewrite hashCode/equals section because of some doubts about id field.
