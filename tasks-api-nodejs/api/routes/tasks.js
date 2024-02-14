module.exports = app => {
    const controller = app.controller.tasks;

    app.route(`/tasks`).get(controller.list);

    app.route(`/tasks/:idTask`).get(controller.findById);

    app.route(`/tasks`).post(controller.save);

    app.route(`/tasks/:idTask`).put(controller.update);

    app.route(`/tasks/:idTask`).delete(controller.delete);
};