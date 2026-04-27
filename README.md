<p align="center">
  <img alt="ONEGO" src="https://user-images.githubusercontent.com/81090746/147493035-1f8652ad-0524-4785-a75e-d37da95846c7.png" />
</p>

# ONEGO

<img width="1927" height="816" alt="image" src="https://github.com/user-attachments/assets/e04bd2ca-cdb9-40ce-b495-834c35558ecb" />


<br />

ONEGO is an AI-assisted writing and blogging platform built for people who have difficulty starting, organizing, or finishing long-form writing. It supports drafting, publishing, searching, and managing blog-style posts, while AI features help with sentence completion, text summarization, and tag generation.

The application combines a Vue writing experience, a Spring Boot API, MongoDB persistence, AWS Cognito authentication, Google Cloud Storage media uploads, and a Flask AI service for Korean writing assistance.

[Visit ONEGO](https://www.onego.qzz.io)

> DEMO LOGIN:
> 
> Email: `test@example.com`<br />
> Password: `test1234`




## My Role

- Built front-end pages and reusable UI pieces with Vue.js, TypeScript, HTML, and CSS.
- Designed and implemented the header, sidebar, footer, search, search results, account settings, edit profile, my profile, change password, saved blogs, posted blogs, and blog writing pages.
- Added writing-page tools for font styling, content folder management, memos, tags, image upload, preview, and post editing.

## Screenshots/Demo

<table>
  <tr>
    <td><img alt="onego_main" src="https://github.com/user-attachments/assets/f614edfd-a9ac-4b7e-8cf0-2e28b24b943d" /></td>
    <td><img alt="onego_bloglist" src="https://github.com/user-attachments/assets/c6156873-32aa-44b2-88e2-b16d6483d28f" />
</td>
    <td><img alt="onego_blogpost" src="https://github.com/user-attachments/assets/ca6911a2-3d50-45d8-9b81-ab276925e6a5" />
</td>
    <td><img alt="onego_login" src="https://github.com/user-attachments/assets/14691f8a-e837-4120-8739-7f70e5f8b372" />
</td>
  </tr>
  <tr>
    <td>Main page</td>
    <td>Blogs list</td>
    <td>Blog post</td>
    <td>Search blogs</td>
  </tr>
</table>

<table>
  <tr>
    <td><img alt="onego_login" src="https://github.com/user-attachments/assets/b42e9b16-61ab-4285-9881-cce9ba2aef08" />
</td>
    <td><img alt="onego_profile" src="https://github.com/user-attachments/assets/ad3794f2-8b9c-4284-a3d5-b7c2e20faf90" />
</td>
    <td><img alt="onego_writer" src="https://github.com/user-attachments/assets/dd996fd4-7234-4a34-bc24-e98fbed40f84" />
</td>
    <td><img alt="onego_writer_dark" src="https://github.com/user-attachments/assets/dab14faa-b02a-451e-9383-20423ed0ae5c" />
</td>
  </tr>
  <tr>
    <td>Login</td>
    <td>Profile</td>
    <td>Writer</td>
    <td>Writer (Dark)</td>
  </tr>
</table>

<table>
  <tr>
    <td>
      <img alt="onego_auto_tab" src="https://github.com/user-attachments/assets/2136b247-35c2-4021-90c3-f4a07886ca4b" />
    </td>
    <td>
      <img alt="onego_auto_click" src="https://github.com/user-attachments/assets/6991fd66-b7f0-43a5-9cd1-f3f005795fbe" />
    </td>
  </tr>
</table>


## Notable Features

### AI-Powered Writing

With one action, the AI service can generate:

- Multiple sentence continuation suggestions when the writer is stuck.
- Tags based on the current post content.
- Subtitles by summarizing the full draft.

### User-Friendly Writing Tool


- Dark mode to reduce eye strain: [source](./frontend/src/components/buttons/write/DarkModeSwitch.vue), [demo](https://youtu.be/oDfjhOdMj88?t=166).
- Tooltips for writing controls and navigation buttons: [demo](https://youtu.be/oDfjhOdMj88?t=48).
- Nested content management for organizing long posts: [source](./frontend/src/components/layout/write/Treeview.vue), [add content demo](https://youtu.be/oDfjhOdMj88?t=20), [edit/delete demo](https://youtu.be/oDfjhOdMj88?t=33).
- Memo panel for tracking story notes and ideas: [source](./frontend/src/components/layout/write/Memo.vue), [add memo demo](https://youtu.be/oDfjhOdMj88?t=100), [drag-and-drop demo](https://youtu.be/oDfjhOdMj88?t=114).
- Text editor with font size, font style, and simple formatting controls: [source](./frontend/src/views/Write.vue).
- Tag entry and AI tag generation: [source](./frontend/src/components/layout/write/Tag.vue), [demo](https://youtu.be/oDfjhOdMj88?t=131).
- Post title image upload: [source](./frontend/src/components/layout/write/Tag.vue), [demo](https://youtu.be/oDfjhOdMj88?t=151).
- Blog preview before publishing: [source](./frontend/src/views/PreviewModal.vue), [demo](https://youtu.be/oDfjhOdMj88?t=157).

### Platform Features

- Public article browsing, search, article detail pages, comments, likes, and scraps.
- AWS Cognito sign up, login, password reset, and authenticated user routes.
- User profiles with profile image upload, followers, followings, and personal article lists.
- Rich writing workspace with Tiptap/Vuetify editing, nested writing sections, memos, tags, draft saves, and post editing.
- AI writing support through Flask endpoints for sentence completion, subtitle summarization, and tag generation.
- Docker Compose setup for local builds, production builds, Nginx routing, MongoDB seeding, and service deployment.

## Project Links

- [Project summary](https://verdant-colt-ad5.notion.site/Encore-Playdata-AI-Writing-Web-Development-dda95b301f224c15be4bc4afc91c7417)

## Architecture

<img width="1672" height="941" alt="onego_structure" src="https://github.com/user-attachments/assets/3f778b6b-4641-4d7e-bff1-b0c0ebc71c52" />


> [!NOTE]
> Note that the original project used AWS EC2 and AWS S3 


## Tech Stack

**Frontend**

![Vue.js](https://img.shields.io/badge/Vue.js_2-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Vue Router](https://img.shields.io/badge/Vue_Router-4FC08D?style=for-the-badge&logo=vuedotjs&logoColor=white)
![Vuex](https://img.shields.io/badge/Vuex-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D)
![Vuetify](https://img.shields.io/badge/Vuetify-1867C0?style=for-the-badge&logo=vuetify&logoColor=white)
![BootstrapVue](https://img.shields.io/badge/BootstrapVue-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![Tiptap](https://img.shields.io/badge/Tiptap-0D0D0D?style=for-the-badge&logo=tiptap&logoColor=white)

**Backend**

![Java](https://img.shields.io/badge/Java_11-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_2.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

**AI Service**

![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Flask](https://img.shields.io/badge/Flask-000000?style=for-the-badge&logo=flask&logoColor=white)
![KoNLPy](https://img.shields.io/badge/KoNLPy-555555?style=for-the-badge)
![TextRank](https://img.shields.io/badge/TextRank-555555?style=for-the-badge)
![Hugging Face](https://img.shields.io/badge/Hugging_Face-FFD21E?style=for-the-badge&logo=huggingface&logoColor=black)

**Cloud & Infrastructure**

![AWS Cognito](https://img.shields.io/badge/AWS_Cognito-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![AWS Amplify](https://img.shields.io/badge/AWS_Amplify-FF9900?style=for-the-badge&logo=awsamplify&logoColor=white)
![Compute Engine](https://img.shields.io/badge/Compute_Engine-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white)
![Google Cloud Storage](https://img.shields.io/badge/Google_Cloud_Storage-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white)
![Artifact Registry](https://img.shields.io/badge/Artifact_Registry-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker_Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)

## Project Structure

```txt
frontend/       Vue 2 client app, routes, views, components, AWS Amplify config
backend/        Spring Boot API, controllers, services, repositories, MongoDB models
ai/             Flask AI API for completion, summarization, and tag extraction
ai_train/       Legacy/experimental AI training utilities
nginx/          Reverse proxy config for frontend, backend, and AI services
scripts/        MongoDB seed data
docker-compose*.yml
Dockerrun.aws.json
```

## Environment

Create a local environment file from the example:

```bash
cp .env.example .env
```

The main variables are:

```txt
MONGO_HOST, MONGO_PORT, MONGO_AUTH_DB, MONGO_DB, MONGO_USERNAME, MONGO_PASSWORD
GCP_STORAGE_BUCKET
COGNITO_ISSUER_URI
HUGGINGFACE_API_KEY, HUGGINGFACE_MODEL
VUE_APP_AWS_REGION
VUE_APP_COGNITO_IDENTITY_POOL_ID
VUE_APP_COGNITO_USER_POOL_ID
VUE_APP_COGNITO_APP_CLIENT_ID
```

For the local Docker build, the backend mounts Google Application Default Credentials from:

```txt
~/.config/gcloud/application_default_credentials.json
```

Run `gcloud auth application-default login` first if that file does not exist.

## Run Locally With Docker

Build and run the full local stack:

```bash
docker compose -f docker-compose.local.build.yml up --build
```

Open:

```txt
http://localhost:8080
```

The local compose file starts MongoDB, seeds sample data from `scripts/seed-mongo.js`, builds the frontend/backend/AI images, and routes requests through Nginx.

## Run Services Manually

Frontend:

```bash
cd frontend
npm install
npm run serve
```

Backend:

```bash
cd backend
./mvnw spring-boot:run
```

AI service:

```bash
cd ai
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
python start_flask.py
```

When running services manually, make sure `/api` and `/api/ai` requests are proxied to the backend and AI service. The Docker/Nginx setup already handles this routing.

## Useful Commands

Frontend:

```bash
cd frontend
npm run lint
npm run build
```

Backend:

```bash
cd backend
./mvnw test
./mvnw package
```

Production image build:

```bash
docker compose -f docker-compose.prod.build.yml build
```

Production run with prebuilt images:

```bash
docker compose -f docker-compose.prod.yml up -d
```

Seed production MongoDB:

```bash
docker compose -f docker-compose.prod.yml --profile seed run --rm seed
```

## API Notes

The backend exposes board, user, comment, follow, scrap, like, and temporary draft endpoints behind `/api`.

The AI service is exposed behind `/api/ai`:

```txt
POST /api/ai/complete     form-data: text=<sentence seed>
POST /api/ai/summarizer   JSON: {"contents": ["..."]}
POST /api/ai/tagger       JSON: {"contents": ["..."]}
```

Swagger metadata is configured as `Onego API` in the Spring Boot backend.

## Deployment

`nginx/default.conf` is configured for `onego.qzz.io` and `www.onego.qzz.io`, redirects HTTP to HTTPS, serves Let's Encrypt ACME challenges, and proxies:

```txt
/       -> frontend:3000
/api    -> backend:8080
/api/ai -> ai:5000
```

`Dockerrun.aws.json` and the production Compose files are included for container-based deployment workflows.
