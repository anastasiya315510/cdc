# CDC project

### Kafka stream with Apache Spark


1) Input: Kafka topic named "my-cdc-input-topic".

2) The records come as the json attached.

3) Spark will consume the records from Kafka, process it and write back to Kafka topic named "my-cdc-output-topic"

4) The process method receives the Kafka record as it input, and output it as follows:

       * If the event occurred in the source DB was an Update or Insert => output the new row value ("data" field)

       * If the event occurred in the source DB was a Delete => output a tombstone Kafka record (a record with null value).

5) Produce the message to Kafka.

6) Kafka key is given.

```
{
"pk": the primary key of this row,
"data": For insert and update - a json containing the row being inserted or updating the existing row in the source database. For delete - null.
"beforeData": For update and delete - a json containing the row before it was updated or deleted in the source database. For insert - null.
"headers": {
"operation": A string indicating the operation that happend in the source database. Can be "INSERT", "UPDATE" or "DELETE"
"timestamp": The event timestamp,
"streamPosition": A unique string indicating the position of the transaction in the database.
}
```
To make a request, please:

1. Open your postman
2. Create a new Post request
3. add link http://localhost:8082/publish (this is default port, but, you can change in application properties file server.port)
4. In the body section choose JSON in the list and add JSON. For example, can be JSON:
```
{
"pk":12,
"data":"2020-01-30T21:07:07.572426",
"beforeData":null,
"headers":{
"operation": "UPDATE",
"timestamp": "2021-08-31T21:07:07.572426",
"streamPosition":"17"
}
}
```
