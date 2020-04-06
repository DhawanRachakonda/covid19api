FROM nginx:1.16.0-alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/conf.d
COPY ./fullchain.pem /etc/nginx/ssl
COPY ./privkey.pem /etc/nginx/ssl
CMD ["nginx", "-g", "daemon off;"]
