FROM node:18-alpine
#FROM ubuntu:latest
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999