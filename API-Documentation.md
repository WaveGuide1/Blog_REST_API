# API DOCUMENTATION.

### Entity and Business logic:

1. **USER**
2. **POSTS**
3. **COMMENTS**

### A. Authentication and Authorisation

#### 1. Register API
- **Endpoint:** ` POST /api/v1/auth/register `
- **Request Body:**

```
{
  "firstname": "string",
  "lastname": "string",
  "email": "string",
  "password": "string",
  "role": "USER"
  }
  ```
- **Successful Response (HTTP Status Code: 200 OK)**

```
{
  "access_token": "string",
  "refresh_token": "string"
}
```

#### 2. Login API
- **Endpoint:** `Post /api/v1/auth/login`
- **Request Body:**
```
{
  "email": "string",
  "password": "string"
}
```
- **Successful Response (Http Status Code: 200 OK)**
```
{
  "access_token": "string",
  "refresh_token": "string"
}
```
#### 3. Refresh Token API
- **Endpoint:** `POST /api/v1/auth/refresh-token`
- **Request Body:**
```

```
- **Successful Response (Http Status Code: 200 OK)**
```

```
#### 4. Change Password API
- **Endpoint:** `PATCH /api/v1/users/change/password`
- **Request Body:**
```
{
  "currentPassword": "string",
  "newPassword": "string",
  "confirmationPassword": "string"
}
```
- **Successful Response (Http Status Code: 200 OK)**
```
{}
```

### B. Posts

#### 1. Create Post
- **Endpoint:** `POST /api/v1/posts/`
- **Request Body**
```
{
  "title": "string",
  "body": "string"
}
```
- **Successful Response (Http Status Code: 200 OK)**
```
{
  "info": {
    "postId": "string",
    "title": "string",
    "body": "string",
    "userId": "string",
    "comments": [],
    "createAt": "2024-05-22T10:24:49.291Z",
    "updatedAt": "2024-05-22T10:24:49.291Z"
  },
  "message": "string"
}
```
#### Get all Post
- **Endpoint:** `GET /api/v1/posts/`
- **Params:**
```
pageNo
integer($int32)
(query)
Default value : 0

0
pageSize
integer($int32)
(query)
Default value : 10

10
sortBy
string
(query)
```
- **Successful Response (Http Status Code: 200 OK)**
```
 "body": "string",
      "userId": "string",
      "comments": [
        {
          "commentId": "string",
          "message": "string",
          "postId": "string",
          "userId": "string",
          "createAt": "2024-05-22T10:27:55.298Z",
          "updatedAt": "2024-05-22T10:27:55.298Z"
        }
      ],
      "createAt": "2024-05-22T10:27:55.298Z",
      "updatedAt": "2024-05-22T10:27:55.298Z"
    }
  ],
}
```
#### Delete Post
- **Endpoint:** `DELETE /api/v1/posts/{postId}`
- **Params:**
```
postId *
string
(path)

```
- **Successful Response (Http Status Code: 200 OK)**
```
{
"message": "String"
}
```
#### Update Post
- **Endpoint:** `PUT /api/v1/posts/{postId}`
- **Params:**
```
postId *
string
(query)
```
- **Request Body:**
```
{
  "title": "string",
  "body": "string"
}
```
- **Successful Response (Http Status Code: 200 OK)**
```
{
  "info": {
    "postId": "string",
    "title": "string",
    "body": "string",
    "userId": "string",
    "comments": [
      {
        "commentId": "string",
        "message": "string",
        "postId": "string",
        "userId": "string",
        "createAt": "2024-05-22T10:43:06.208Z",
        "updatedAt": "2024-05-22T10:43:06.208Z"
      }
    ],
    "createAt": "2024-05-22T10:43:06.208Z",
    "updatedAt": "2024-05-22T10:43:06.208Z"
  },
  "message": "string"
}
```
#### Get a Post
- **Endpoint:** `GET /api/v1/posts/{postId}`
- **Params:**
```
postId *
string
(query)
```
- **Successful Response (Http Status Code: 200 Ok)**
```
{
  "info": {
    "postId": "string",
    "title": "string",
    "body": "string",
    "userId": "string",
    "comments": [
      {
        "commentId": "string",
        "message": "string",
        "postId": "string",
        "userId": "string",
        "createAt": "2024-05-22T10:50:06.961Z",
        "updatedAt": "2024-05-22T10:50:06.961Z"
      }
    ],
    "createAt": "2024-05-22T10:50:06.961Z",
    "updatedAt": "2024-05-22T10:50:06.961Z"
  },
  "message": "string"
}
```

### C. Comments

#### 1. Add Comment
- **Endpoint:** `POST /api/v1/comments/{postId}`
- **Params:**
```
postId *
string
(query)
```
- **Request Body:**
```
{
  "message": "string"
}
```

- **Successful Response (Http Status Code: 200 Ok)**
```
{
  "info": {
    "commentId": "string",
    "message": "string",
    "postId": "string",
    "userId": "string",
    "createAt": "2024-05-22T10:57:21.718Z",
    "updatedAt": "2024-05-22T10:57:21.718Z"
  },
  "message": "string"
}
```

#### 1. Update Comment
- **Endpoint:** `PATCH /api/v1/comments/{commentId}`
- **Params:**
```
postId *
string
(query)
```
- **Request Body:**
```
{
  "message": "string"
}
```
- **Successful Response (Http Status Code: 200 Ok)**
```
{
  "info": {
    "commentId": "string",
    "message": "string",
    "postId": "string",
    "userId": "string",
    "createAt": "2024-05-22T11:01:03.083Z",
    "updatedAt": "2024-05-22T11:01:03.083Z"
  },
  "message": "string"
}
```
#### 1. Delete Comment
- **Endpoint:** `DELETE /api/v1/comments/{commentId}`
- **Params:**
```
postId *
string
(query)
```
- **Successful Response (Http Status Code: 200 Ok)**
```
{
  "message": "string"
}
```
