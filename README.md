# Description

The main goal of this simple project is practicing some non-trivial
implementations connecting them to AWS S3.

In order to run this app, you must have Docker installed on your machine.

You can build this app with the following command: 
```
docker build \
  --build-arg AWS_ACCESS_KEY_ID \
  --build-arg AWS_SECRET_ACCESS_KEY \
  -t qrcode-generator:1.0 .
```

And run through this: 
```
docker run --env-file .env -p 8080:8080 qrcode-generator:1.0
```