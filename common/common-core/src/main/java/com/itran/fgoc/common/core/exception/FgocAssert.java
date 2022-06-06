package com.itran.fgoc.common.core.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.itran.fgoc.common.core.api.ResultCode;

import java.util.Collection;

public class FgocAssert {
    protected FgocAssert() {
    }

    public static void assertTrue(boolean condition, ResultCode resultCode) {
        if (!condition) {
            fail(resultCode);
        }
    }

    public static void assertFalse(boolean condition, ResultCode resultCode) {
        assertTrue(!condition, resultCode);
    }

    public static void fail(ResultCode resultCode) {
        throw new ApiException(resultCode);
    }

    public static void assertEquals(Object expected, Object actual, ResultCode resultCode) {
        if (!equalsRegardingNull(expected, actual)) {
            throw new ApiException(resultCode);
        }
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        } else {
            return isEquals(expected, actual);
        }
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    public static void assertNotEquals(Object unexpected, Object actual, ResultCode resultCode) {
        if (equalsRegardingNull(unexpected, actual)) {
            fail(resultCode);
        }
    }

    public static void assertNotEquals(long unexpected, long actual, ResultCode resultCode) {
        if (unexpected == actual) {
            fail(resultCode);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, double delta, ResultCode resultCode) {
        if (!doubleIsDifferent(unexpected, actual, delta)) {
            fail(resultCode);
        }

    }

    public static void assertEquals(double expected, double actual, double delta, ResultCode resultCode) {
        if (doubleIsDifferent(expected, actual, delta)) {
            fail(resultCode);
        }

    }

    public static void assertEquals(float expected, float actual, float delta, ResultCode resultCode) {
        if (floatIsDifferent(expected, actual, delta)) {
            fail(resultCode);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, float delta, ResultCode resultCode) {
        if (!floatIsDifferent(unexpected, actual, delta)) {
            fail(resultCode);
        }
    }

    public static <T> void  assertEmpty(Collection<T> collections, ResultCode resultCode) {
        if (!CollUtil.isEmpty(collections)) {
            fail(resultCode);
        }
    }

    public static <T> void  assertNotEmpty(Collection<T> collections, ResultCode resultCode) {
        if (CollUtil.isEmpty(collections)) {
            fail(resultCode);
        }
    }

    public static <T> void  assertBlank(String str, ResultCode resultCode) {
        if (!StrUtil.isBlank(str)) {
            fail(resultCode);
        }
    }

    public static <T> void  assertNotBlank(String str, ResultCode resultCode) {
        if (StrUtil.isBlank(str)) {
            fail(resultCode);
        }
    }

    private static boolean doubleIsDifferent(double d1, double d2, double delta) {
        if (Double.compare(d1, d2) == 0) {
            return false;
        } else {
            return !(Math.abs(d1 - d2) <= delta);
        }
    }

    private static boolean floatIsDifferent(float f1, float f2, float delta) {
        if (Float.compare(f1, f2) == 0) {
            return false;
        } else {
            return !(Math.abs(f1 - f2) <= delta);
        }
    }

    public static void assertEquals(long expected, long actual, ResultCode resultCode) {
        if (expected != actual) {
            fail(resultCode);
        }

    }

    public static void assertNotNull(Object object, ResultCode resultCode) {
        assertTrue(object != null, resultCode);
    }

    public static void assertNull(Object object, ResultCode resultCode) {
        if (object != null) {
            fail(resultCode);
        }
    }

    public static void assertSame(Object expected, Object actual, ResultCode resultCode) {
        if (expected != actual) {
            fail(resultCode);
        }
    }

    public static void assertNotSame(Object unexpected, Object actual, ResultCode resultCode) {
        if (unexpected == actual) {
            fail(resultCode);
        }
    }
}
