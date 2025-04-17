# Journal App API Documentation

This document provides detailed information about the REST API endpoints available in the Journal App. The API is secured using Spring Security with role-based access control.

## Table of Contents

1. [Authentication](#authentication)
2. [Admin Operations](#admin-operations)
3. [Journal Operations](#journal-operations)
4. [Postman Collection](#postman-collection)

## Base URL

All API URLs in this documentation use the base URL:

```
http://localhost:8080
```

## Authentication

The API uses Spring Security for authentication. By default, all endpoints except `/api/auth/**` require authentication.

### Register User

Creates a new user account in the system.

- **URL**: `/api/auth/register`
- **Method**: `POST`
- **Auth required**: No
- **Request Body**:
  ```json
  {
    "username": "testuser",
    "password": "password123"
  }
  ```
- **Success Response**:
  - **Code**: 201 Created
  - **Content**:
    ```json
    {
      "message": "User registered successfully",
      "username": "testuser"
    }
    ```
- **Error Response**:
  - **Code**: 400 Bad Request
  - **Content**:
    ```json
    "Username already exists"
    ```

### Login User

Authenticates a user and creates a session.

- **URL**: `/api/auth/login`
- **Method**: `POST`
- **Auth required**: No
- **Request Body**:
  ```json
  {
    "username": "testuser",
    "password": "password123"
  }
  ```
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "message": "Login successful"
    }
    ```
- **Error Response**:
  - **Code**: 401 Unauthorized
  - **Content**:
    ```json
    "Invalid username or password"
    ```

### Get Current User

Returns information about the currently authenticated user.

- **URL**: `/api/auth/user`
- **Method**: `GET`
- **Auth required**: Yes
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "username": "testuser"
    }
    ```
- **Error Response**:
  - **Code**: 401 Unauthorized
  - **Content**:
    ```json
    "Not authenticated"
    ```

### Logout

Ends the user's session.

- **URL**: `/api/auth/logout`
- **Method**: `POST`
- **Auth required**: Yes
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: Redirects to login page

## Admin Operations

These endpoints require the `ROLE_ADMIN` authority. By default, an admin user is created at application startup with username `admin` and password `admin123`.

### Get All Users

Returns a list of all users in the system.

- **URL**: `/api/admin/users`
- **Method**: `GET`
- **Auth required**: Yes (with ROLE_ADMIN)
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    [
      {
        "id": "60c7b1b0e1c2d34e5f6a7b8c",
        "username": "admin",
        "roles": ["ROLE_ADMIN", "ROLE_USER"],
        "journalEntries": []
      },
      {
        "id": "60c7b1b0e1c2d34e5f6a7b9d",
        "username": "testuser",
        "roles": ["ROLE_USER"],
        "journalEntries": []
      }
    ]
    ```
- **Error Response**:
  - **Code**: 403 Forbidden
  - **Content**: Access Denied

### Add Role to User

Adds a role to a user.

- **URL**: `/api/admin/users/{username}/roles`
- **Method**: `POST`
- **Auth required**: Yes (with ROLE_ADMIN)
- **URL Parameters**: `username=[string]`
- **Request Body**:
  ```json
  {
    "role": "MODERATOR"
  }
  ```
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "username": "testuser",
      "roles": ["ROLE_USER", "ROLE_MODERATOR"]
    }
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "User not found"
    ```

### Remove Role from User

Removes a role from a user.

- **URL**: `/api/admin/users/{username}/roles/{role}`
- **Method**: `DELETE`
- **Auth required**: Yes (with ROLE_ADMIN)
- **URL Parameters**: 
  - `username=[string]`
  - `role=[string]` (without "ROLE_" prefix)
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "username": "testuser",
      "roles": ["ROLE_USER"]
    }
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "User not found"
    ```

### Delete User

Deletes a user from the system.

- **URL**: `/api/admin/users/{username}`
- **Method**: `DELETE`
- **Auth required**: Yes (with ROLE_ADMIN)
- **URL Parameters**: `username=[string]`
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    "User deleted successfully"
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "User not found"
    ```

## Journal Operations

These endpoints allow users to manage their journal entries. All endpoints require authentication.

### Get All Journals

Returns all journal entries for the authenticated user.

- **URL**: `/api/journals`
- **Method**: `GET`
- **Auth required**: Yes
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    [
      {
        "id": "60c7b1b0e1c2d34e5f6a7b8d",
        "title": "My First Journal Entry",
        "content": "This is the content of my first journal entry.",
        "tags": ["personal", "thoughts"],
        "createdAt": "2023-05-01T12:34:56.789Z"
      }
    ]
    ```

### Create Journal Entry

Creates a new journal entry for the authenticated user.

- **URL**: `/api/journals`
- **Method**: `POST`
- **Auth required**: Yes
- **Request Body**:
  ```json
  {
    "title": "My First Journal Entry",
    "content": "This is the content of my first journal entry.",
    "tags": ["personal", "thoughts"]
  }
  ```
- **Success Response**:
  - **Code**: 201 Created
  - **Content**:
    ```json
    {
      "id": "60c7b1b0e1c2d34e5f6a7b8d",
      "title": "My First Journal Entry",
      "content": "This is the content of my first journal entry.",
      "tags": ["personal", "thoughts"],
      "createdAt": "2023-05-01T12:34:56.789Z"
    }
    ```

### Get Journal by ID

Returns a specific journal entry by ID.

- **URL**: `/api/journals/{journal_id}`
- **Method**: `GET`
- **Auth required**: Yes
- **URL Parameters**: `journal_id=[ObjectId]`
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "id": "60c7b1b0e1c2d34e5f6a7b8d",
      "title": "My First Journal Entry",
      "content": "This is the content of my first journal entry.",
      "tags": ["personal", "thoughts"],
      "createdAt": "2023-05-01T12:34:56.789Z"
    }
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "Journal entry not found"
    ```

### Update Journal Entry

Updates an existing journal entry.

- **URL**: `/api/journals/{journal_id}`
- **Method**: `PUT`
- **Auth required**: Yes
- **URL Parameters**: `journal_id=[ObjectId]`
- **Request Body**:
  ```json
  {
    "title": "Updated Journal Entry",
    "content": "This is the updated content of my journal entry.",
    "tags": ["personal", "thoughts", "updated"]
  }
  ```
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    {
      "id": "60c7b1b0e1c2d34e5f6a7b8d",
      "title": "Updated Journal Entry",
      "content": "This is the updated content of my journal entry.",
      "tags": ["personal", "thoughts", "updated"],
      "createdAt": "2023-05-01T12:34:56.789Z",
      "updatedAt": "2023-05-02T12:34:56.789Z"
    }
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "Journal entry not found"
    ```

### Delete Journal Entry

Deletes a journal entry.

- **URL**: `/api/journals/{journal_id}`
- **Method**: `DELETE`
- **Auth required**: Yes
- **URL Parameters**: `journal_id=[ObjectId]`
- **Success Response**:
  - **Code**: 200 OK
  - **Content**:
    ```json
    "Journal entry deleted successfully"
    ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**:
    ```json
    "Journal entry not found"
    ```

## Postman Collection

A Postman collection is available in the file `postman_collection.json` to help you test all API endpoints. The collection includes:

1. Authentication requests
2. Admin operations requests
3. Journal operations requests

### Importing the Collection

1. Open Postman
2. Click on "Import" button
3. Select the `postman_collection.json` file
4. The collection will be imported with all requests

### Authentication in Postman

The collection is configured with Basic Authentication for the admin user by default. To use different users:

1. Select the request
2. Go to the "Authorization" tab
3. Select "Basic Auth" type
4. Enter the username and password
5. Click "Send"

For the first request, you'll need to authenticate. After a successful login, subsequent requests will use the session cookie automatically.

### Testing Admin Operations

Make sure you're authenticated as a user with `ROLE_ADMIN` (the default admin user is recommended) when testing admin operations. 