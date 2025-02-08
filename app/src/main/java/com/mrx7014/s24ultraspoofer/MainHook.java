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

import android.os.Build;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {        
        XposedBridge.log("Colored_FP: Hooking into: " + lpparam.packageName);
         if (!lpparam.packageName.equals("com.android.systemui")) {
                 return;}
         
            // Target class and method
             String targetClass = "com.coloros.systemui.keyguard.onscreenfingerprint.OnScreenFingerprintOpticalAnimCtrl";
             String targetMethod = "updateFpIconColor";

             // Hook the method
             XposedHelpers.findAndHookMethod(targetClass, lpparam.classLoader, targetMethod, new XC_MethodReplacement() {
                 @Override
                 protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                       // Do nothing when the method is called
                       return null;
                     Log.d("Colored_FP", "updateFpIconColor() hooked and bypassed!");
                     
      }
      }
      }                                       
      }                                       
