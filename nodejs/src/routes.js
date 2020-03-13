const express = require('express');
const routes = express.Router();

// Controllers
const UserController = require('./controllers/UserController');

// Users
routes.get("/users", UserController.index);
routes.get("/users/since/:since", UserController.since);
routes.get("/users/:id", UserController.show);
routes.post("/users", UserController.create);
routes.put("/users/:id", UserController.upgrade);
routes.delete("/users/:id", UserController.destroy);

module.exports = routes;