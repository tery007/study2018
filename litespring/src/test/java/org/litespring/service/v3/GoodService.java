package org.litespring.service.v3;

import org.litespring.dao.v3.AccountDao;
import org.litespring.dao.v3.ItemDao;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/28 下午10:46
 **/
public class GoodService {

    private ItemDao    itemDao;
    private AccountDao accountDao;
    private String     owner;
    private int        version;

    public GoodService(AccountDao accoutDao, ItemDao itemDao, int version) {
        this.itemDao = itemDao;
        this.accountDao = accoutDao;
        this.version = version;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String getOwner() {
        return this.owner;
    }

    public int getVersion() {
        return this.version;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
