# Frontend

## Keycloak Setup example
[Slackspace Example](http://slackspace.de/articles/authentication-with-spring-boot-angularjs-and-keycloak/)


## Making authenticated calls with key cloak.

1. http get :8081/api/me
    Response: 401 Unauthorized
2. 
```sh 
RESULT=`curl --data "grant_type=password&client_id=spring-angular-frontend&username=admin&password=admin"\
http://localhost:8080/auth/realms/master/protocol/openid-connect/token`
 
echo $RESULT
```
3. Find the token using the following shell command
```sh 
TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`
```

4. Now Make an authenticated requests with such as
```sh 
curl http://localhost:8081/api/me -H "Authorization: bearer $TOKEN"
```
## https://auth0.com/blog/angular-2-authentication/

## Angular Authentication.

http://angularjs.blogspot.com/2016/11/easy-angular-authentication-with-json.html
https://medium.com/@blacksonic86/angular-2-authentication-revisited-611bf7373bf9

## Angular Http Interceptor

https://www.illucit.com/blog/2016/03/angular2-http-authentication-interceptor/
https://scotch.io/@kashyapmukkamala/using-http-interceptor-with-angular2

## Loading Interceptor

https://long2know.com/2017/01/angular2-http-interceptor-and-loading-indicator/

## Font-awesome

https://medium.com/@robince885/angular-2-project-with-bootstrap-1e6fc82dc017

## Resolve Guard

http://shermandigital.com/blog/wait-for-data-before-rendering-views-in-angular-2/
