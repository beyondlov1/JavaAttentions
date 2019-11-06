package com.beyond.solrdemo.solr.component.filter;

import lombok.Data;

/**
 * 供货对象过滤
 *
 * @author beyondlov1
 * @date 2019/11/01
 */
@Data
public class StoreSubtypeFilterQueryComp extends FilterQueryComp {

    private final int storeSubtype;

    public StoreSubtypeFilterQueryComp(int storeSubtype) {
        this.storeSubtype = storeSubtype;
    }

    @Override
    protected void init() {
        if (storeSubtype != -1) {
            String expression = String.format("storetype_gp_id:0 OR store_subtypes:%s", storeSubtype);
            setExpression(expression);
        }
    }
}
