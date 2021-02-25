# Starting this App

```bash
# Start the Java app (Open localhost:8080/companies/)
docker-compose up -d
# Populate the DB
docker-compose run --rm -v ${PWD}:/opt/src -w /opt/src mysql bash
# In the container shell. The password is password D:
mysql -ucompanies -hmysql -p
mysql -ucompanies -hmysql -p companies < ./db/creation.sql
```
