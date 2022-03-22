package com.example.seajudge.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.seajudge.data.model.Report
import com.example.seajudge.ui.theme.DarkGrey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.Red
import com.example.seajudge.ui.theme.Secondary
import com.example.seajudge.util.Formatter
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Edit
import compose.icons.evaicons.outline.Pin
import compose.icons.evaicons.outline.Trash

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ReportCard(
    report: Report,
    isEditable: Boolean? = false,
    onImageClicked: () -> Unit,
    onEditBtnClicked: () -> Unit = {},
    onDeleteBtnClicked: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(17.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = report.reporter,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )

                if (isEditable == true) {
                    Row {
                        Icon(
                            modifier = Modifier.clickable(onClick = onEditBtnClicked),
                            imageVector = EvaIcons.Outline.Edit,
                            tint = Secondary,
                            contentDescription = "Edit report icon"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            modifier = Modifier
                                .size(21.dp)
                                .clickable(onClick = onDeleteBtnClicked),
                            imageVector = EvaIcons.Outline.Trash,
                            tint = Red,
                            contentDescription = "Delete report icon"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = onImageClicked),
                painter = rememberImagePainter(report.photo),
                contentScale = ContentScale.Crop,
                contentDescription = "Report image"
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = EvaIcons.Outline.Pin,
                        tint = Primary,
                        contentDescription = "Location icon"
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = report.location,
                        color = Primary,
                        style = MaterialTheme.typography.caption
                    )
                }
                Text(
                    text = "${Formatter.formatDate(report.date)}, ${report.time}",
                    color = DarkGrey,
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = report.violation,
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )
        }
    }
}