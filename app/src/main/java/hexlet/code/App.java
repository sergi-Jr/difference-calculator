package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Parameters(description = "Path to original file",
                paramLabel = "originalFilePath")
    private String originalFilePath;
    @Parameters(description = "Path to file to compare with original source",
                paramLabel = "comparableFilePath")
    private String comparableFilePath;
    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format")
    private String format = "stylish";
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public String call() throws Exception {
        String callResult = Differ.generate(originalFilePath, comparableFilePath);
        System.out.println(callResult);
        return callResult;
    }
}
