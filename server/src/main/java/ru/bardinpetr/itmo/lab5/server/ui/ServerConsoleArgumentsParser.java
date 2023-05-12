package ru.bardinpetr.itmo.lab5.server.ui;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import ru.bardinpetr.itmo.lab5.common.ui.AbstractConsoleArgumentsParser;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class ServerConsoleArgumentsParser extends AbstractConsoleArgumentsParser {
    public ServerConsoleArgumentsParser(String[] args) {
        super(args);
    }

    @Override
    protected Options createOptions() {
        var options = new Options();
        options.addOption(
                Option.builder()
                        .option("b")
                        .longOpt("bootstrap")
                        .desc("clear database and recreate tables")
                        .build()
        );
        options.addOption(
                Option.builder()
                        .option("d")
                        .longOpt("db")
                        .hasArg(true)
                        .desc("database connection url")
                        .build()
        );
        options.addOption(
                Option.builder()
                        .option("u")
                        .longOpt("dbuser")
                        .hasArg(true)
                        .desc("database username")
                        .build()
        );
        options.addOption(
                Option.builder()
                        .option("p")
                        .longOpt("dbpass")
                        .hasArg(true)
                        .desc("database password")
                        .build()
        );

        options.addOption(
                Option.builder()
                        .option("p")
                        .longOpt("port")
                        .hasArg(true)
                        .desc("UDP port")
                        .build()
        );

        Option output = new Option("p", "port", true, "UDP port");
        output.setRequired(true);
        options.addOption(output);
        return options;
    }

    public Path getDatabasePath() {
        try {
            return Path.of(getOptions().getOptionValue("database"));
        } catch (InvalidPathException ignored) {
            System.err.println("invalid db path");
            System.exit(1);
        }
        return null;
    }

    public Integer getPort() {
        try {
            return Integer.parseInt(getOptions().getOptionValue("port", "5000"));
        } catch (NumberFormatException ignored) {
            System.err.println("invalid port");
            System.exit(1);
        }
        return null;
    }

    public Boolean doBootstrap() {
        return getOptions().hasOption("bootstrap");
    }
}
