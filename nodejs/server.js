const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const requireDir = require('require-dir');

// DB config
mongoose.connect(
    "mongodb://localhost:27017/node",
    { useNewUrlParser: true, useUnifiedTopology: true, useCreateIndex: true, useFindAndModify: false }
);
requireDir('./src/models');

// Api config
const app = express();
app.use(express.json());
app.use(cors());
app.use("/api", require("./src/routes"));
app.listen(8081); 