# ClojureScript - Todo

## Getting Started
This is the main challenge app, built with ClojureScript. This client app needs a back-end server to make a request and get a response to show something on browser / UI. 

We have a very simple back-end server that is written in NodeJS. So before starting, we should wake the server up! 

That back-end app is located in server folder. 

Classically, `cd server`, `npm install`, and `npm start` for the server to run.

Then we can run our challenge App.

## Development

### Running the App

Start a temporary local web server, build the app with the `dev` profile, and serve the app with
hot reload:

```sh
lein dev
```

Please be patient; it may take over 20 seconds to see any output, and over 40 seconds to complete.

When `[:app] Build completed` appears in the output, browse to
[http://localhost:8280/](http://localhost:8280/).

### Compiling CSS with `lein-less`
We used lein-less to use less for this project. So, you also run the `lein less once` or `lein less auto` command to compile css.

If you want to change or develop something in less file and you want less garden to detect it and apply changes then you should run `lein less auto` command.

## Production

Build the app with the `prod` profile:

```sh
lein prod
```

