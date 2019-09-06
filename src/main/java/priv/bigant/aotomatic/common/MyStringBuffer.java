package priv.bigant.aotomatic.common;

import priv.bigant.aotomatic.StringUtils;

public class MyStringBuffer {
    public static final String SEMICOLON = ";";
    StringBuffer ALLATORIxDEMO = new StringBuffer();
    public static final String COMMA = ",";

    public MyStringBuffer appendM(String... a) {
        for (String s : a) {
            this.ALLATORIxDEMO.append(s);
        }

        this.ALLATORIxDEMO.append(";\n");
        return this;
    }

    public static String addSingleQuote(String a) {
        return "'" + a + "'";
    }

    /*public void printInfo(String a) {
        this.ALLATORIxDEMO.append(ServiceException.ALLATORIxDEMO("\f{,v:oqm*vqr-k1v3lw ") + a + ListUtils.ALLATORIxDEMO("0j)") + ServiceException.ALLATORIxDEMO("\b"));
    }*/

    public MyStringBuffer append(String... a) {
        for (String s : a) {
            this.ALLATORIxDEMO.append(s);
        }

        return this;
    }

    public boolean isEmpty() {
        boolean var1 = false;
        if (StringUtils.isEmpty(this.ALLATORIxDEMO.toString())) {
            var1 = true;
        }

        return var1;
    }

    public MyStringBuffer appendK(String... a) {
        for (String s : a) {
            this.ALLATORIxDEMO.append(s);
        }

        this.ALLATORIxDEMO.append("\n");
        return this;
    }

    public MyStringBuffer() {
    }

    public MyStringBuffer appendH(String... a) {
        for (String s : a) {
            this.ALLATORIxDEMO.append(s);
        }
        this.ALLATORIxDEMO.append(");\n");
        return this;
    }

    public static MyStringBuffer newInstance() {
        return new MyStringBuffer();
    }

    public static String addQuote(String a) {
        return '"' + a + '"';
    }

    public void insertBlankLine() {
        this.ALLATORIxDEMO.append("\n");
    }

    public String toString() {
        return this.ALLATORIxDEMO.toString();
    }
}
