package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

@Composable
fun ContentAgent(
    agents: List<UsersCollection>,
    onCardClicked: (agent: UsersCollection) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        content = {

            items(
                items = agents
            ) { agent ->

                CaretakerCard(
                    context = context,
                    agent = agent,
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            onCardClicked(agent)
                        },
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

            }
        },
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    )


}