package com.udd.udd.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }

    public Date expiresDate(int ms) {

        return new Date(this.now().getTime() + ms);
    }
}