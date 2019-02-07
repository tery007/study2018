package org.litespring.service.v4;

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.stereotype.Component;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/5
 **/
@Component("goodService")
public class GoodService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao    itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
