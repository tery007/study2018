package org.litespring.service.v2;

import org.litespring.dao.v2.DebitDao;
import org.litespring.dao.v2.GoodDao;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/28 下午10:46
 **/
public class GoodService {

    private GoodDao  goodDao;
    private DebitDao debitDao;
    private String   owner;
    private int      version;

    public GoodDao getGoodDao() {
        return goodDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public DebitDao getDebitDao() {
        return debitDao;
    }

    public void setDebitDao(DebitDao debitDao) {
        this.debitDao = debitDao;
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
