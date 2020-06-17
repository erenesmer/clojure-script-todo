const express = require('express');
const bodyParser = require('body-parser');
const db = require('./db.js');

const app = express();

let TODOS = [...db];

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

var allowCrossDomain = function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, PATCH, DELETE, OPTIONS');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
}

app.use(allowCrossDomain);

app.get('/', (req, res) => {

  res.json({
    status: true,
    data: [ ...TODOS ]
  });

});

app.post('/', (req, res) => {
  const id = TODOS.length ?
    TODOS[TODOS.length - 1]['id'] + 1 :
    1;
  const { title, done, created } = req.body;

  if (!title.trim()) {
    return res.status(400).send({
      status: false,
      'error-message': "Title can not be empty"
    });
  }

  const data = {
    id,
    title,
    done: done || false,
    created
  };

  TODOS.push(data);

  return res.json({
    status: true,
    data: { ...data }
  });

});

app.patch('/:id', (req, res) => {
  const { id } = req.params;
  const { done } = req.body;

  const todo = TODOS.find(todo => todo.id == id);

  if (todo) {
    todo.done = done;

    return res.json({
      status: true,
      data: { ...todo }
    });
  }

  return res.status(404).send(new Error("Not Found"));
});

app.delete('/:id', function (req, res) {
  let { id } = req.params;
  id = parseInt(id);

  const todo = TODOS.find(todo => todo.id === id);
  if (todo) {
    TODOS = TODOS.filter(i => i.id !== id);

    return res.json({
      status: true,
      data: { }
    });
  }

  return res.status(404).send(new Error("Not Found"));
});


const PORT = 8000;
app.listen(PORT, () => {
  console.log(`Listening at http://localhost:${PORT}`)
});
