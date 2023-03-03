package ru.bardinpetr.itmo.lab5.server.filedb;

import java.time.LocalDateTime;

/**
 * Information of current file database
 *
 * @param filePath     full path to db file
 * @param creationDate db file creation date
 */
public record FileDBInfo(String filePath, LocalDateTime creationDate) {
}
