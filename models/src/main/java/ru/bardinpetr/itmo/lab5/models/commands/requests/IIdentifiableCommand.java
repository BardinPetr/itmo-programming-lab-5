package ru.bardinpetr.itmo.lab5.models.commands.requests;

public interface IIdentifiableCommand {
    /**
     * @return any textual identifier that could uniquely distinguish that command type
     */
    String getCmdIdentifier();
}
