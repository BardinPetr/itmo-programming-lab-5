package ru.bardinpetr.itmo.lab5.network;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;

import java.io.IOException;


@Slf4j
public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        SetupJUL.loadProperties(ServerMain.class);
    }
}
