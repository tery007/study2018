package org.litespring.service.v5;

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v5.AccountDao;
import org.litespring.dao.v5.ItemDao;
import org.litespring.stereotype.Component;
import org.litespring.util.MessageTracker;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/15
 **/
@Component(value = "goodService")
public class GoodService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao    itemDao;

    public GoodService() {

    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void placeOrder(){
        MessageTracker.log(MessageTracker.PLACE_ORDER);

    }

    public void placeOrderWithException(){
        throw new NullPointerException();
    }
}
