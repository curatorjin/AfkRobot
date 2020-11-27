package io.github.curatorjin.afk.utils;


import org.apache.commons.cli.*;

public final class AfkArgsParser {
    public static boolean parseArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("l", "log", true, "log configuration");
        options.addOption("t", "task", true, "task name");
        options.addOption("T", "timeout", true, "timeout");

        try {
            CommandLine commandLine = parser.parse(options, args);
            AfkConfig.setLogging(commandLine.hasOption('l'));
            if (!commandLine.hasOption('t')) {
                System.out.println("请输入任务名");
                return false;
            }
            String timeoutStr = commandLine.hasOption('T') ? commandLine.getOptionValue('T') : "0";
            AfkConfig.setTimeout(Integer.parseInt(timeoutStr));
            AfkConfig.setTaskName(commandLine.getOptionValue('t'));
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
