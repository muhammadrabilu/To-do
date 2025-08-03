package com.rabilu.todo.ui.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    properties: TextFieldProperties = TextFieldProperties(
        label = "Text",
        placeHolderText = "Enter your email"
    ),
    trailingIcons: @Composable (() -> Unit)? = null,
    leadingIcons: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    validateLength: Boolean = false,
    lenght: Int = 0,
    colors: TextFieldColors = MyTextfieldColor(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChang: ((String) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        if (properties.label.isNotEmpty())
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF78828A)
                    )
                ) {
                    append(properties.label.replaceFirstChar { it.uppercase() })
                }

            }, modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = properties.getText(),
            onValueChange = {
                if (validateLength) {
                    if (it.length <= lenght) {
                        properties.setText(it)
                        properties.isError.value = false
                    }
                } else {
                    properties.setText(it)
                    properties.isError.value = false
                }

                if (onTextChang != null)
                    onTextChang(it)

            },
            singleLine = true,
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            keyboardOptions = properties.keyboardOptions,
            placeholder = {
                Text(
                    text = properties.placeHolderText,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 14.sp,
                    )
                )
            },

            leadingIcon = leadingIcons,
            trailingIcon = trailingIcons,

            supportingText = {
                if (properties.isError.value) {
                    Text(
                        text = properties.errorMessage.value,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.Red
                        )
                    )
                }
            },

            )


    }
}



@Preview(showBackground = true)
@Composable
private fun CostumeTextField() {
//    CustomButtonPlaceholder{
//
//    }
}