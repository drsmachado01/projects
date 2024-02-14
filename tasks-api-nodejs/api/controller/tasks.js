module.exports = (app) => {
    const taskList = app.data.tasks;
    const controller = {};

    controller.list = (req, res) => res.status(200).json(taskList);

    controller.save = (req, res) => {
        let nbr = taskList.length + 1;
        let newTask = {
            idTask: nbr,
            taskName: req.body.taskName,
            taskDescription: req.body.taskDescription,
            uniqueExecution: req.body.uniqueExecution,
            startDate: req.body.startDate,
            startTime: req.body.startTime,
            endDate: req.body.endDate,
            endTime: req.body.endTime,
            address: req.body.address,
            host: req.body.host,
            _links: {
                self: {
                    href: "http://localhost:8080/tasks/" + nbr
                }
            }
        };
        taskList.push(newTask);
        res.status(201).json(newTask);
    }

    controller.findById = (req, res) => {
        const {idTask} = req.params;

        const theTaskIndex = findTask(idTask, res);

        const theTask = taskList[theTaskIndex];
        res.status(200).json(theTask);
    };

    controller.update = (req, res) => {
        const {idTask} = req.params;
        
        const theTaskIndex = findTask(idTask, res);

        let newTask = {
            idTask: idTask,
            taskName: req.body.taskName,
            taskDescription: req.body.taskDescription,
            uniqueExecution: req.body.uniqueExecution,
            startDate: req.body.startDate,
            startTime: req.body.startTime,
            endDate: req.body.endDate,
            endTime: req.body.endTime,
            address: req.body.address,
            host: req.body.host,
            _links: {
                self: {
                    href: "http://localhost:8080/tasks/" + idTask
                }
            }
        };

        taskList.splice(theTaskIndex, 1, newTask);
        res.status(200).json(newTask);
    };

    controller.delete = (req, res) => {
        const {idTask} = req.params;
        
        const theTaskIndex = findTask(idTask, res);

        taskList.splice(theTaskIndex, 1);
        
        res.status(200).json({
            message: `Task successfuly deleted`
        });
    }

    findTask = (idTask, res) => {
        const theTaskIndex = taskList.findIndex(task => task.idTask == idTask);

        if(theTaskIndex === (-1)) {
            res.status(404).json({
                message: `There is no task associated to id ${idTask}`,
                success: false,
            });
        }
        return theTaskIndex;
    };

    return controller;
};