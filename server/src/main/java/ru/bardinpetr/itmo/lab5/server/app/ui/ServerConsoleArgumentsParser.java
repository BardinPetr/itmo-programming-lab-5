package ru.bardinpetr.itmo.lab5.server.app.ui;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import ru.bardinpetr.itmo.lab5.common.ui.AbstractConsoleArgumentsParser;

@Slf4j
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
                        .longOpt("dburl")
                        .hasArg(true)
                        .desc("database connection url")
                        .build()
        );


        var dbuser = Option.builder()
                .option("u")
                .longOpt("dbuser")
                .hasArg(true)
                .desc("database username")
                .build();
        options.addOption(dbuser);


        var dbpass = Option.builder()
                .option("p")
                .longOpt("dbpass")
                .hasArg(true)
                .desc("database password")
                .build();
        options.addOption(dbpass);


        var port = Option.builder()
                .longOpt("port")
                .hasArg(true)
                .desc("UDP port")
                .build();
        options.addOption(port);

        return options;
    }

    public String getDatabaseUrl() {
        return getOptions().getOptionValue("dburl", "jdbc:postgresql://82.209.92.89:5000/workers");
    }

    public String getUsername() {
        return getOptions().getOptionValue("dbuser", "s367079");
    }

    public String getPassword() {
        return getOptions().getOptionValue("dbpass", "aKNKcUmScdpvwhYu");
    }

    public Integer getPort() {
        try {
            var port = Integer.parseInt(getOptions().getOptionValue("port", "5001"));
            if (port < 1 || port > 65535)
                throw new Exception();
            return port;
        } catch (Exception ignored) {
            log.error("Invalid port");
            System.exit(1);
        }
        return null;
    }

    public Boolean doBootstrap() {
        return getOptions().hasOption("bootstrap");
    }
}
