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

package com.niumedia.comeralia;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import org.apache.cordova.*;

import com.facebook.Settings;

public class Comeralia extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        

        if(isOnline()){
            super.setIntegerProperty("splashscreen", R.drawable.splash);
            super.loadUrl("file:///android_asset/www/index.html", 10000);
            super.setIntegerProperty("loadUrlTimeoutValue", 6000);
        }else{
        	Toast.makeText(getApplicationContext(), "Sin conexiona internet no ṕuedes usar esta app", Toast.LENGTH_LONG).show();
        }
    }
    
	public boolean isOnline() {
	    ConnectivityManager cm = 
	         (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Settings.publishInstallAsync(getApplicationContext(), getString(R.string.fb_id));
	}
}

