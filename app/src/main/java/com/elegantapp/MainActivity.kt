package com.elegantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elegantapp.ui.theme.ElegantAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElegantAppTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var isVisible by remember { mutableStateOf(false) }
    
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alpha"
    )
    
    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1A1A),
                        Color(0xFF000000)
                    )
                )
            )
            .alpha(alpha)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "ELEGANT",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 4.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
        
        // Main Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                WelcomeCard()
            }
            
            items(getFeaturesList()) { feature ->
                FeatureCard(feature = feature)
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a ELEGANT",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                letterSpacing = 2.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Una aplicación profesional diseñada para la excelencia y la elegancia en cada detalle.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFCCCCCC),
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun FeatureCard(feature: Feature) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = feature.accentColor,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = feature.icon,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = feature.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = feature.description,
                    fontSize = 14.sp,
                    color = Color(0xFFAAAAAA),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

data class Feature(
    val title: String,
    val description: String,
    val icon: String,
    val accentColor: Color
)

fun getFeaturesList(): List<Feature> {
    return listOf(
        Feature(
            title = "Diseño Moderno",
            description = "Interfaz elegante y minimalista siguiendo las últimas tendencias de diseño",
            icon = "✨",
            accentColor = Color(0xFF007AFF)
        ),
        Feature(
            title = "Rendimiento Optimizado",
            description = "Arquitectura moderna con Jetpack Compose para máximo rendimiento",
            icon = "⚡",
            accentColor = Color(0xFF34C759)
        ),
        Feature(
            title = "Experiencia Fluida",
            description = "Animaciones suaves y transiciones elegantes en cada interacción",
            icon = "🎯",
            accentColor = Color(0xFFFF9500)
        ),
        Feature(
            title = "Seguridad Avanzada",
            description = "Implementación de las mejores prácticas de seguridad móvil",
            icon = "🔒",
            accentColor = Color(0xFFFF3B30)
        ),
        Feature(
            title = "Personalización",
            description = "Opciones avanzadas de personalización para cada usuario",
            icon = "🎨",
            accentColor = Color(0xFF5856D6)
        ),
        Feature(
            title = "Soporte 24/7",
            description = "Atención al cliente profesional disponible en todo momento",
            icon = "💬",
            accentColor = Color(0xFFAF52DE)
        )
    )
}
