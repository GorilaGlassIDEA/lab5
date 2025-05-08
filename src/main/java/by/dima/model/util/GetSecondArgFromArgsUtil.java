package by.dima.model.util;

public class GetSecondArgFromArgsUtil {
    public static String getSecondArg(String stringArgs) {
        String[] args = stringArgs.split(" ");
        if (args.length > 1) {
            if (args.length > 2) {
                System.err.println("Incorrect input!");
            } else {
                return args[1];
            }
        }
        return "";
    }

    public static String getFirstArg(String stringArgs) {
        String[] args = stringArgs.split(" ");
        if (args.length > 0) {
            return args[0];
        } else {
            return "";
        }
    }
}
