# Gen-AI Service

A microservice for interacting with the Gen-AI models through [Spring AI](https://docs.spring.io/spring-ai/reference/). \
Currently only the Vertex AI models from Google are supported.

## Tech Stack

- Java + Spring Boot + Spring AI
- Vertex AI Gemini
- Text generation, Text embeddings, Multimodal processing
- Google Cloud PubSub
- Docker
- Maven
- Modulith Architecture
- Event Driven Architecture

## Endpoints

- POST 
`/genai/describe-image` \
`Content-Type: multipart/form-data`

## Running in Docker locally:

- Build the image \
  `mvn jib:dockerBuild`
- Deploy the image to Docker \
  `docker-compose up -d genai-service`

### TODO:

- [ ] Implement caption generation
- [ ] Publish to PubSub topic 'lyrics'
- [ ] Add tests
- [ ] Add CI/CD
- [ ] Deploy on Kubernetes