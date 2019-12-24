- [js函数传参](https://cloud.tencent.com/developer/ask/193307)

confirmDelete()函数需要字符串id

```js
th:onclick="'javascript:confirmDelete(\'' + ${company.id} + '\');'"
```

如果它需要一个数字ID
```js
th:onclick="'javascript:confirmDelete(' + ${company.id} + ');'"
```

## MySQL

- 查看时区

```sql
show variables like "%time_zone%";
```


