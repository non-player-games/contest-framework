FROM golang:onbuild

EXPOSE 9000

RUN mkdir -p /usr/src/app

COPY main.go /usr/src/app

WORKDIR /usr/src/app

RUN go build -o main .

CMD ["./main"]
