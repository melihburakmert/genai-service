# Gen-AI Service

A microservice for interacting with the Gen-AI models through [Spring AI](https://docs.spring.io/spring-ai/reference/). \
Currently only the Vertex AI models from Google are supported.

## Tech Stack

- Java + Spring Boot + Spring AI
- Vertex AI Gemini
- Text generation, Text embeddings, Multimodal processing
- Google Cloud PubSub
- Redis
- Docker
- Maven
- Modulith Architecture
- Event Driven Architecture

## Endpoints

- POST 
`/genai/describe-image` \
`Content-Type: multipart/form-data` \
`X-Session-ID: <session-id>` \


- GET
`/genai/caption` \
`X-Session-ID: <session-id>` \

## Running in Docker locally:

- Build the image \
  `mvn jib:dockerBuild`
- Deploy the image to Docker \
  `docker-compose up -d genai-service`

### TODO:
- [ ] Use Redis for vector storage
- [ ] Add tests
- [ ] Add CI/CD
- [ ] Deploy on Kubernetes