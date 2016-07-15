package org.osmdroid.samplefragments;

import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;

/**
 * Simple how to for setting a custom tile source
 * @author alex
 */
public class SampleCustomTileSource extends BaseSampleFragment{

     @Override
     public String getSampleTitle() {
          return "Custom Tile Source";
          
     }
      @Override
     public void addOverlays() {
          mMapView.setTileSource(new OnlineTileSourceBase("USGS Topo", 0, 18, 256, "", 
               new String[] { "http://mt3.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&scale=2&" }) {
               @Override
               public String getTileURLString(MapTile aTile) {
                    //return getBaseUrl() + aTile.getZoomLevel() + "/" + aTile.getY() + "/" + aTile.getX()+ mImageFilenameEnding;
                 return getBaseUrl() + "&x=" + aTile.getX() + "&y=" + aTile.getY() + "&z=" + aTile.getZoomLevel();
               }
          });
          
     }
     
}
