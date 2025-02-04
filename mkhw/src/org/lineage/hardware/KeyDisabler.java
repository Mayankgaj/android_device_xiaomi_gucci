/*
 * Copyright (C) 2016 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineage.hardware;

import org.lineage.internal.util.FileUtils;

/*
 * Disable capacitive keys
 *
 * This is intended for use on devices in which the capacitive keys
 * can be fully disabled for replacement with a soft navbar. You
 * really should not be using this on a device with mechanical or
 * otherwise visible-when-inactive keys
 */

public class KeyDisabler {

    private static String CONTROL_PATH = "/sys/bus/i2c/drivers/atmel_mxt_ts/5-004a/keys_off";

    public static boolean isSupported() {
        return FileUtils.isFileReadable(CONTROL_PATH) &&
            FileUtils.isFileWritable(CONTROL_PATH);
    }

    public static boolean isActive() {
        return FileUtils.readOneLine(CONTROL_PATH).equals("0");
    }

    public static boolean setActive(boolean state) {
        return FileUtils.writeLine(CONTROL_PATH, (state ? "1" : "0"));
    }

}
