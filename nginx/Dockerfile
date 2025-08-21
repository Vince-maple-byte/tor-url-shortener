FROM nginx:alpine

RUN apk add --no-cache \
    tor

# Need to add a copy file for the torrc file
COPY . /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80