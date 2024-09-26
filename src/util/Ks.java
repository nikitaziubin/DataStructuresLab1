/** @author Eimutis Karčiauskas, KTU IF Department of Software Engineering, 23 09 2014
 *
 * The class is designed for convenient retrieval of data from the keyboard and
 * efficient presentation of results to sout and serr streams.
 * All methods are static and dedicated to one data type.
 *************************************************************************** */

package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Ks {

    private static final BufferedReader keyboard
            = new BufferedReader(new InputStreamReader(System.in));
    private static String dataFolderKTU = "Data";

    static public String giveString(String prompt) {
        Ks.ou(prompt);
        try {
            return keyboard.readLine();
        } catch (IOException e) {
            Ks.ern("Keyboard input not working, program stopped working");
            System.exit(0);
        }
        return "";
    }

    static public long giveLong(String prompt) {
        while (true) {
            String s = giveString(prompt);
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                Ks.ern("Incorrect number format, please repeat");
            }
        }
    }

    static public long giveLong(String prompt, long bound1, long bound2) {
        while (true) {
            long a = giveLong(prompt + " between boundaries [" + bound1 + ":" + bound2 + "]=");
            if (a < bound1) {
                Ks.ern("Number less than allowed, please repeat");
            } else if (a > bound2) {
                Ks.ern("Number greater than allowed, please repeat");
            } else {
                return a;
            }
        }
    }

    static public int giveInt(String prompt) {
        while (true) {
            long a = giveLong(prompt);
            if (a < Integer.MIN_VALUE) {
                Ks.ern("Number less than " + Integer.MIN_VALUE
                        + ", please repeat");
            } else if (a > Integer.MAX_VALUE) {
                Ks.ern("Number greater than " + Integer.MAX_VALUE
                        + ", please repeat");
            } else {
                return (int) a;
            }
        }
    }

    static public int giveInt(String prompt, int bound1, int bound2) {
        return (int) giveLong(prompt, bound1, bound2);
    }

    static public double giveDouble(String prompt) {
        while (true) {
            String s = giveString(prompt);
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                if (s.contains(",")) {
                    Ks.ern("Use a full stop instead of a comma, please repeat");

                } else {
                    Ks.ern("Incorrect number format, please repeat");

                }
            }
        }
    }

    static public double giveDouble(String prompt, double bound1, double bound2) {
        while (true) {
            double a = giveDouble(prompt + " between boundaries [" + bound1 + ":" + bound2 + "]=");
            if (a < bound1) {
                Ks.ern("Number less than allowed, please repeat");
            } else if (a > bound2) {
                Ks.ern("Number greater than allowed, please repeat");
            } else {
                return a;
            }
        }
    }

    static public String giveFileName() {
        File dir = new File(dataFolderKTU);
        dir.mkdir();
        oun("Files available to you " + Arrays.toString(dir.list()));
                String fn = giveString("Specify the name of the selected data file: ");
        return (fn);
    }

    static public String getDataFolder() {
        return dataFolderKTU;
    }

    static public void setDataFolder(String folderName) {
        dataFolderKTU = folderName;
    }

    private static final PrintStream sout = System.out;
    private static final PrintStream serr = System.out;
    private static int lineNr;
    private static int errorNr;
    private static final boolean formatStartOfLine = true;

    static public void ou(Object obj) {
        if (formatStartOfLine) {
            sout.printf("%2d| %s", ++lineNr, obj);
        } else {
            sout.println(obj);
        }
    }

    static public void oun(Object obj) {
        if (formatStartOfLine) {
            sout.printf("%2d| %s\n", ++lineNr, obj);
        } else {
            sout.println(obj);
        }
    }

    static public void ouf(String format, Object... args) {
        sout.printf(format, args);
    }

    static public void er(Object obj) {
        serr.printf("***Error %d: %s", ++errorNr, obj);
    }

    static public void ern(Object obj) {
        serr.printf("***Error %d: %s\n", ++errorNr, obj);
    }

    static public void erf(String format, Object... args) {
        serr.printf(format, args);
    }
}
