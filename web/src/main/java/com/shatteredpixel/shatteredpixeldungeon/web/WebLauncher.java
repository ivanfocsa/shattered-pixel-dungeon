/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2025 Evan Debenham
 *
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.web;

import com.badlogic.gdx.Files;
import com.github.xpenatan.gdx.teavm.backends.web.WebApplication;
import com.github.xpenatan.gdx.teavm.backends.web.WebApplicationConfiguration;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.watabou.noosa.Game;
import com.watabou.utils.FileUtils;

public class WebLauncher {

	public static void main(String[] args) {
		Game.version = WebVersion.NAME;
		Game.versionCode = WebVersion.CODE;

		//saves go to the browser's local storage (IndexedDB)
		FileUtils.setDefaultFileProperties(Files.FileType.Local, "");

		WebApplicationConfiguration config = new WebApplicationConfiguration("canvas");
		config.width = 0;   //fill the browser window
		config.height = 0;
		config.useGL30 = false;
		config.usePhysicalPixels = true; //sharp rendering on high density (mobile) screens
		config.showDownloadLogs = false;
		config.storagePrefix = "spd";
		config.preloadListener = assetLoader -> assetLoader.loadScript("freetype.js");

		new WebApplication(new ShatteredPixelDungeon(new WebPlatformSupport()), config);
	}
}
