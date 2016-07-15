package org.osmdroid.tileprovider.tilesource;

import android.util.Log;

import org.osmdroid.tileprovider.MapTile;

/**
 * An implementation of {@link org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase}
 */
public class XYTileSource extends OnlineTileSourceBase {

	private static final String TAG = XYTileSource.class.getSimpleName();
	public XYTileSource(final String aName, final int aZoomMinLevel,
			final int aZoomMaxLevel, final int aTileSizePixels, final String aImageFilenameEnding,
			final String[] aBaseUrl) {
		super(aName, aZoomMinLevel, aZoomMaxLevel, aTileSizePixels,
				aImageFilenameEnding, aBaseUrl);
	}

	@Override
	public String getTileURLString(final MapTile aTile) {
		//return getBaseUrl() + aTile.getZoomLevel() + "/" + aTile.getX() + "/" + aTile.getY()+ mImageFilenameEnding;
		Log.d(TAG, "url=" + getBaseUrl());
		Log.d(TAG, "title=" + getBaseUrl() + "&x=" + aTile.getX() + "&y=" + aTile.getY() + "&z=" + aTile.getZoomLevel());

		return getBaseUrl() + "&x=" + aTile.getX() + "&y=" + aTile.getY() + "&z=" + aTile.getZoomLevel();
		//return getBaseUrl()+""+getTileNumber(aTile.getX(),aTile.getY(),aTile.getZoomLevel());
	}

	public static String getTileNumber(final double lat, final double lon, final int zoom) {
		int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
		int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
		if (xtile < 0)
			xtile=0;
		if (xtile >= (1<<zoom))
			xtile=((1<<zoom)-1);
		if (ytile < 0)
			ytile=0;
		if (ytile >= (1<<zoom))
			ytile=((1<<zoom)-1);

		return "&x=" + xtile + "&y=" + ytile + "&z=" + zoom;
	}


}
