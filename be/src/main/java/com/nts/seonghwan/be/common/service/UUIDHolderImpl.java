package com.nts.seonghwan.be.common.service;

import java.util.UUID;

public class UUIDHolderImpl implements UUIDHolder{

    @Override
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
