# Spring Boot + Angular + AWS Servcies application

### Prerequisites

Java: 17

MySQL: 8.32

Angular: 17.3.0

# Summary

The main application point is uploading image to AWS S3 Bucket, after using AWS Rekognition Service to recognize object labels which appeared at image, and save metadata in DB, to use it in next requests from front-end

# Home display page

<img width="1512" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/b1792de6-c57c-4935-8310-d6c36b5cb05d">

# Uploading image to S3 from front-end

<img width="1512" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/cb5fef59-890c-4c0c-8114-61b8a5b7dd42">

# Search by label 'dolphin'

<img width="1512" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/9f9f7603-bb30-41df-a48c-9f701f4a6226">

# Search by label 'animal'

<img width="1512" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/c7b0624d-8c68-41f6-a265-372fe12cd30a">

# Search by label 'nothing' which does not exist in DB

<img width="1512" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/c9539f34-8d0a-40bf-91de-f44e3670ba05">

# MySQL M:M relation results

<img width="696" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/2bea8128-df38-4a4e-b0e9-40c7b535a558">

# S3 bucket demonstration

<img width="1115" alt="image" src="https://github.com/spikyzero/aws-upload-image-app/assets/57000663/21c9293e-4c2b-4f5b-aa1d-feef3b750787">
