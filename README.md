# Project Plan: Building a YouTube-like System
## Project Goal
To collaboratively design, implement, and deploy a high-level distributed system that enables users to:

- Upload video files.

- View/Stream uploaded videos efficiently.

- Search for videos based on metadata.

## High-Level Architecture Overview
![image](https://github.com/user-attachments/assets/0303219b-f947-497b-bf2a-559cc3c73c13)

https://youtu.be/tWVDg4pVQEk

## Project Milestones/Phases
Consider a phased/parallel approach to build incrementally:

### Milestone 1: Core Upload & Storage (MVP)
- Basic user authentication.
- Video upload to blob storage (raw).
- Basic metadata storage.

### Milestone 2: Basic Viewing & Processing
- Trigger for async processing.
- Video processing/transcoding to a single format.
- Basic video playback from processed storage.

### Milestone 3: Search & Discovery
- Implementing search API and basic frontend search.
- Populating search index with metadata.
  
### Milestone 4: User Management
- Implement User login signup to provide access to system.
  
### Milestone 5: Enhancements & Scalability
- CDN integration for processed videos.
- Load Balancers and API GAteways
- Caching integration (Redis) for thumbnails/metadata.

## Specific Tasks for Exploration and Execution
### 1. Video Upload Functionality 
Goal: Enable users to securely upload video files.
- Client-Side Upload Logic: Build a simple web interface (HTML/JS/React) to allow users to select and upload files, displaying progress.
- Backend Upload Service: Develop a microservice (e.g., in Python/Flask, Go, Node.js) that handles upload requests.
- Cloud Storage Integration: Implement logic to interact with chosen blob storage (e.g., AWS S3 SDK, Azure Blob Storage SDK, Google Cloud Storage client library).
- Upload Metadata: Upload the video's metadata in the database (e.g.video title, description, available resolutions, thumbnail URLs).
Exploration (Research & Design):
- Direct Upload vs. Proxy Upload: Investigate pros and cons of direct-to-storage upload (e.g., signed URLs for S3/GCS) versus proxying uploads through an API Gateway/service.
- Signed URL Generation (if direct upload): Implement endpoint to generate pre-signed URLs for client-side direct uploads.
- Large File Uploads: Research best practices for handling large file uploads (multipart uploads, resumable uploads).

### 2. Video viewing and processing
Goal: Convert uploaded videos into various formats and resolutions and Enable users to view videoes

- Video Playback Service: A microservice that provides video metadata and manifest URLs to the client.
- Client-Side Video Player: Integrate a chosen ABR-compatible video player into the web interface.
- Message Queue Integration: After successful upload to blob storage, publish a message to a queue (e.g., "video_uploaded" event) for asynchronous processing.
- Processing Service: Develop a microservice that consumes messages from the "video_uploaded" queue to generate different tags for better search.
- Transcoding Logic: Integrate FFmpeg (if self-managing) or call the chosen cloud transcoding API.

Exploration (Research & Design):
- CDN for Streaming: How CDNs optimize video delivery (edge caching, byte-range requests).
- Analytics: How to track video views and playback metrics.

### 3. Video Search & Discovery
Goal: Allow users to find videos based on keywords and metadata.
- Client-Side Web Interface: Build a search bar and display search results.
- Search Service: A microservice that exposes a search API, querying the search index.
- Metadata Integration: Ensure the video processing service pushes relevant metadata to the search index.
  
Exploration (Research & Design):
-Search Technologies: Research full-text search engines (Elasticsearch, Apache Solr) or managed cloud search services (AWS OpenSearch, Azure Cognitive Search, Google Cloud Search).

### 4. User Management (Basic)
Goal: Allow users to register, log in, and manage their uploaded videos.
- User Management Service: A microservice for user authentication and profile data.
- Database Integration: Connect to a database (e.g., Cloud SQL, Azure SQL Database).
- Frontend Integration: Build login/registration forms and integrate with the user service.

Exploration (Research & Design):
- Authentication/Authorization: Research OAuth 2.0, JWTs, or managed identity services (AWS Cognito, Azure AD B2C, Firebase Auth).

### 5. Enhancement and Scaling
Goal: How to support scaling and improve performance.
- Explore CDN Configuration: Configure the CDN to serve processed video files from your blob storage.
- API Gateway Setup: Configure routing rules to direct traffic to appropriate microservices.
- Load Balancer Setup: Place a load balancer in front of the API Gateway or directly in front of public-facing services.
- Managed Redis Instance: Provision a managed Redis instance (Memorystore, Azure Cache for Redis, ElastiCache)and integrates into microservices that benefit from caching.

## Extras
- Deployment of services using Kubernates, Docker etc.
- Log Aggregation: How to collect logs from all services into a central location.









