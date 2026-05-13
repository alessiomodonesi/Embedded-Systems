package com.bignerdranch.android.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private val TAG: String = BoxDrawingView::class.java.simpleName

class BoxDrawingView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private var currentBox: Box? = null
    private val boxen = mutableListOf<Box>()
    private val boxPaint = Paint()
    private val backgroundPaint = Paint()

    init {
        boxPaint.color = context.getColor(R.color.box_color)
        backgroundPaint.color = context.getColor(R.color.bg_color)
    }

    override fun onDraw(canvas: Canvas) {
        // Fill the background
        canvas.drawPaint(backgroundPaint)

        // Draw the boxes
        boxen.forEach { box ->
            canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current = PointF(event.x, event.y)
        val action: String

        when (event.action) {
            // TODO: manage ACTION_DOWN, ACTION_MOVE, ACTION_UP
            // - ACTION_DOWN: assign a new box to currentBox, add it to boxen
            // - ACTION_MOVE: update the current box, invalidate the view to force a redraw
            // - ACTION_UP: update the current box, invalidate the view to force a redraw,
            //   then we are done with this box
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
                // From the official documentation for MotionEvent: "You should treat this
                // as an up event, but not perform any action that you normally would"
                currentBox = null      // ... and we are done with this box
            }
            MotionEvent.ACTION_OUTSIDE -> {
                action = "ACTION_OUTSIDE"
                // Movement outside of the normal bounds of the UI element is ignored
            }
            else -> {
                action = "unexpected action"
            }
        }

        Log.i(TAG, "Box ${boxen.size}: $action at x=${current.x}, y=${current.y}")

        return true
    }
}
