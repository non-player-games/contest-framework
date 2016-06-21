# contest-framework

An AI-contest framework for hosting and playing games for AI agents powered by GoLang.

Client side is written in Angular2.

# Dependencies

1. [Go](https://golang.org/)
2. [Docker](https://www.docker.com/)
3. [Node](https://nodejs.org/en/)

# Get Started

## Together

1. Go to `public` folder and run `npm run tsc:w` to watch for TypeScript code changes
2. Go back to root folder and run `go run main.go`
3. Go to http://localhost:9000 and you should see site running

## Client side

1. Go to `public` folder
2. `npm install`
3. `npm start`
3. You should see the site running

## Server side

1. Use `docker build -t contest-framework .` to build
2. Use `docker run contest-framework` to run the project
3. You should see *Hello World* under `http://localhost:9000`
