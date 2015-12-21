# contest-framework

An AI-contest framework for hosting and playing games for AI agents powered by Akka framework.

# Dependencies

1. Java
2. [SBT](http://www.scala-sbt.org/)
3. [Docker](https://www.docker.com/)

# Get Started with SBT

1. Use `sbt compile` to compile project
2. Use `sbt run` to run the project
3. You should see *Hello World* under `http://localhost:9000`

# Package and deploy

1. Use `sbt docker:publishLocal` to create docker file and publish to local docker
2. Get the docker image id by `docker image`
3. Once you get the image id, run the image by `docker run -p 9000:9000 {IMAGE_ID}`
4. Open your browser to `http://{DOCKER_MACHINE_IP}:9000`
5. To deploy, use Digital Ocean driver to run Docker instead
