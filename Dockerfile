FROM golang:alpine

# Update package index and install go + git
RUN apk add --update go git nodejs

RUN go get gopkg.in/mgo.v2 && \
    go get github.com/labstack/echo/...

RUN mkdir -p /usr/src/app

COPY public /usr/src/app/public
COPY main.go /usr/src/app

WORKDIR /usr/src/app/public

RUN npm install && npm run typings && npm run tsc

WORKDIR /usr/src/app

RUN go build -o main .

EXPOSE 9000

CMD ["./main"]
