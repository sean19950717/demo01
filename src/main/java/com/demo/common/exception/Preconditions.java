package com.demo.common.exception;

/**
 * Created by lin on 17-6-15.
 */
public final class Preconditions {

    public static void checkArgument(boolean expression,ErrorKeyEnum ErrorKeyEnum) {
        if(!expression) {
            throw new CException(ErrorKeyEnum);
        }
    }

    public static <T> T checkNotNull(T reference, ErrorKeyEnum ErrorKeyEnum) {
        if(reference == null) {
            throw new CException(ErrorKeyEnum);
        } else {
            return reference;
        }
    }

    public static void checkState(boolean expression,ErrorKeyEnum ErrorKeyEnum) {
        if(!expression) {
            throw new CException(ErrorKeyEnum);
        }
    }

    public static void checkTrue(boolean expression,ErrorKeyEnum ErrorKeyEnum) {
        if(!expression) {
            throw new CException(ErrorKeyEnum);
        }
    }

    public static void checkFalse(boolean expression,ErrorKeyEnum ErrorKeyEnum) {
        if(expression) {
            throw new CException(ErrorKeyEnum);
        }
    }

}
