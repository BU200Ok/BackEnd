# 이후로 application.yml은 .gitignore에 꼭 추가 합니다.
뭐...의존성은 중요합니다..뭐시기
```
spring:
  mail:
    host: smtp.naver.com
    port: 587
    username: 자신의 네이버 이메일
    password: 네이버 비밀번호
    properties:
      smtp:
        auth: true
      starttls:
        enable: true
  datasource:
    url: jdbc:mariadb://전달받은아이피//groupware
    username: 전달받은 값
    password: 전달받은 값
```
