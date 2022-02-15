package com.myapp.todoapp.presentation.todo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    changeTitle: (String) -> Unit,
    changeDescription: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(1.dp, RoundedCornerShape(11.dp))
                        .clip(RoundedCornerShape(11.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(11.dp)
                        )
                        .clickable {}
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    BasicTextField(
                        value = "Make Android App every day!",
                        onValueChange = changeTitle,
                        textStyle = MaterialTheme.typography.body1
                    )
                    Row {
                        Text(
                            text = "February 15n",
                            style = MaterialTheme.typography.body2,
                            color = Color.LightGray
                        )
                        Text(
                            text = " • ",
                            color = Color.LightGray
                        )
                        Text(
                            text = "10:00 AM",
                            style = MaterialTheme.typography.body2,
                            color = Color.LightGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = "Make sure to make Android Apps as often as possible",
                    onValueChange = changeDescription,
                    textStyle = TextStyle(color = Color.Gray),
                    modifier = Modifier.weight(1f)
                )
            }



        }
    }
}
