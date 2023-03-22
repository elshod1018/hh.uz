package uz.hh.utils;

import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class BaseUtils {
    public static String generateUniqueName(@NonNull String fileName) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
    }
}
