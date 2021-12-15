/*
 * Copyright (c) 2021 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.mapbox.mapboxsdk.Mapbox
import im.vector.app.BuildConfig
import im.vector.app.core.platform.VectorBaseFragment
import im.vector.app.databinding.FragmentLocationSharingBinding
import javax.inject.Inject

class LocationSharingFragment @Inject constructor() :
        VectorBaseFragment<FragmentLocationSharingBinding>() {

    private val viewModel: LocationSharingViewModel by activityViewModel()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocationSharingBinding {
        return FragmentLocationSharingBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Mapbox before inflating mapView
        Mapbox.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMapView(savedInstanceState)
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        val key = BuildConfig.mapTilerKey
        val styleUrl = "https://api.maptiler.com/maps/streets/style.json?key=${key}"
        views.mapView.onCreate(savedInstanceState)
        views.mapView.getMapAsync { map ->
            map.setStyle(styleUrl)
        }
    }
}
