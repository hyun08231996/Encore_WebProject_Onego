# Edit Order And Commit Plan

This file is based on the current working tree and the recent file modification timeline in this repo.

Notes:
- Order below is newest to older.
- I excluded obvious runtime artifacts such as `__pycache__` and crash dumps.
- The repo has many modified files; the commit plan below groups them into smaller, safer commits instead of one large commit.

## Recent Edit Order

### 1. Root environment and deployment config

Files:
- [/.env](/Users/peternam/Desktop/onego/.env)
- [/.env.example](/Users/peternam/Desktop/onego/.env.example)
- [docker-compose2.yml](/Users/peternam/Desktop/onego/docker-compose2.yml)
- [nginx/default.conf](/Users/peternam/Desktop/onego/nginx/default.conf)
- [/.gitignore](/Users/peternam/Desktop/onego/.gitignore)

Code touched:
- root env vars for Mongo, Cognito, GCS, Gemini
- Compose env wiring for backend and AI
- ADC mount path for backend
- nginx `client_max_body_size`

### 2. Write autocomplete UX

Files:
- [frontend/src/views/Write.vue](/Users/peternam/Desktop/onego/frontend/src/views/Write.vue)
- [frontend/src/components/layout/write/Autocom.vue](/Users/peternam/Desktop/onego/frontend/src/components/layout/write/Autocom.vue)
- [docs/write-autocomplete-instructions.md](/Users/peternam/Desktop/onego/docs/write-autocomplete-instructions.md)

Code touched:
- debounced autocomplete request flow
- inline ghost/shimmer suggestion UI
- `Tab` acceptance behavior
- clickable copyable autocomplete suggestion list
- tooltip and copied-state UI

### 3. Backend upload/runtime config

Files:
- [backend/src/main/resources/application.yml](/Users/peternam/Desktop/onego/backend/src/main/resources/application.yml)
- [backend/src/main/java/com/encore/backend/controller/BoardController.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/controller/BoardController.java)
- [backend/src/main/java/com/encore/backend/controller/UserController.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/controller/UserController.java)
- [backend/src/main/java/com/encore/backend/service/BoardService.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/service/BoardService.java)
- [backend/src/main/java/com/encore/backend/service/UserServiceImpl.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/service/UserServiceImpl.java)

Code touched:
- Spring multipart upload limits
- optional multipart file parts for user/board updates
- preserve existing image URL when no new file is sent
- upload board title image on update when a new file is provided

### 4. AI completion and AI runtime fixes

Files:
- [ai/libs/sentence_complete/complete.py](/Users/peternam/Desktop/onego/ai/libs/sentence_complete/complete.py)
- [ai/Dockerfile](/Users/peternam/Desktop/onego/ai/Dockerfile)

Code touched:
- replaced dead completion endpoint with Gemini
- moved Gemini key from query string to header
- candidate extraction and sentence cleanup logic
- Java runtime upgrade for KoNLPy/Komoran compatibility

### 5. Frontend Cognito env migration

Files:
- [frontend/.env.local](/Users/peternam/Desktop/onego/frontend/.env.local)
- [frontend/.env.example](/Users/peternam/Desktop/onego/frontend/.env.example)
- [frontend/src/aws-exports.ts](/Users/peternam/Desktop/onego/frontend/src/aws-exports.ts)
- [frontend/src/config/aws/aws-exports.js](/Users/peternam/Desktop/onego/frontend/src/config/aws/aws-exports.js)
- [frontend/src/config/aws/aws-exports.d.ts](/Users/peternam/Desktop/onego/frontend/src/config/aws/aws-exports.d.ts)

Code touched:
- moved frontend Cognito config to `VUE_APP_*`
- removed hardcoded pool/client values from runtime config

### 6. Backend GCS migration

Files:
- [backend/src/main/java/com/encore/backend/storage/StorageUploader.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/storage/StorageUploader.java)
- [backend/src/main/java/com/encore/backend/storage/GcsUploader.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/storage/GcsUploader.java)
- [backend/src/main/java/com/encore/backend/storage/GcsStorageConfig.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/storage/GcsStorageConfig.java)
- [backend/src/main/resources/application-gcp.yml](/Users/peternam/Desktop/onego/backend/src/main/resources/application-gcp.yml)
- [backend/pom.xml](/Users/peternam/Desktop/onego/backend/pom.xml)
- [backend/src/main/resources/application-aws.yml](/Users/peternam/Desktop/onego/backend/src/main/resources/application-aws.yml)
- [backend/src/main/resources/application-credentials.yml](/Users/peternam/Desktop/onego/backend/src/main/resources/application-credentials.yml)
- deleted: [backend/src/main/java/com/encore/backend/s3/S3Uploader.java](/Users/peternam/Desktop/onego/backend/src/main/java/com/encore/backend/s3/S3Uploader.java)

Code touched:
- introduced storage abstraction
- replaced AWS S3 uploader with Google Cloud Storage uploader
- switched backend config toward GCP bucket/env usage

### 7. Seed data and local Mongo workflow

Files:
- [scripts/seed-mongo.js](/Users/peternam/Desktop/onego/scripts/seed-mongo.js)
- [docker-compose2.yml](/Users/peternam/Desktop/onego/docker-compose2.yml)

Code touched:
- idempotent Mongo seed script
- Korean blog-style seed boards/users/temp boards
- Compose seed service and Mongo healthcheck workflow

### 8. Feed/article rendering changes

Files:
- [frontend/src/views/Article.vue](/Users/peternam/Desktop/onego/frontend/src/views/Article.vue)
- [frontend/src/views/Scrap.vue](/Users/peternam/Desktop/onego/frontend/src/views/Scrap.vue)
- [frontend/src/components/layout/Page.vue](/Users/peternam/Desktop/onego/frontend/src/components/layout/Page.vue)
- [frontend/src/components/layout/Post.vue](/Users/peternam/Desktop/onego/frontend/src/views/Post.vue)
- [frontend/src/views/PreviewModal.vue](/Users/peternam/Desktop/onego/frontend/src/views/PreviewModal.vue)
- [frontend/src/assets/css/SearchResult.css](/Users/peternam/Desktop/onego/frontend/src/assets/css/SearchResult.css)

Code touched:
- article card cover image display
- profile image enrichment from `/users/{email}`
- article list behavior tied to board data
- image opacity/build-output fixes

### 9. Write/editor related support files

Files:
- [frontend/src/store/modules/writeStore.ts](/Users/peternam/Desktop/onego/frontend/src/store/modules/writeStore.ts)
- [frontend/src/components/layout/write/Tag.vue](/Users/peternam/Desktop/onego/frontend/src/components/layout/write/Tag.vue)
- [frontend/src/components/layout/write/Treeview.vue](/Users/peternam/Desktop/onego/frontend/src/components/layout/write/Treeview.vue)
- [frontend/src/components/layout/write/Memo.vue](/Users/peternam/Desktop/onego/frontend/src/components/layout/write/Memo.vue)
- [frontend/src/assets/css/Write.css](/Users/peternam/Desktop/onego/frontend/src/assets/css/Write.css)
- [frontend/src/components/buttons/write/DarkModeSwitch.vue](/Users/peternam/Desktop/onego/frontend/src/components/buttons/write/DarkModeSwitch.vue)

Code touched:
- write-side UI state and image state
- treeview default-open behavior
- write-page CSS fixes and editor visuals

### 10. App/session/frontend shell changes

Files:
- [frontend/src/App.vue](/Users/peternam/Desktop/onego/frontend/src/App.vue)
- [frontend/src/store/index.ts](/Users/peternam/Desktop/onego/frontend/src/store/index.ts)
- [frontend/src/router/index.ts](/Users/peternam/Desktop/onego/frontend/src/router/index.ts)
- [frontend/src/main.ts](/Users/peternam/Desktop/onego/frontend/src/main.ts)

Code touched:
- startup auth/session restore
- app bootstrap behavior
- shared event bus usage

## Suggested Commit Sequence

These commit messages follow a common conventional commit style and keep each commit focused.

### Commit 1

Files:
- `frontend/src/views/Write.vue`
- `frontend/src/components/layout/write/Autocom.vue`
- `docs/write-autocomplete-instructions.md`

Message:

```text
feat(frontend): add debounced write autocomplete interactions

Implement inline sentence suggestions in the editor with Tab acceptance.
Make autocomplete suggestions clickable and copyable from the side panel.
```

### Commit 2

Files:
- `ai/libs/sentence_complete/complete.py`

Message:

```text
feat(ai): replace legacy completion endpoint with Gemini

Switch sentence completion to Gemini and improve candidate extraction.
Send the API key through headers instead of leaking it in request URLs.
```

### Commit 3

Files:
- `ai/Dockerfile`

Message:

```text
fix(ai): upgrade runtime for KoNLPy and Komoran

Move the AI image off Java 8 so summarizer and tagger can start correctly.
Set an explicit modern JAVA_HOME for the container runtime.
```

### Commit 4

Files:
- `backend/src/main/java/com/encore/backend/storage/*`
- `backend/pom.xml`
- `backend/src/main/resources/application-gcp.yml`
- `backend/src/main/resources/application-aws.yml`
- `backend/src/main/resources/application-credentials.yml`
- `backend/src/main/java/com/encore/backend/s3/S3Uploader.java` (deletion)
- `backend/src/main/java/com/encore/backend/service/TempBoardService.java`

Message:

```text
refactor(backend): replace S3 uploader with GCS storage abstraction

Introduce a storage interface and a Google Cloud Storage implementation.
Remove the old S3-specific uploader path and align backend dependencies with GCS.
```

### Commit 5

Files:
- `backend/src/main/java/com/encore/backend/controller/BoardController.java`
- `backend/src/main/java/com/encore/backend/controller/UserController.java`
- `backend/src/main/java/com/encore/backend/service/BoardService.java`
- `backend/src/main/java/com/encore/backend/service/UserServiceImpl.java`
- `backend/src/main/resources/application.yml`
- `nginx/default.conf`

Message:

```text
fix(backend): allow larger optional image uploads

Make multipart image parts optional on update and preserve existing image URLs.
Raise nginx and Spring multipart limits so profile and board uploads can pass through.
```

### Commit 6

Files:
- `.env.example`
- `.gitignore`
- `docker-compose2.yml`
- `backend/src/main/resources/application.yml`

Message:

```text
chore(config): centralize compose and backend env wiring

Move Mongo, Cognito, and GCS runtime values into shared env-driven configuration.
Wire compose and Spring config to consume the same production-facing variables.
```

### Commit 7

Files:
- `frontend/.env.example`
- `frontend/src/aws-exports.ts`
- `frontend/src/config/aws/aws-exports.js`
- `frontend/src/config/aws/aws-exports.d.ts`

Message:

```text
chore(frontend): move Cognito config to Vue env variables

Replace hardcoded Amplify pool settings with VUE_APP-based configuration.
Keep frontend auth settings environment-specific without editing source per deploy.
```

### Commit 8

Files:
- `scripts/seed-mongo.js`
- `docker-compose2.yml`

Message:

```text
feat(dev): add local Mongo seed workflow

Create an idempotent Mongo seed script with Korean demo content.
Hook local compose startup into Mongo healthchecks and one-shot seeding.
```

### Commit 9

Files:
- `frontend/src/views/Article.vue`
- `frontend/src/views/Scrap.vue`
- `frontend/src/components/layout/Page.vue`
- `frontend/src/components/layout/Post.vue`
- `frontend/src/views/PreviewModal.vue`
- `frontend/src/assets/css/SearchResult.css`

Message:

```text
feat(frontend): improve article card and post image rendering

Show board cover images in list/detail views and enrich cards with profile data.
Clean up image styling so built assets render the expected opacity and layout.
```

### Commit 10

Files:
- `frontend/src/App.vue`
- `frontend/src/store/index.ts`

Message:

```text
fix(frontend): restore persisted auth state on app startup

Rebuild user session state from Cognito on reload instead of losing login UI.
Restore access token and user info consistently during app bootstrap.
```

## Extra Note

There are many additional modified frontend files in the current working tree that appear broader than the focused features above. If you want clean history, review those separately before committing and avoid mixing them into the targeted commits above unless they are truly required for the same behavior.
