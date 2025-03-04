/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mrx7014.s24ultraspoofer;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }

        XposedBridge.log("Blur Background: Hooking into: " + lpparam.packageName);

        String targetClass = "com.coloros.systemui.navbar.gesture.sidegesture.ColorSideGestureNavView";
        String targetMethod = "onDraw";

        XposedHelpers.findAndHookMethod(targetClass, lpparam.classLoader, targetMethod, 
            android.graphics.Canvas.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    Object thisObject = param.thisObject;

                    int mGestureState = (int) XposedHelpers.getObjectField(thisObject, "mGestureState");
                    boolean mBackIconProcessing = (boolean) XposedHelpers.getObjectField(thisObject, "mBackIconProcessing");
                    Object mBackIcon = XposedHelpers.getObjectField(thisObject, "mBackIcon");
                    Object mBackIconMatrix = XposedHelpers.getObjectField(thisObject, "mBackIconMatrix");
                    Object mIconPaint = XposedHelpers.getObjectField(thisObject, "mIconPaint");
                    Object canvas = param.args[0];

                    if (mGestureState == 2 || mBackIconProcessing) {
                        XposedHelpers.callMethod(canvas, "drawBitmap", mBackIcon, mBackIconMatrix, mIconPaint);
                    }

                    Object mAppIcon = XposedHelpers.getObjectField(thisObject, "mAppIcon");
                    boolean mAppIconProcessing = (boolean) XposedHelpers.getObjectField(thisObject, "mAppIconProcessing");

                    if (mAppIcon == null) {
                        XposedBridge.log("onDraw, mAppIcon is null");
                    } else if (mGestureState == 3 || mAppIconProcessing) {
                        Object mAppIconMatrix = XposedHelpers.getObjectField(thisObject, "mAppIconMatrix");
                        XposedHelpers.callMethod(canvas, "drawBitmap", mAppIcon, mAppIconMatrix, mIconPaint);
                    }

                    return null;
                }
            }
        );
    }
}
