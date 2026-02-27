package org.kh.monopolyidentityservice.exception;

import org.slf4j.MDC;

public final class TraceId {

    private TraceId(){}

    public static String current(){
        return MDC.get("traceId");
    }
}
