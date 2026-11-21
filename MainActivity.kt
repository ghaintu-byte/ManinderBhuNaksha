package com.maninder.bhunaksha

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

private data class Portal(val title: String, val url: String, val detail: String)

private val portals = listOf(
    Portal(
        "भू-नक्शा (प्लॉट मैप)",
        "https://bhunakshahp.nic.in/",
        "गाँव और खसरा नंबर के अनुसार उपलब्ध डिजिटाइज़्ड प्लॉट मैप"
    ),
    Portal(
        "हिमभूमि भूमि रिकॉर्ड",
        "https://himbhoomilmk.nic.in/viewLandRecords.aspx",
        "जमाबंदी और अन्य उपलब्ध भूमि रिकॉर्ड"
    ),
    Portal(
        "गाँव डिजिटाइजेशन स्थिति",
        "https://trainingehimbhoomi.hp.gov.in/citizen",
        "गाँव/तहसील के नक्शा डिजिटाइजेशन की उपलब्ध स्थिति"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { ManinderApp() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ManinderApp() {
    var selectedPortal by remember { mutableStateOf<Portal?>(null) }
    var district by remember { mutableStateOf("Mandi") }
    var tehsil by remember { mutableStateOf("Jogindernagar") }
    var village by remember { mutableStateOf("") }
    var khasra by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Maninder Bhu Naksha") },
                navigationIcon = {
                    if (selectedPortal != null) {
                        TextButton(onClick = { selectedPortal = null }) { Text("वापस") }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (selectedPortal != null) {
            Column(Modifier.fillMaxSize().padding(innerPadding)) {
                Text(
                    selectedPortal!!.detail,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            settings.loadsImagesAutomatically = true
                            webViewClient = WebViewClient()
                            loadUrl(selectedPortal!!.url)
                        }
                    },
                    update = { webView ->
                        if (webView.url != selectedPortal!!.url) {
                            webView.loadUrl(selectedPortal!!.url)
                        }
                    }
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
                    .verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("हिमाचल प्रदेश के भूमि रिकॉर्ड", style = MaterialTheme.typography.headlineSmall)
                Text(
                    "यह ऐप सरकारी पोर्टलों को एक जगह खोलती है। रिकॉर्ड की उपलब्धता संबंधित गाँव की डिजिटाइजेशन स्थिति पर निर्भर है।",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = district, onValueChange = { district = it },
                    label = { Text("जिला (फिलहाल मैन्युअल)") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = tehsil, onValueChange = { tehsil = it },
                    label = { Text("तहसील (फिलहाल मैन्युअल)") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = village, onValueChange = { village = it },
                    label = { Text("गाँव का नाम") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = khasra, onValueChange = { khasra = it },
                    label = { Text("खसरा नंबर") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                Text(
                    "ध्यान दें: ऊपर के बॉक्स अभी सरकारी डेटाबेस से जुड़े नहीं हैं; इनमें लिखने से रिकॉर्ड अपने-आप खोजा नहीं जाएगा।",
                    style = MaterialTheme.typography.bodySmall
                )
                HorizontalDivider()
                Text("सरकारी सेवाएँ", style = MaterialTheme.typography.titleLarge)
                portals.forEach { portal ->
                    ElevatedCard(
                        onClick = { selectedPortal = portal },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(portal.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(4.dp))
                            Text(portal.detail, style = MaterialTheme.typography.bodySmall)
                            Spacer(Modifier.height(6.dp))
                            Text("खोलें", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                Text(
                    "आधिकारिक GIS/API कनेक्शन के लिए विभाग से लिखित अनुमति और सर्वर/API विवरण लेना आवश्यक है।",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
