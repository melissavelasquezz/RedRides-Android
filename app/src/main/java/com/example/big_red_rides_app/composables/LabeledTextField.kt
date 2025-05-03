package com.example.big_red_rides_app.composables

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabeledTextField(
    icon: Int,
    label: String,
    description: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
      Row(
          verticalAlignment = Alignment.CenterVertically
      ) {
          Icon(
              painter = painterResource(id = icon),
              contentDescription = "$label icon",
              modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
              label,
              style = MaterialTheme.typography.labelLarge,
              fontWeight = FontWeight.Bold
          )
      }
        Spacer(modifier = Modifier.width(2.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    description,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            singleLine = true,
            modifier= Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedLabelColor = Color.DarkGray
            )
        )


    }
}