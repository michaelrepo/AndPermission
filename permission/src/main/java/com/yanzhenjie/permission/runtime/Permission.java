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
package com.yanzhenjie.permission.runtime;

import android.content.Context;
import android.os.Build;

import com.yanzhenjie.permission.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Permissions.</p>
 * Created by Zhenjie Yan on 2017/8/4.
 */
public class Permission {


    public static final String CAMERA = "android.permission.CAMERA";

    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";

    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";

    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";







    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static final class Group {


        public static final String[] CAMERA = new String[]{Permission.CAMERA};


        public static final String[] LOCATION = new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION,
            Permission.ACCESS_BACKGROUND_LOCATION};

        public static final String[] MICROPHONE = new String[]{Permission.RECORD_AUDIO};


        public static final String[] STORAGE = new String[]{Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String... permissions) {
        return transformText(context, Arrays.asList(permissions));
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        return transformText(context, permissionList);
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, List<String> permissions) {
        List<String> textList = new ArrayList<>();
        for (String permission : permissions) {
            switch (permission) {

                case Permission.CAMERA: {
                    String message = context.getString(R.string.permission_name_camera);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }

                case Permission.ACCESS_FINE_LOCATION:
                case Permission.ACCESS_COARSE_LOCATION: {
                    String message = context.getString(R.string.permission_name_location);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case Permission.RECORD_AUDIO: {
                    String message = context.getString(R.string.permission_name_microphone);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
                case Permission.READ_PHONE_STATE:
                case Permission.CALL_PHONE:

                case Permission.READ_EXTERNAL_STORAGE:
                case Permission.WRITE_EXTERNAL_STORAGE: {
                    String message = context.getString(R.string.permission_name_storage);
                    if (!textList.contains(message)) {
                        textList.add(message);
                    }
                    break;
                }
            }
        }
        return textList;
    }

}