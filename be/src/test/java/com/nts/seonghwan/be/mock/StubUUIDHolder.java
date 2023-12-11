package com.nts.seonghwan.be.mock;

import com.nts.seonghwan.be.common.service.UUIDHolder;

public class StubUUIDHolder implements UUIDHolder {
    private String assigned;

    @Override
    public String uuid() {
        return assigned;
    }

    public void setUUID(String uuid){
        this.assigned = uuid;
    }
}
