# Compatibility

| - | - |
| --- | --- |
| Spring boot | 2.7.2 |
| Java | 11 |
| Build tool | maven |
| Database | h2 |
| Swagger | 3.x |
| Jpa | 2.7.x |

# Swagger Info

- Swagger Home : [http://localhost:8899/swagger-ui/index.html](http://localhost:8899/swagger-ui/index.html)

## User API

- /v1/signup  (Method : POST)

```jsx
jwt 필수여부(x)

해당 API는 회원가입 API입니다.
jwt 인증은 필요 없으며 포맷에 맞게 실행하시면 됩니다.

- header
{}
- parameter
{}
- body value
{
  "userid": "string",
  "pw": "string",
  "username": "string"
}
```

- /v1/signin  (Method : POST)

```jsx
jwt 필수여부(x)

해당 API는 로그인 API입니다.
jwt 인증은 필요 없으며 포맷에 맞게 실행하시면 됩니다.

- header
{}
- parameter
{}
- body value
{
  "userid": "string",
  "pw": "string"
}
```

- /v1/profile  (Method : GET)

```jsx
jwt 필수여부(o)

해당 API는 프로필 조회 API입니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- parameter
{}
- body value
{}
```

- /v1/point  (Method : GET)

```jsx
jwt 필수여부(o)

해당 API는 포인트 조회 API입니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- parameter
{}
- body value
{}
```

## Article API

- /v1/article  (Method : POST)

```jsx
jwt 필수여부(o)

해당 API는 글 쓰기 API입니다.
글 작성자는 3 포인트가 증가합니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- parameter
{}
- body value
{
	"articleTitle": "string",
	"articleContents": "string"
}
```

- /v1/article  (Method : PUT)

```jsx
jwt 필수여부(o)

해당 API는 글 수정 API입니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- parameter
{}
- body value
{
	"articleId": "string",
	"articleTitle": "string",
	"articleContents": "string"
}
```

- /v1/article/{articleId}  (Method : GET)

```jsx
jwt 필수여부(o)

해당 API는 글 조회 API입니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- pathvariable
{
	"articleId": "string"
}
- parameter
{}
- body value
{}
```

- /v1/article/{articleId}  (Method : DELETE)

```jsx
jwt 필수여부(o)

해당 API는 글 삭제 API입니다.
글 삭제할 경우, 타 유저의 댓글로 획득한 포인트는 전부 회수됩니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- pathvariable
{
	"articleId": "string"
}
- parameter
{}
- body value
{}
```

## Comment API

- /v1/comments (Method : POST)

```jsx
jwt 필수여부(o)

해당 API는 댓글 작성 API입니다.
타 유저 글에 댓글을 작성할 경우 2포인트를 획득하며, 해당 원글 작성자는 1포인트를 획득합니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- parameter
{
	"articleId": "string",
	"commentsContents": "string"
}
- body value
{}
```

- /v1/comments/{commentsId}. (Method : DELETE)

```jsx
jwt 필수여부(o)

해당 API는 댓글 삭제 API입니다.
댓글을 삭제할 경우, 해당 댓글로 획득한 포인트를 회수합니다.
jwt 토큰 인증이 필요하며 인증 정보가 없거나 올바르지 않을 시 exception을 떨어뜨려줍니다.

- header
{
	"Authorization": "token"
}
- pathvariable
{
	"commentsId": "string"
}
- parameter
{
	"articleId": "string"
}
- body value
{}
```

# API Response Object

```json
{
  "data": {},            // api 요청의 반환되는 데이터
  "apiStatus": {         // api 결과의 객체
    "apiCode": 0,        // api 결과 코드
    "message": "string"  // api 결과 메세지
  }
}
```

- Table에 insert하는 작업을 성공 시 상태코드 (201)을 보내줍니다.
- select, update, delete 작업 성공 시 상태코드 (200)을 보내줍니다.
- 서버 요청 예외 발생 시 다음과 같은 Object 형식으로 상태값을 보내줍니다.

## e.g

- 로그인 요청(실패)

    ```json
    {
      "apiStatus": {
        "apiCode": 401,
        "message": "Unauthorized"
      }
    }
    ```

- 로그인 요청(성공)
    - 로그인 요청 성공 시 Jwt 토큰을 반환합니다. (Life time : 30m, Refresh Token은 발급하지 않습니다)
    - 반환된 Jwt 토큰은 ‘Header : Authorization’에 포함시켜 로그인, 회원가입 외의 Api에 접근을 가능하게 합니다.
    - e.g > Header - 'Authorization: Bearer gAAAAABjYfaTGfH-KFB5qZI6LcDvTF6WqITnvpxO_V8GYkNUPp3XYnF5CVsmDAqCW21lRFLXAm3UKRtv89cslqsbAflYx5wz7PdHoilNMJzfLNhTlU2yaxZjrAMTHFhn4A8JMV7NA6GPnE4jgsFKkTdoeCvfCXjOUCyhnTUlU9cVlAw7WPHVCa4’

    ```json
    {
      "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Y2JiZWRlMy00N2YyLTQ1NWQtODg5MS05YjBmYjkwNDMzZjIiLCJpYXQiOjE2NzMwODEzNjMsImV4cCI6MTY3MzA4MzE2M30.W8UGrQ1PzNkcegAbdIXu_TLPPBHXQKGN3XEJ_i_goH8"
      },
      "apiStatus": {
        "apiCode": 200,
        "message": "OK"
      }
    }
    ```

- Article 생성 요청(성공)

    ```json
    {
      "data": {
        "articleId": "2e3808cc-e59e-48fc-83da-80cc27c5a621"
      },
      "apiStatus": {
        "apiCode": 201,
        "message": "Created"
      }
    }
    ```

- Article 댓글 작성(실패)

    ```json
    {
      "apiStatus": {
        "apiCode": 603,
        "message": "글을 찾을 수 없습니다."
      }
    }
    ```


# API Status List

```json
OK(200, "OK"),
CREATED(201, "Created"),
BAD_REQUEST(400, "Bad Request"),
UNAUTHORIZED(401, "Unauthorized"),
FORBIDDEN(403, "Forbidden"),
USER_NOT_FOUND(600, "유저를 찾을 수 없습니다."),
INVALID_PASSWORD(601, "비밀번호가 올바르지 않습니다."),
ID_ALREADY_EXIST(602, "해당 아이디가 존재합니다."),
ARTICLE_NOT_FOUND(603, "글을 찾을 수 없습니다."),
ARTICLE_UNAUTHORIZED(604, "글 권한이 없습니다."),
COMMENT_NOT_FOUND(605, "댓글을 찾을 수 없습니다."),
COMMENT_UNAUTHORIZED(606, "댓글 권한이 없습니다."),
INCORRECT_JWT_TOKEN(998, "Jwt 토큰이 올바르지 않습니다."),
SYSTEM_ERROR(999, "SYSTEM_ERROR");
```

## SQL QUERY

```sql

CREATE TABLE USER_TB (
	UID CHARACTER(36) NOT NULL,
	CREATE_DT TIMESTAMP NOT NULL,
	UPDATE_DT TIMESTAMP,
	POINT BIGINT,
	PASSWORD CHARACTER VARYING(64),
	USER_ID CHARACTER VARYING(20),
	USER_NAME CHARACTER VARYING(10),
	CONSTRAINT CONSTRAINT_2 PRIMARY KEY (UID)
);
CREATE UNIQUE INDEX PRIMARY_KEY_2 ON USER_TB (UID);
---
CREATE TABLE ARTICLE (
	ID CHARACTER(36) NOT NULL,
	CREATE_DT TIMESTAMP NOT NULL,
	UPDATE_DT TIMESTAMP,
	CONTENT CHARACTER VARYING(200),
	TITLE CHARACTER VARYING(100),
	UID CHARACTER(36),
	CONSTRAINT CONSTRAINT_F PRIMARY KEY (ID),
	CONSTRAINT FKFP15YVP4SVA3J1POHHNYTOO26 FOREIGN KEY (UID) REFERENCES USER_TB(UID) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE INDEX FKFP15YVP4SVA3J1POHHNYTOO26_INDEX_F ON ARTICLE (UID);
CREATE UNIQUE INDEX PRIMARY_KEY_F ON ARTICLE (ID);
---
CREATE TABLE COMMENT (
	ID CHARACTER(36) NOT NULL,
	CREATE_DT TIMESTAMP NOT NULL,
	UPDATE_DT TIMESTAMP,
	CONTENT CHARACTER VARYING(200),
	ARTICLE_ID CHARACTER(36),
	UID CHARACTER VARYING(36),
	IS_DELETE TINYINT,
	CONSTRAINT CONSTRAINT_6 PRIMARY KEY (ID),
	CONSTRAINT FK5YX0UPHGJC6IK6HB82KKW501Y FOREIGN KEY (ARTICLE_ID) REFERENCES ARTICLE(ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE INDEX FK5YX0UPHGJC6IK6HB82KKW501Y_INDEX_D ON COMMENT (ARTICLE_ID);
CREATE UNIQUE INDEX PRIMARY_KEY_D4 ON COMMENT (ID);
```

# 질문 리스트

- (1) MSA 구성하는 방식에는 어떤 것들이 있고, 그중 어떤 방식을 선택하실지 서술해 주세요.

  MSA를 구성하기 위해서는 서비스의 확장성을 고려해 서버를 클라우드 인프라로 설치하여 클라우드 환경인Kubernetes에서 구성하는 방법을 택할 것입니다. MSA 서버의 요청은 Cloud Gateway 앞단에 두어 로깅, 인증을 진행하고 트래픽을 분산해서 로드밸런싱 해 줄 것입니다. 내부적으로 분산 트랜잭션을 사용한다면 MSA환경의 트랜잭션 실패 상황을 고려해 saga pattern을 사용한 서비스들의 보상 트랜잭션을 고려할 것입니다.

- (2) 외부 의존성이 높은 서비스를 만들 때 고려해야 할 사항이 무엇인지 서술해 주세요.

  해당 외부 서비스가 장애가 났을 시를 고려하여 아키텍처 적으로 고려하여 설계합니다.

  외부 서비스의 이중화를 고려하고 이중화의 비용이 많이 든다면 해당 서비스를 제외하고 나머지 서비스들이 잘 운영될 수 있도록 의존성을 줄여서 설계합니다.

- (3) 글로벌 서비스를 만들 때 고려해야 할 사항이 무엇인지 서술해 주세요.

  서비스를 제공할 지역의 언어가 지원 되어야 합니다. 또한 해당 국가의 법규 등 고려해야 하며 타겟을 글로벌 유저에게 맞춰서 애플리케이션도 해당 국가에 필요한 서비스인지 고려해야 할 것 같습니다.

- (4) Back-End 개발자의 개발 범위에 대해 본인의 생각을 서술해 주세요.

  애플리케이션의 서버 측 개발은 기본으로 개발한 서비스의 유지보수를 담당합니다. 또한 프론트엔드 애플리케이션을 지원하기 위한 코드를 작성하며 데이터베이스 설정과 유지관리, 애플리케이션의 보안과 확장성을 고려하고 계속해서 성능을 최적화 시켜야 합니다.

- (5) Back-End 개발에서 중요하다고 생각하는 지식에 대해 서술해 주세요.

  개발 언어에 대한 이해가 가장 중요하지만 개발 경력이 쌓여 갈 수록 인프라 적으로 아키텍처 설계하는 능력과 유지보수가 쉬운 즉 다른 사람들이 이해할 수 있게 작성한 클린코드 작성 능력을 중요하다고 생각을 하고 있습니다.

- (6) 본인이 생각하는 이상적인 개발환경은 무엇인지 서술해 주세요.

  먼저 개발하는 회사의 개발팀 분위기와 문화가 중요하다고 생각합니다. 개발하는데에 있어서 충분한 사양을 가진 최신 장비와 서버는 기본이라 생각하며 팀과의 협동과 믿음을 기반으로 자율성과 책임성을 중요시 여기는 환경이 개발하는데 있어서 가장 이상적인 환경이라 생각합니다.

- (7) 본인이 가장 잘 아는 개발 방법론에 대해 서술해 주세요.

  저는 개인적으로 애자일 방법론을 선호하며 그 중 스크럼 방식의 환경에서 개발해 왔습니다. 프로젝트 관리를 위해 일정한 짧은 주기의 스프린트 기간동안 정해진 백로그 기능들을 개발하여 검증하고 새로운 기능들의 목표 달성하는 재미가 있는 개발 방법론 입니다.

- (8) 본인이 애플리케이션에서 버그를 찾아서 테스트하는 프로세스에 대해 서술해 주세요.

  중요한 기능들은 기본적으로 기능테스트를 진행합니다. 기능 테스트를 진행하고 해당 서비스를 테스트 환경에서 통합적으로 테스트를 합니다. 통합테스트를 하면서 예외, 에러가 생길 수 있는 상황들을 고려해 테스트를 진행 후 리팩토링 합니다.

- (9) 비개발 직군 동료와 효과적으로 의사소통하려면 어떻게 해야할지 본인의 생각을 서술해 주세요.
