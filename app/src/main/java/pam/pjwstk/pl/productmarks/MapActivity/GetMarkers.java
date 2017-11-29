package pam.pjwstk.pl.productmarks.MapActivity;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Boberkowy on 28.11.2017.
 */

public class GetMarkers extends AsyncTask<Object,Void,String> {


    String mGoogleDataPlaces;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();

        try {
            mGoogleDataPlaces = downloadUrl.readUrl(url);
        }catch (IOException e){
            e.printStackTrace();
        }

        return mGoogleDataPlaces;

    }

    @Override
    protected void onPostExecute(String string) {
        List<HashMap<String,String>> nearbyPlaceList = null;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(string);
        showPlaces(nearbyPlaceList);
    }

    private void showPlaces(List<HashMap<String,String>> nearbyPlaceList) {
        for (int i = 0; i < nearbyPlaceList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : " + vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            mMap.addMarker(markerOptions);

        }
    }
}
