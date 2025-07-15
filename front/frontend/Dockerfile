FROM node:18 AS builder
WORKDIR /app
COPY . .
RUN npm install && npm run build --configuration=production

FROM nginx:alpine
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html
COPY default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
