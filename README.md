# 이후로 application.yml은 .gitignore에 꼭 추가 합니다.
뭐...의존성은 중요합니다..뭐시기
```


# server port config
server:
  port: 8080  # server port

# db config
spring:
  mail:
    host: {호스트}
    port: {포트}
    username: {메일}
    password: {비번}
    properties:
      smtp:
        auth: true
      starttls:
        enable: true
  jwt:
    secret: 시크릿~
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://{호스트}:3306/groupware # db url
    username: feeleat                                # db username
    password: {패스워드}                      # db password
  devtools:
    restart:
      enabled: false # auto restart option enabled

  # jpa config
  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        show_sql: true
        format_sql: true

# Logging Level
logging:
  level:
    root: info

file:
  upload-dir: C:\project-files\bu200ServerFile
```
