package org.litespring.tx;

import org.litespring.util.MessageTracker;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/15
 **/
public class TransactionManager {

    public void start() {
        MessageTracker.log(MessageTracker.START_MSG);
    }

    public void commit() {
        MessageTracker.log(MessageTracker.COMMIT_MSG);
    }

    public void rollback() {
        MessageTracker.log(MessageTracker.ROLLBACK_MSG);
    }


}
