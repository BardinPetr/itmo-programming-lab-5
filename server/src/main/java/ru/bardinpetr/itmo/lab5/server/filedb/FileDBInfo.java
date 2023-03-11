package ru.bardinpetr.itmo.lab5.server.filedb;

import java.time.ZonedDateTime;

/**
 * Information of current file database
 *
 * @param filePath     full path to db file
 * @param creationDate db file creation date
 */
public record FileDBInfo(String filePath, ZonedDateTime creationDate) {
}
