package ru.bardinpetr.itmo.lab5.db.frontend.adapters.owned.error;

public class NotOwnedException extends RuntimeException {
    public NotOwnedException(String user) {
        super("WorkerUpdateFrameZ.updateFailed.notOwned.text");
    }
}
