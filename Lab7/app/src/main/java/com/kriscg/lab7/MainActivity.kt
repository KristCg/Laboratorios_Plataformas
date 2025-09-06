package com.kriscg.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kriscg.lab7.ui.theme.Lab7Theme


enum class NotificationType {
    GENERAL,
    NEW_MEETING
}

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: String,
    val type: NotificationType
)

fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nueva reunión agendada con Koalit",
        "Mensaje de Maria",
        "Capacitación: jetpack compose internals"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Koalit te ha enviado un evento para que agregues a tu calendario",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "Inicio de la capacitación 'Jetpack Compose Internals', no faltes"
    )
    val types = NotificationType.entries.toTypedArray()

    for (i in 1..50) {
        val sendAt = "30 Sept 10:45am"
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = sendAt,
                type = types.random()
            )
        )
    }
    return notifications
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenContent(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var filtro by remember { mutableStateOf<NotificationType?>(null) }
    val notifications = remember { generateFakeNotifications() }

    val filtroNotificaciones = when (filtro) {
        NotificationType.GENERAL -> notifications.filter { it.type == NotificationType.GENERAL }
        NotificationType.NEW_MEETING -> notifications.filter { it.type == NotificationType.NEW_MEETING }
        else -> notifications
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notificaciones",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Tipos de Notificaciones",
                style = MaterialTheme.typography.bodyLarge
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterButton(
                    text = "Informativas",
                    selected = filtro == NotificationType.GENERAL
                ) {
                    filtro = if (filtro == NotificationType.GENERAL) null else NotificationType.GENERAL
                }

                FilterButton(
                    text = "Capacitaciones",
                    selected = filtro == NotificationType.NEW_MEETING
                ) {
                    filtro = if (filtro == NotificationType.NEW_MEETING) null else NotificationType.NEW_MEETING
                }
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filtroNotificaciones) { notification ->
                        NotificationItem(notification = notification)
                    }
                }
            }
        }
    }
}


@Composable
fun FilterButton(text: String, selected: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = if (selected) {
            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        },
        border = BorderStroke(
            1.dp,
            if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            }
        )
    ) {
        Text(text)
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
@Composable
fun getNotificationColors(type: NotificationType): Pair<androidx.compose.ui.graphics.Color, androidx.compose.ui.graphics.Color> {
    return when (type) {
        NotificationType.GENERAL -> Pair(
            androidx.compose.ui.graphics.Color(0xFFBBDEFB),
            MaterialTheme.colorScheme.onPrimaryContainer
        )
        NotificationType.NEW_MEETING -> Pair(
            androidx.compose.ui.graphics.Color(0xFFFFF9C4),
            MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun NotificationItem(notification: Notification) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = if (notification.type == NotificationType.GENERAL) {
            Icons.Default.Notifications
        } else {
            Icons.Default.DateRange
        }

        val colors = getNotificationColors(notification.type)

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = colors.first, shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.second,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = notification.sendAt,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = notification.body,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreenContent() {
    MaterialTheme {
        ScreenContent(navController = rememberNavController())
    }
}



