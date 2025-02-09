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
        if (!lpparam.packageName.equals("com.android.settings")) {
            return;
        }

        // Log that we're hooking into the target package
        XposedBridge.log("Colored_FP: Hooking into: " + lpparam.packageName);

        // Target class and method
        String targetClass = "com.coloros.settings.feature.othersettings.cameraeffect.CameraSoundPrefCategoryController";
        String targetMethod = "loadSoundEffects";
        

        // Hook the method and replace its implementation with an empty body
        XposedHelpers.findAndHookMethod(targetClass, lpparam.classLoader, targetMethod, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                 addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_technology), RES_PATH_TECHNOLOGY_UP, RES_PATH_TECHNOLOGY_DOWN);
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_mechanism), RES_PATH_MECHANISM_UP, RES_PATH_MECHANISM_DOWN);
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_pure_music), RES_PATH_PURE_MUSIC_UP, RES_PATH_PURE_MUSIC_DOWN);
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_chilun), "/system_ext/media/audio/ui/popup_chilun_up.mp3", "/system_ext/media/audio/ui/popup_chilun_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_jijia), "/system_ext/media/audio/ui/popup_jijia_up.mp3", "/system_ext/media/audio/ui/popup_jijia_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_cangmen), "/system_ext/media/audio/ui/popup_cangmen_up.mp3", "/system_ext/media/audio/ui/popup_cangmen_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_ironman), "/system_ext/media/audio/ui/popup_ironman_up.mp3", "/system_ext/media/audio/ui/popup_ironman_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_lightsaber), "/system_ext/media/audio/ui/popup_LighSaber_up.ogg", "/system_ext/media/audio/ui/popup_LighSaber_down.ogg");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_mario), "/system_ext/media/audio/ui/popup_mario_up.ogg", "/system_ext/media/audio/ui/popup_mario_down.ogg");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_mofa), "/system_ext/media/audio/ui/popup_mofa_up.mp3", "/system_ext/media/audio/ui/popup_mofa_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_muqin), "/system_ext/media/audio/ui/popup_muqin_up.mp3", "/system_ext/media/audio/ui/popup_muqin_down.mp3");
        addSoundPathAndLoad(this.mContext.getString(R.string.camera_3d_sound_yingyan), "/system_ext/media/audio/ui/popup_yingyan_up.mp3", "/system_ext/media/audio/ui/popup_yingyan_down.mp3");
       
            }
        });
    }
}
