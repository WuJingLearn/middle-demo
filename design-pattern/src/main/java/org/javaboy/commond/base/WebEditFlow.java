package org.javaboy.commond.base;

import java.util.ArrayList;
import java.util.List;

public class WebEditFlow {
    private final List<Command> commands;
    public WebEditFlow() {
        commands = new ArrayList<>();
    }
    public void setCommand(Command command) {
        commands.add(command);
    }
    public void run() {
        commands.forEach(Command::execute);
    }
}