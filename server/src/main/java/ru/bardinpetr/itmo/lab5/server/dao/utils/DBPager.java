package ru.bardinpetr.itmo.lab5.server.dao.utils;

import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;

import java.util.List;
import java.util.stream.Stream;

/**
 * Pagination adapter for db methods
 *
 * @param <T> collection item
 */
public class DBPager<T> {
    public List<T> paginate(Stream<T> input, PagingAPICommand command) {
        return input.skip(command.offset).limit(command.count).toList();
    }
}
