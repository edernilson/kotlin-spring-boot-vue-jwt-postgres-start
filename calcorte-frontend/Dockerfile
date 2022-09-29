#FROM node:lts-alpine as build-stage
#MAINTAINER "edernilson/calcorte-web"
#WORKDIR /app
#ARG VUE_APP_API_URL
#ENV VUE_APP_API_URL=$VUE_APP_API_URL
#COPY package*.json ./
#RUN npm install
#COPY . .
#RUN npm run build
#
## production stage
#FROM nginx:stable-alpine as production-stage
#COPY --from=build-stage /app/dist /usr/share/nginx/html/dist
#COPY --from=build-stage /app/index.html /usr/share/nginx/html
#COPY --from=build-stage /app/default.css /usr/share/nginx/html
#EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]


FROM node:lts-alpine
RUN npm install -g http-server
WORKDIR /app/frontend
COPY package*.json ./
RUN npm install --production
COPY . .
RUN npm run build
COPY ./index.html ./dist
COPY ./default.css ./dist
CMD ["http-server", ".", "-p", "3000"]
