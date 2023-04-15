package ru.bardinpetr.itmo.lab5.server.executor;

import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.api.PrintDescendingCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.dao.utils.DBPager;
import ru.bardinpetr.itmo.lab5.server.dao.workers.IWorkerCollectionDAO;

import java.util.Comparator;

/**
 * Executor for resolving workers collection commands with paging to DAO calls
 */
public class WorkersPagingExecutor extends Executor {

    private final IWorkerCollectionDAO dao;
    private final DBPager<Worker> pager;

    public WorkersPagingExecutor(IWorkerCollectionDAO dao) {
        this.pager = new DBPager<>();
        this.dao = dao;

        registerOperation(ShowCommand.class, this::showAll);
        registerOperation(PrintDescendingCommand.class, this::printDescending);
    }

    private ShowCommand.ShowCommandResponse showAll(ShowCommand req) {
        var resp = req.createResponse();
        var data = dao.asStream().sorted(Worker.getComparator());
        resp.setResult(pager.paginate(data, req));
        return resp;
    }

    private PrintDescendingCommand.PrintDescendingCommandResponse printDescending(PrintDescendingCommand req) {
        var resp = req.createResponse();
        var data = dao.asStream().sorted(Comparator.reverseOrder());
        resp.setResult(pager.paginate(data, req));
        return resp;
    }

}
