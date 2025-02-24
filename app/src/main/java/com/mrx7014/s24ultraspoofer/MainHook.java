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
        if (!lpparam.packageName.equals("com.android.incallui")) {
            return;
        }

        // Log that we're hooking into the target package
        XposedBridge.log("Blur Background: Hooking into: " + lpparam.packageName);

        // Target class and method
        String targetClass = "com.android.incallui.OppoInCallActivity";
        String targetMethod = "showBackground";
        

        // Hook the method and replace its implementation with an empty body
        XposedHelpers.findAndHookMethod(targetClass, lpparam.classLoader, targetMethod, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                
        if (Log.sDebug) {
            Log.d(LOG_TAG, "isAniamtion = " + z);
        }
        UiModeManager uiModeManager = this.mUiModeManager;
        if (uiModeManager != null && uiModeManager.getNightMode() == 3) {
            if (Log.sDebug) {
                Log.d(LOG_TAG, "NightMode is true");
            }
            this.mBackground.setBackgroundColor(getResources().getColor(R.color.incall_white_color));
        } else {
            this.mUseGaussianBlurBackground = false;
            try {
                WeakReference weakReference = new WeakReference(OppoGaussianBlurUtils.getInstance().getBackgroundBitmap());
                if (weakReference.get() != null && !((Bitmap) weakReference.get()).isRecycled()) {
                    if (Log.sDebug) {
                        Log.d(LOG_TAG, "wallPaper = " + weakReference.get());
                    }
                    this.mBackground.setBackground(new BitmapDrawable((Bitmap) weakReference.get()));
                    if (OppoGaussianBlurUtils.getInstance().isBackgrounBitMapUsed()) {
                        this.mUseGaussianBlurBackground = true;
                    }
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "exception = " + e);
            }
            if (!this.mUseGaussianBlurBackground) {
                try {
                    this.mBackground.setBackgroundColor(c.a(getApplicationContext(), C12, C12));
                } catch (Resources.NotFoundException e2) {
                    Log.d(LOG_TAG, "NotFoundException: " + e2.toString());
                } catch (Exception e3) {
                    Log.d(LOG_TAG, "Exception: " + e3.toString());
                }
            }
        }
        if (z) {
            OppoAnimationUtils.Fade.show(this.mBackground);
        }
    }

        });
    }
}
