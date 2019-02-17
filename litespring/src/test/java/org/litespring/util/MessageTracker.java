package org.litespring.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTracker {

    public static final String START_MSG    = "start transaction";
    public static final String COMMIT_MSG   = "commit transaction";
    public static final String ROLLBACK_MSG = "rollback transaction";
    public static final String PLACE_ORDER  = "place order";

    private static List<String> MESSAGES = new ArrayList<String>();

    public static void addMsg(String msg) {
        MESSAGES.add(msg);
    }

    public static void clearMsgs() {
        MESSAGES.clear();
    }

    public static List<String> getMsgs() {
        return MESSAGES;
    }

    public static void log(String msg) {
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }
}
