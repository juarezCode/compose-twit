package com.ju4r3z.composetwit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ju4r3z.composetwit.ui.theme.ComposeTwitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTwitTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF161D26)),
                ) {
                    TwitterCard()
                    TwitDivider()
                }
            }
        }
    }
}

@Preview
@Composable
fun TwitterCard() {
    var chatIconSelected by remember { mutableStateOf(false) }
    var rtIconSelected by remember { mutableStateOf(false) }
    var likeIconSelected by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(55.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle(title = "Aris", modifier = Modifier.padding(end = 8.dp))
                DefaultText(title = "Aristidevs", modifier = Modifier.padding(end = 8.dp))
                DefaultText(title = "4h", modifier = Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            TextBody(
                text = "Esto es un twit de prueba para mi applicacion en jetpack compose",
                Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                contentScale = ContentScale.Crop
            )
            Row(Modifier.padding(top = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = chatIconSelected,
                    onItemSelected = {
                        chatIconSelected = !chatIconSelected
                    }
                )
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = rtIconSelected,
                    onItemSelected = {
                        rtIconSelected = !rtIconSelected
                    }
                )
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like_filled),
                            contentDescription = "",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = likeIconSelected,
                    onItemSelected = {
                        likeIconSelected = !likeIconSelected
                    }
                )
            }
        }

    }
}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1
    Row(
        modifier = modifier.clickable {
            onItemSelected()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSelected) selectedIcon()
        else unselectedIcon()

        Text(
            text = if (isSelected) "${defaultValue + 1}" else "$defaultValue",
            color = Color(0xFF7e8b98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Color.White, modifier = modifier)
}

@Composable
fun TextTitle(title: String, modifier: Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultText(title: String, modifier: Modifier) {
    Text(text = title, color = Color.Gray, modifier = modifier)
}

@Composable
fun TwitDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        color = Color.Gray
    )
}