package org.osmdroid.tileprovider.tilesource;

import android.util.Log;

import org.osmdroid.tileprovider.MapTile;

import java.util.ArrayList;

public class TileSourceFactory {

    // private static final Logger logger = LoggerFactory.getLogger(TileSourceFactory.class);

    /**
     * Get the tile source with the specified name. The tile source must be one of the registered sources
     * as defined in the static list mTileSources of this class.
     *
     * @param aName the tile source name
     * @return the tile source
     * @throws IllegalArgumentException if tile source not found
     */
    public static ITileSource getTileSource(final String aName) throws IllegalArgumentException {
        Log.d("------", "getTileSource" + aName);
        for (final ITileSource tileSource : mTileSources) {
            if (tileSource.name().equals(aName)) {
                return tileSource;
            }
        }
        throw new IllegalArgumentException("No such tile source: " + aName);
    }

    public static boolean containsTileSource(final String aName) {
        for (final ITileSource tileSource : mTileSources) {
            if (tileSource.name().equals(aName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the tile source at the specified position.
     *
     * @param aOrdinal
     * @return the tile source
     * @throws IllegalArgumentException if tile source not found
     */
    @Deprecated
    public static ITileSource getTileSource(final int aOrdinal) throws IllegalArgumentException {
        for (final ITileSource tileSource : mTileSources) {
            if (tileSource.ordinal() == aOrdinal) {
                return tileSource;
            }
        }
        throw new IllegalArgumentException("No tile source at position: " + aOrdinal);
    }

    public static ArrayList<ITileSource> getTileSources() {
        return mTileSources;
    }

    public static void addTileSource(final ITileSource mTileSource) {
        mTileSources.add(mTileSource);
    }

    public static final OnlineTileSourceBase MAPNIK = new XYTileSource("交通图",
            0, 18, 512, ".png", new String[]{
            "http://mt1.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt2.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt3.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2"});

    public static final OnlineTileSourceBase CYCLEMAP = new XYTileSource("卫星图",
            0, 18, 512, ".png", new String[]{
            "http://mt1.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
            "http://mt2.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
            "http://mt3.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2"});

    public static final OnlineTileSourceBase PUBLIC_TRANSPORT = new XYTileSource(
            "OSMPublicTransport", 0, 25, 256, ".png", new String[]{
            "http://mt1.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt2.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt3.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2"});

    public static final OnlineTileSourceBase MAPQUESTOSM = new XYTileSource("MapquestOSM",
            0, 18, 640, ".jpg", new String[]{
            "http://mt1.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
            "http://mt2.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
            "http://mt3.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2"});

    public static final OnlineTileSourceBase MAPQUESTAERIAL = new XYTileSource("MapquestAerial",
            0, 18, 640, ".jpg", new String[]{
            "http://mt1.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt2.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2",
            "http://mt3.google.cn/vt/lyrs=m,traffic&hl=zh-CN&gl=cn&scale=2"});

    // From MapQuest documentation:
    // Please also note that global coverage is provided at zoom levels 0-11. Zoom Levels 12+ are
    // provided only in the United States (lower 48).
    public static final OnlineTileSourceBase MAPQUESTAERIAL_US = new XYTileSource(
            "MapquestAerialUSA", 0, 18, 256, ".jpg",
            new String[]{
                    "http://mt1.google.cn/vt/v=w2.97",
                    "http://mt2.google.cn/vt/v=w2.97",
                    "http://mt3.google.cn/vt/v=w2.97"});


    public static final OnlineTileSourceBase DEFAULT_TILE_SOURCE = MAPNIK;

    // CloudMade tile sources are not in mTileSource because they are not free
    // and therefore not provided by default.

    public static final OnlineTileSourceBase CLOUDMADESTANDARDTILES = new CloudmadeTileSource(
            "CloudMadeStandardTiles", 0, 18, 256, ".png",
            new String[]{
                    "http://mt1.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
                    "http://mt2.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
                    "http://mt3.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2"});

    // FYI - This tile source has a tileSize of "6"
    public static final OnlineTileSourceBase CLOUDMADESMALLTILES = new CloudmadeTileSource(
            "CloudMadeSmallTiles", 0, 21, 64, ".png",
            new String[]{
                    "http://mt1.google.cn/vt/v=w2.97",
                    "http://mt2.google.cn/vt/v=w2.97",
                    "http://mt3.google.cn/vt/v=w2.97"});

    // The following tile sources are overlays, not standalone map views.
    // They are therefore not in mTileSources.

    public static final OnlineTileSourceBase FIETS_OVERLAY_NL = new XYTileSource("Fiets",
            3, 18, 256, ".png",
            new String[]{
                    "http://mt1.google.cn/vt/v=w2.97",
                    "http://mt2.google.cn/vt/v=w2.97",
                    "http://mt3.google.cn/vt/v=w2.97"});

    public static final OnlineTileSourceBase BASE_OVERLAY_NL = new XYTileSource("BaseNL",
            0, 18, 256, ".png",
            new String[]{"http://overlay.openstreetmap.nl/basemap/"});

    public static final OnlineTileSourceBase ROADS_OVERLAY_NL = new XYTileSource("RoadsNL",
            0, 18, 256, ".png",
            new String[]{"http://overlay.openstreetmap.nl/roads/"});

    public static final OnlineTileSourceBase HIKEBIKEMAP = new XYTileSource("HikeBikeMap",
            0, 18, 256, ".png",
            new String[]{
                    "http://mt1.google.cn/vt/v=w2.97",
                    "http://mt2.google.cn/vt/v=w2.97",
                    "http://mt3.google.cn/vt/v=w2.97"});

    public static final OnlineTileSourceBase USGS_TOPO = new OnlineTileSourceBase("USGS National Map Topo", 0, 18, 256, "",
            new String[]{
                    "http://mt1.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
                    "http://mt2.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2",
                    "http://mt3.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2"}) {
        @Override
        public String getTileURLString(MapTile aTile) {
            return getBaseUrl() + aTile.getZoomLevel() + "/" + aTile.getY() + "/" + aTile.getX();
        }
    };

    private static ArrayList<ITileSource> mTileSources;

    static {
        mTileSources = new ArrayList<ITileSource>();
        mTileSources.add(MAPNIK);
        mTileSources.add(CYCLEMAP);
       /* mTileSources.add(PUBLIC_TRANSPORT);
        mTileSources.add(MAPQUESTOSM);
        mTileSources.add(MAPQUESTAERIAL);
        mTileSources.add(HIKEBIKEMAP);
        mTileSources.add(USGS_TOPO);*/
    }
}
