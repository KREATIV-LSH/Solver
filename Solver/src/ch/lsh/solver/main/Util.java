package ch.lsh.solver.main;

public class Util {
    
    public static StringBuilder mainSB;

    public static void setup(int sbCapacity) {
        mainSB = new StringBuilder(sbCapacity);
    }

    public static void garbageCollector() {
        System.gc();
        System.runFinalization();
    }

    public static void startPrint() {
        mainSB.setLength(0);
    }

    public static void endPrint() {
        System.out.println(mainSB.toString());
    }

}
