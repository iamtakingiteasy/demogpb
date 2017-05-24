# demo

для вызова через сurl:
```
curl -H "Authorization: Bearer $(curl "localhost:9080/auth-service/auth/oauth/token" -d "grant_type=password&username=reader&password=reader&client_id=web-app" | jq '.access_token' -r)" "http://localhost:8081/catalog"
```

в браузере открыть по адресу 