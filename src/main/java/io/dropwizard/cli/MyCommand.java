package io.dropwizard.cli;

import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class MyCommand extends Command {

    public MyCommand() {
        super("hello", "Prints a greeting!!");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("The user of the program");
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) {
        System.out.println("Hello " + namespace.getString("user"));
    }
}
