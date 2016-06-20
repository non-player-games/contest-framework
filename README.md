# contest-framework

An AI-contest framework for hosting and playing games for AI agents powered by GoLang.

Client side is written in Angular2.

# Dependencies

1. [Go](https://golang.org/)
2. [Docker](https://www.docker.com/)
3. [Node](https://nodejs.org/en/)

# Get Started

## Client side

1. Go to `public` folder
2. `npm install`
3. `npm start`
3. You should see the site running

## Server side

1. Use `docker build -t contest-framework .` to build
2. Use `docker run contest-framework` to run the project
3. You should see *Hello World* under `http://localhost:9000`
