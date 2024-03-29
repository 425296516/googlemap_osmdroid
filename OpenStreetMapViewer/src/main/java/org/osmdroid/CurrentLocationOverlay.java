package org.osmdroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MyLocationOverlay;

/**
 * Created by zhangcirui on 16/5/27.
 */
public class CurrentLocationOverlay extends MyLocationOverlay {
    private final static int PADDING_ACTIVE_ZOOM     = 50;
    private MapController mc;
    private Bitmap marker;
    private Point currentPoint            = new Point();
    private boolean          centerOnCurrentLocation = true;
    private int              height;
    private int              width;
    /**
     * By default this CurrentLocationOverlay will center on the current location,
     * if the currentLocation is near the
     * edge, or off the screen. To dynamically enable/disable this,
     * use {@link #setCenterOnCurrentLocation(boolean)}.
     * @param context
     * @param mapView
     */
    public CurrentLocationOverlay(Context context, MapView mapView) {
        super(context, mapView);
        this.mc = (MapController)mapView.getController();
        this.marker = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_default);
    }
    protected void drawMyLocation(Canvas canvas, MapView mapView, Location lastFix, GeoPoint myLocation, long when) {
        if (this.height == 0) {
            this.height = mapView.getHeight();
            this.width = mapView.getWidth();
        }
        mapView.getProjection().toPixels(myLocation, currentPoint);

        canvas.drawBitmap(marker, currentPoint.x, currentPoint.y - 40, null);
    }
    @Override
    public synchronized void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        // only move to new position if enabled and we are in an border-area
        if (mc != null && centerOnCurrentLocation && inZoomActiveArea(currentPoint)) {
            mc.animateTo(getMyLocation());
            setCenterOnCurrentLocation(true);
        }
    }
    private boolean inZoomActiveArea(Point currentPoint) {
        if ((currentPoint.x > PADDING_ACTIVE_ZOOM && currentPoint.x < width - PADDING_ACTIVE_ZOOM) && (currentPoint.y > PADDING_ACTIVE_ZOOM && currentPoint.y < height - PADDING_ACTIVE_ZOOM)) {
            return false;
        }
        return true;
    }
    public void setCenterOnCurrentLocation(boolean centerOnCurrentLocation) {
        this.centerOnCurrentLocation = centerOnCurrentLocation;
    }
}
