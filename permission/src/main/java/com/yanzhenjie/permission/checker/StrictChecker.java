/*
 * Copyright © Zhenjie Yan
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
package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;

import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * Created by Zhenjie Yan on 2018/1/7.
 */
public final class StrictChecker implements PermissionChecker {

    public StrictChecker() {
    }

    @Override
    public boolean hasPermission(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return true;

        for (String permission : permissions) {
            if (!hasPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasPermission(Context context, List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return true;

        for (String permission : permissions) {
            if (!hasPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPermission(Context context, String permission) {
        try {
            switch (permission) {
                case Permission.CAMERA:
                    return checkCamera(context);
                case Permission.ACCESS_COARSE_LOCATION:
                    return checkCoarseLocation(context);
                case Permission.ACCESS_FINE_LOCATION:
                    return checkFineLocation(context);
                case Permission.RECORD_AUDIO:
                    return checkRecordAudio(context);
                case Permission.READ_PHONE_STATE:
                    return checkReadPhoneState(context);
                case Permission.CALL_PHONE:
                    return true;
                case Permission.READ_EXTERNAL_STORAGE:
                    return checkReadStorage();
                case Permission.WRITE_EXTERNAL_STORAGE:
                    return checkWriteStorage(context);
            }
        } catch (Throwable e) {
            return false;
        }
        return true;
    }


    private static boolean checkCamera(Context context) throws Throwable {
        PermissionTest test = new CameraTest(context);
        return test.test();
    }


    private static boolean checkCoarseLocation(Context context) throws Throwable {
        PermissionTest test = new LocationCoarseTest(context);
        return test.test();
    }

    private static boolean checkFineLocation(Context context) throws Throwable {
        PermissionTest test = new LocationFineTest(context);
        return test.test();
    }

    private static boolean checkRecordAudio(Context context) throws Throwable {
        PermissionTest test = new RecordAudioTest(context);
        return test.test();
    }

    private static boolean checkReadPhoneState(Context context) throws Throwable {
        PermissionTest test = new PhoneStateReadTest(context);
        return test.test();
    }

    private static boolean checkReadStorage() throws Throwable {
        PermissionTest test = new StorageReadTest();
        return test.test();
    }

    private static boolean checkWriteStorage(Context context) throws Throwable {
        PermissionTest test = new StorageWriteTest(context);
        return test.test();
    }
}