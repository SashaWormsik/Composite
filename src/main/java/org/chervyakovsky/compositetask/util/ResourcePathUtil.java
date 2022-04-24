package org.chervyakovsky.compositetask.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.compositetask.exception.CustomException;

import java.net.URL;

public class ResourcePathUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static String getResourcePath(String resourceName) throws CustomException {
        final int startPosition = 6;
        ClassLoader loader = ResourcePathUtil.class.getClassLoader();
        URL resource = loader.getResource(resourceName);
        if (resource == null) {
            LOGGER.info("Resource " + resourceName + " is not found");
            throw new CustomException("Resource " + resourceName + " is not found");
        }
        return resource.toString().substring(startPosition);
    }
}
