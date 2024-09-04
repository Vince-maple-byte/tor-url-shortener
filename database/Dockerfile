FROM mysql

ENV MYSQL_ROOT_PASSWORD=""
ENV MYSQL_DATABASE=torurl
ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306