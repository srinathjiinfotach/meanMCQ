For login

```bash
curl -X POST -vu meanmcq:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=pass1&username=musa&grant_type=password&scope=read%20write&client_secret=123456&client_id=meanmcq"
```