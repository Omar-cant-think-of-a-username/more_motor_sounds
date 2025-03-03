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
        // Check if the target package is being loaded
        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }

        // Log that we're hooking into the target package
        XposedBridge.log("Blur Background: Hooking into: " + lpparam.packageName);

        // Target class and method
        String targetClass = "com.coloros.systemui.navbar.gesture.sidegesture.ColorSideGestureNavView";
        String targetMethod = "onDraw";
        

        // Hook the method and replace its implementation with an empty body
        XposedHelpers.findAndHookMethod(targetClass, lpparam.classLoader, targetMethod, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                
        if (this.mGestureState == 2 || this.mBackIconProcessing) {
            canvas.drawBitmap(this.mBackIcon, this.mBackIconMatrix, this.mIconPaint);
        }
        if (this.mAppIcon == null) {
            LogUtil.normal(LogUtil.TAG_NAVBAR, ColorSideGestureConfiguration.TAG, "onDraw, mAppIcon is null");
        } else if (this.mGestureState == 3 || this.mAppIconProcessing) {
            canvas.drawBitmap(this.mAppIcon, this.mAppIconMatrix, this.mIconPaint);
        }
    }


        });
    }
}
