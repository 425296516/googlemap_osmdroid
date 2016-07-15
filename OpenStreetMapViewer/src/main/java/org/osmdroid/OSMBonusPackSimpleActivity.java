package org.osmdroid;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
import org.osmdroid.bonuspack.overlays.ItemizedOverlayWithBubble;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;

import java.util.ArrayList;

public class OSMBonusPackSimpleActivity extends Activity {
    private MapView map;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osmbonus_pack_simple);
        //获取地图对象
        map = (MapView) findViewById(R.id.myOSMmapview);
        //设置地图图源
        map.setTileSource(TileSourceFactory.MAPNIK);
        //设置起始点和中心
        GeoPoint startPoint = new GeoPoint(48.13, -1.63);
        MapController mapController = (MapController)map.getController();
        mapController.setCenter(startPoint);
        mapController.setZoom(9);
        //创建RoadManager管理器
        RoadManager roadManager = new OSRMRoadManager();

        //如果想需要自行车线路采用如下方式
        //RoadManager roadManager = new MapQuestRoadManager();
        //roadManager.addRequestOption("routeType=bicycle");

        //设置线路的起始点位置
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(startPoint);
        waypoints.add(new GeoPoint(48.4, -1.9)); //end point
        //获取相关的线路对象
        Road road = roadManager.getRoad(waypoints);
        //创建线路图层
        PathOverlay roadOverlay = RoadManager.buildRoadOverlay(road, map.getContext());
        //添加线路图层
        map.getOverlays().add(roadOverlay);


        //设置线路的上的标志
        final ArrayList<ExtendedOverlayItem> roadItems =    new ArrayList<ExtendedOverlayItem>();
        Drawable marker = getResources().getDrawable(R.drawable.icon);
        for (int i=0; i<road.mNodes.size(); i++){
            RoadNode node = road.mNodes.get(i);
            ExtendedOverlayItem nodeMarker = new ExtendedOverlayItem("Step "+i, "", node.mLocation, this);
            nodeMarker.setMarkerHotspot(OverlayItem.HotspotPlace.CENTER);
            nodeMarker.setMarker(marker);
            roadItems.add(nodeMarker);
        }
        //创建气泡
        ItemizedOverlayWithBubble<ExtendedOverlayItem> roadNodes =    new ItemizedOverlayWithBubble<ExtendedOverlayItem>(this, roadItems, map);
        //添加线路上气泡信息
        map.getOverlays().add(roadNodes);

        //刷新地图
        map.invalidate();
    }
}