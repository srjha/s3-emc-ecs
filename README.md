# s3-emc-ecs
s3 file download via rest

To build use 
```
mvnw clean install
```

Once build is successful, please find jar with embedded tomcat in target folder.
Before running the jar appropriate values should be set as environment variables.

```
DATA_BUCKET_NAME=help-docs-botw
AWS_ACCESS_KEY_ID=AKIAJFHTGVJLSDSYLWAPA
AWS_SECRET_ACCESS_KEY=GyAVRQJnhjjJHHHUf7xTGrxdID/pgg8t
AWS_REGION=us-east-1
```

once env vars are set, this jar can be run by using below command.
```
java -jar s3-emc-ecs-0.0.1-SNAPSHOT.jar
```
