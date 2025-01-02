FROM nginx:latest

RUN apt-get update && apt-get install -y \
    tor \
    && apt-get clean


COPY . /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80