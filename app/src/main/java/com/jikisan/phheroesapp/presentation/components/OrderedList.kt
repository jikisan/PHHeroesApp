package com.jikisan.phheroesapp.presentation.components


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.jikisan.phheroesapp.ui.theme.SMALL_PADDING
import com.jikisan.phheroesapp.ui.theme.titleColor

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column (
        modifier = Modifier.padding(bottom = SMALL_PADDING),
    ){
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
        )
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. ${item}",
                color = textColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                overflow = TextOverflow.Ellipsis

            )
        }
    }
}

fun reduceName(name: String): String {
    var split = name.split(" ")

    if (split.size < 2 ) {
        return split[0]
    }

    return "${split[0]} ${split[1]}"
}

@Composable
@Preview(showBackground = true)
fun OrderedListPreview() {
    OrderedList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        textColor = MaterialTheme.colors.titleColor
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun OrderedListDarkPreview() {
    OrderedList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        textColor = MaterialTheme.colors.titleColor
    )
}