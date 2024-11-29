package com.example.mydoctor.component.homeScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toOffset
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.White
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun Modifier.tooltip(
    title: String? = null,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    enabled: Boolean = true,
    showOverlay: Boolean = true,
    highlightComponent: Boolean = true,
    onDismiss: () -> Unit = {}
): Modifier {
    val paddingValues = PaddingValues(horizontal = 32.dp)
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = remember { with(density) { configuration.screenWidthDp.dp.roundToPx() } }
    val screenHeightPx = remember { with(density) { configuration.screenHeightDp.dp.roundToPx() } }

    var positionInRootCompotent by remember { mutableStateOf(IntOffset.Zero) }
    var tooltipSize by remember { mutableStateOf(IntSize(0, 0)) }
    var componentSize by remember { mutableStateOf(IntSize(0, 0)) }

    val tooltipOffset by remember(positionInRootCompotent, componentSize, tooltipSize) {
        derivedStateOf {
            calculateOffset(
                positionInRootCompotent, componentSize, tooltipSize, screenWidthPx, screenHeightPx
            )
        }
    }

    if (enabled) {
        Popup {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawOverlayBackground(
                        showOverlay = showOverlay,
                        highlightComponent = highlightComponent,
                        positionInRoot = positionInRootCompotent,
                        componentSize = componentSize,
                        backgroundColor = Black1000,
                        backgroundAlpha = 0.2f
                    )
                    .clickable(
                        onClick = {
                            onDismiss()
                        }
                    )
            ) {
                ArrowTooltip(
                    modifier = Modifier
                        .onSizeChanged { tooltipSize = it }
                        .offset { tooltipOffset }
                        .padding(paddingValues),
                    title = title,
                    text = text,
                    textAlign = textAlign,
                    onDismiss = { onDismiss() }
                )
            }
        }
    }

    return this then Modifier.onPlaced {
        componentSize = it.size
        positionInRootCompotent = it.positionInRoot().toIntOffset()
    }
}

@Composable
fun ArrowTooltip(
    modifier: Modifier = Modifier,
    title: String?,
    text: String,
    textAlign: TextAlign,
    onDismiss: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(25.dp,16.dp),
    ) {

        IconButton(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd),
            onClick = { onDismiss() }
        ) {
            Icon(
                painterResource(
                    id = R.drawable.iconexit
                ),
                modifier = Modifier
                    .fillMaxSize(),
                contentDescription = "icon exit"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                painterResource(
                    id = R.drawable.iconcamera
                ),
                modifier = Modifier
                    .padding(8.dp),
                contentDescription = "iconcamera"
            )
            if (title != null) {
                Text(title,
                    modifier = Modifier
                        .padding(8.dp),
                    fontSize = 18.sp,
                    color = Black1000)
            }
            Text(
                text,
                modifier = Modifier
                    .padding(8.dp),
                textAlign = textAlign,
                fontSize = 14.sp,
                color = Black1000
            )
        }
    }

}


private fun calculateOffset(
    positionInRoot: IntOffset,
    componentSize: IntSize,
    tooltipSize: IntSize,
    screenWidthPx: Int,
    screenHeightPx: Int,
): IntOffset {
    val horizontalAlignmentPosition =  positionInRoot.x //+ (componentSize.width / 2) - (tooltipSize.width / 2)


    val verticalAlignmentPosition = positionInRoot.y - (tooltipSize.height + componentSize.height)

    return IntOffset(
        x = min(screenWidthPx - tooltipSize.width, horizontalAlignmentPosition),
        y = min(screenHeightPx - tooltipSize.height, verticalAlignmentPosition)
    )
}

private fun Modifier.drawOverlayBackground(
    showOverlay: Boolean,
    highlightComponent: Boolean,
    positionInRoot: IntOffset,
    componentSize: IntSize,
    backgroundColor: Color,
    backgroundAlpha: Float
) : Modifier {
    return if (showOverlay) {

        if (highlightComponent) {
            val positionInRootComponent = IntOffset(
                x = positionInRoot.x,
                y = positionInRoot.y-((componentSize.toSize().height)*3/5).toInt()
            )
            val CornerRadiusComponent = CornerRadius(componentSize.height/2.toFloat())
            // Если значение highlightComponent установлено в true, нам нужно создать Path и Rect с положением
            // и размером компонента и нарисовать фон, обрезав Path.
            drawBehind {
                val highlightPath = Path().apply {
                    addRoundRect(
                        RoundRect(
                            Rect(
                                positionInRootComponent.toOffset(),
                                componentSize.toSize()
                            ),
                            CornerRadiusComponent
                        )
                    )
                }
                clipPath(highlightPath, clipOp = ClipOp.Difference) {
                    drawRect(SolidColor(backgroundColor.copy(alpha = backgroundAlpha)))
                }
            }
        } else {
            // Если highlightComponent установлен в false, мы просто добавим полноразмерный фон,
            // который закроет все и будет иметь альфа-значение для обеспечения полупрозрачности.
            background(backgroundColor.copy(alpha = backgroundAlpha))
        }
    } else {
        this
    }
}


private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())