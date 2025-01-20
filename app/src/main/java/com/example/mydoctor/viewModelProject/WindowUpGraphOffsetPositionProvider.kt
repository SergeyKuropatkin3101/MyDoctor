package com.example.mydoctor.viewModelProject

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

class WindowUpGraphOffsetPositionProvider (
    private val x: Int = 0,
    private val y: Int = 0
    ) : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowSize: IntSize,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize
        ): IntOffset {
            return IntOffset(
                (windowSize.width - popupContentSize.width) / 2 + x,
                windowSize.height / 2 - popupContentSize.height + 10
            )
        }
}