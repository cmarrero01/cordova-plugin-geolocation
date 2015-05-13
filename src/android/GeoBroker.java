/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package org.apache.cordova.geolocation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/*
 * This class is the interface to the Geolocation.  It's bound to the geo object.
 *
 * This class only starts and stops various GeoListeners, which consist of a GPS and a Network Listener
 */

public class GeoBroker extends CordovaPlugin {
    private GPSListener gpsListener;
    private NetworkListener networkListener;
    private LocationManager locationManager;    

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action 		The action to execute.
     * @param args 		JSONArry of arguments for the plugin.
     * @param callbackContext	The callback id used when calling back into JavaScript.
     * @return 			True if the action was valid, or false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (locationManager == null) {
            locationManager = (LocationManager) this.cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
        }
        if(action.equals("isGPSEnabled")){
            PluginResult result;
            if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )){
                result = new PluginResult(PluginResult.Status.OK);
            }else{
                result = new PluginResult(PluginResult.Status.ERROR);
            }
            callbackContext.sendPluginResult(result);
        }
        return true;
    }


    /**
     * Called when the view navigates.
     * Stop the listeners.
     */
    public void onReset() {
        this.onDestroy();
    }
}
