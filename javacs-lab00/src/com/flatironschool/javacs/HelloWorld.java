package com.flatironschool.javacs;

public class HelloWorld {
    public static Double getVersion() {
        // I won't work!

        String version = System.getProperty("java.specification.version");
        Double out = Double.parseDouble(version);
        return out;
    }

    public static void main(String[] args) {
	    // you can test the output of getVersion() here
        Double version = getVersion();

        System.out.println(version);
    }
}
