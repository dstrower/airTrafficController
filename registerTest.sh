curl -X POST -H "Content-Type: application/json" http://localhost:10000/enqueue/register -d '{ "mail": "test@test.de", "password": "password", "lastName": "lastName", "name": "name", "address": "somewhere" }'