package com.example.examplecompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer()
        }
    }
}


@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { ToolBar() },
        content = { Content() },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End
    )
    MyGoogleMaps()
}

@Composable
fun MyGoogleMaps() {

    val marker = LatLng(-31.449142127185834, -64.17518237640897)
    val markerState = MarkerState(position = marker)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }
    val myProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.HYBRID))
    }
    val myUiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = myProperties,
        uiSettings = myUiSettings
    ) {
        Marker(
            state = markerState,
            title = "Cancha Taiere",
            snippet = "Marker in Taiere"
        )
    }
}

@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context, "Pressed button", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Press me", color = Color.Red)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar() {
    TopAppBar(
        title = { Text(text = "Maxi Rodriguez Profile", fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.background)),
    )
}

@Composable
fun Content() {

    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.aries),
                contentDescription = "Prueba de Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "icon favorite",
                    modifier = Modifier.clickable { counter++ }
                )
                Text(
                    text = counter.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            Text(
                text = "Maxi Rodriguez",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Desarrollador Android",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                textAlign = TextAlign.Center
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Text(text = "Mi Stack:", fontSize = 20.sp, color = Color.White)
                    Text(text = "Kotlin", fontSize = 20.sp, color = Color.White)
                    Text(text = "Java", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

