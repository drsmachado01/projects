const express       = require('express');
const config        = require('config');
const consign        = require('consign');

module.exports = () => {
    const app = express();

    app.set('port', process.env.PORT || config.get('server.port'));

    app.use(express.json());
    app.use(express.urlencoded({ extended: true}));

    consign({cwd: 'api'})
    .then(`data`)
    .then(`controller`)
    .then(`routes`)
    .into(app);

    return app;
};