package com.zhy.service.v4;

import com.zhy.beans.factory.annotation.AutoWired;
import com.zhy.dao.v3.AccountDao;
import com.zhy.dao.v3.ItemDao;
import com.zhy.stereotype.Component;

@Component(value = "petStore")
public class PetStoreService {
    @AutoWired
    private AccountDao accountDao;

    @AutoWired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
