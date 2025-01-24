package com.chilcotin.rickandmortyapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chilcotin.rickandmortyapp.R
import com.chilcotin.rickandmortyapp.entities.CharacterModel
import com.chilcotin.rickandmortyapp.ui.theme.AliveGround
import com.chilcotin.rickandmortyapp.ui.theme.DeadGround
import com.chilcotin.rickandmortyapp.ui.theme.Origin
import com.chilcotin.rickandmortyapp.ui.theme.Typography
import com.chilcotin.rickandmortyapp.ui.theme.UnknownGround
import com.chilcotin.rickandmortyapp.ui.theme.WatchEpisodes
import com.chilcotin.rickandmortyapp.ui.theme.WatchEpisodesGround
import com.chilcotin.rickandmortyapp.viewmodels.MainViewModel
import java.util.Locale

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    mainViewModel: MainViewModel
) {
    val list = mainViewModel.liveData.observeAsState().value

    Column(
        modifier = Modifier
            .padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            fontSize = 31.sp,
            lineHeight = 37.sp,
            fontStyle = Typography.bodyLarge.fontStyle,
            fontWeight = FontWeight.Bold,
            color = Typography.bodyLarge.color,
            text = "Characters",
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = rememberLazyListState()
        ) {
            if (list != null)
                items(items = list, key = { item -> item.hashCode() }) {
                    CharacterItem(it)
                }
        }
    }
}

@Composable
fun CharacterItem(item: CharacterModel) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(40.dp)),
                painter = rememberAsyncImagePainter(item.avatar),
                contentDescription = "Avatar"
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 18.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier
                            .weight(0.8f),
                        fontSize = Typography.labelLarge.fontSize,
                        lineHeight = Typography.labelLarge.lineHeight,
                        color = Typography.labelLarge.color,
                        fontStyle = Typography.bodyLarge.fontStyle,
                        fontWeight = FontWeight.Bold,
                        text = item.name,
                        maxLines = 1,
                    )
                    Text(
                        modifier = when (item.status) {
                            "Alive" -> {
                                Modifier
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(AliveGround)
                                    .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                            }

                            "Dead" -> {
                                Modifier
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(DeadGround)
                                    .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                            }

                            else -> {
                                Modifier
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(UnknownGround)
                                    .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                            }
                        },
                        fontSize = Typography.labelSmall.fontSize,
                        lineHeight = Typography.labelSmall.lineHeight,
                        color = Typography.labelSmall.color,
                        fontStyle = Typography.labelSmall.fontStyle,
                        text = item.status.uppercase(Locale.getDefault()),
                        maxLines = 1,
                    )
                }
                Text(
                    modifier = Modifier,
                    fontSize = Typography.labelSmall.fontSize,
                    lineHeight = Typography.labelSmall.lineHeight,
                    color = Typography.labelSmall.color,
                    fontStyle = Typography.labelSmall.fontStyle,
                    text = "${item.species}, ${item.gender}",
                    maxLines = 1,
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(17.dp))
                        .background(WatchEpisodesGround)
                        .clickable { }
                        .padding(start = 4.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.baseline_arrow_right_24),
                        contentDescription = "Watch episodes"
                    )
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        fontSize = Typography.labelSmall.fontSize,
                        lineHeight = Typography.labelSmall.lineHeight,
                        fontStyle = Typography.labelSmall.fontStyle,
                        color = WatchEpisodes,
                        text = "Watch episodes",
                        maxLines = 1,
                    )
                }
                Row(
                    modifier = Modifier
                ) {
                    Image(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Location icon"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        fontSize = Typography.labelSmall.fontSize,
                        lineHeight = Typography.labelSmall.lineHeight,
                        fontStyle = Typography.labelSmall.fontStyle,
                        color = Origin,
                        text = item.origin,
                    )
                }
            }
        }
    }
}