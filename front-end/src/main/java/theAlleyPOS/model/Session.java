package theAlleyPOS.model;

public class Session {
    private static boolean isManager = false;

    public static boolean isManager() {
        return isManager;
    }

    public static void setManager(boolean manager)
    {
        isManager = manager;
    }
}
