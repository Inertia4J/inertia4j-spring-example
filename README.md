# inertia4j-spring-example

Example application made using Spring, Inertia and React, with the Inertia4J Spring adapter.

![Demo](https://github.com/Inertia4J/inertia4j-spring-example/blob/assets/Demo.gif)

## Running

1. Set the database up. By default, the project uses PostgreSQL, so you should edit
   `src/main/resources/application.properties` to add your own database credentials.
2. In one terminal tab/window, run `./gradlew bootRun`.
3. In another terminal tab/window, enter the `src/main/front` directory and run the following commands:
    ```sh
    npm install -G yarn # you will have to install Node.js if you don't have it already
    yarn install
    yarn start
    ```
4. Access [http://localhost:5173/](http://localhost:5173/).

The server running on port 5173 is a custom Vite development server and should work for other frontend and backend frameworks.
If necessary, you can tweak it by modifying [server.js](/src/main/front/server.js).
