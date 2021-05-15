/*
 * Copyright (C) 2016 The Pure Nexus Project
 *
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

package com.legion.settings.utils;

import static android.os.UserHandle.USER_SYSTEM;

import android.app.ActivityManager;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.widget.Toast;

import com.android.settings.R;

public class Utils {

    public static boolean isBlurSupported() {
        boolean blurSupportedSysProp = SystemProperties
            .getBoolean("ro.surface_flinger.supports_background_blur", false);
        boolean blurDisabledSysProp = SystemProperties
            .getBoolean("persist.sys.sf.disable_blurs", false);
        return blurSupportedSysProp && !blurDisabledSysProp && ActivityManager.isHighEndGfx();
    }

    public static void handleOverlays(String packagename, Boolean state, IOverlayManager mOverlayManager) {
        try {
            mOverlayManager.setEnabled(packagename, state, USER_SYSTEM);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
