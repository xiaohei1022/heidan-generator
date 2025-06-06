package com.heidan.maker.cli;

import com.heidan.maker.cli.command.ConfigCommand;
import com.heidan.maker.cli.command.GeneratorCommand;
import com.heidan.maker.cli.command.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "heidan", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;
    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GeneratorCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或者输入 --help 查看帮助信息");
    }

    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}
