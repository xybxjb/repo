package cn.deepcoding.util;

public class EnumUtil {
	public static <E extends Enum<?> & CodeBaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.code() == code)
                return e;
        }
        return null;
    }
}
