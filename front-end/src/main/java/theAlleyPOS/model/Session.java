package theAlleyPOS.model;

/**
 * @author Sebastian Oberg
 */
public class Session {
    private static boolean isManager = false;

    /**
     * This is the getter for the isManager check
     * @return true or false
     */
    public static boolean isManager() {
        return isManager;
    }

    /**
     * This is the setter for the isManager check
     * @param manager
     */
    public static void setManager(boolean manager)
    {
        isManager = manager;
    }
}
