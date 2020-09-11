package com.example.mylibrary2

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View


class TwoCircularView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var mDrawable1: Drawable? = null
    private var mDrawable2: Drawable? = null
    private var mRadius = 0f


    protected var shader: BitmapShader? = null
    private val center = 0f
    private var bitmapCenterX = 0f
    private var bitmapCenterY = 0f
    private val borderRadius = 0f
    private var bitmapRadius = 0
    protected var drawable: Drawable? = null
    protected var viewWidth = 0
    protected var viewHeight = 0

    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }
//
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH
//
    private val paint = Paint()
    private val mouthPath = Path()
    private val secondaryCirclePath = Path()
    private var size = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = Math.min(measuredWidth, measuredHeight)

        setMeasuredDimension(size, size)
    }



    init {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.TwoCircularView,
            0, 0)

        // Extract custom attributes into member variables
        mDrawable1 = typedArray.getDrawable(R.styleable.TwoCircularView_drawable1)
        mDrawable2 = typedArray.getDrawable(R.styleable.TwoCircularView_drawable2)
        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPrimaryCircle(canvas)
        drawSecondaryCircle(canvas)

    }

    private fun drawPrimaryCircle(canvas: Canvas) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        mRadius = size / 3f

        canvas.drawCircle(size / 2f, size / 2f, mRadius, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawCircle(size / 2f, size / 2f, mRadius - borderWidth / 2f, paint)

        val centerOfCircle = PointF(size / 2f, size / 2f)
        val avatarBounds = RectFFactory.fromCircle(centerOfCircle, radius = mRadius).inflate(2f)
        mDrawable1?.let {
            canvas.drawBitmap(getCircularBitmap(getBitmap(mDrawable1)!!)!!, null, avatarBounds, paint)

        }


    }

    private fun drawSecondaryCircle(canvas: Canvas) {


        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        val radius = size / 4f

//
        canvas.drawCircle((size / 8) * 6.toFloat(), (size / 8) * 6.toFloat(), radius / 2f, paint)
//
        val shapeBounds = RectFFactory.fromLTWH(
            (size / 4).toFloat(),
            (size / 4).toFloat(),
            size.toFloat(),
            (size / 2).toFloat()
        )
        val centerAvatar = PointF(shapeBounds.centerX(), shapeBounds.bottom)
        val avatarBounds = RectFFactory.fromCircle(centerAvatar, radius = size / 6f).inflate(2f)

//        val curvePath = Path().apply {
//            arcTo(avatarBounds, -180f, 359f, false)
//            close()
//        }
//        canvas.drawPath(curvePath, paint)
//        drawable = resources.getDrawable(R.drawable.default_event)
//        drawable.let {
//            it?.setBounds(avatarBounds.left.toInt(), avatarBounds.top.toInt(), avatarBounds.right.toInt(), avatarBounds.bottom.toInt())
//            it?.draw(canvas)
//        }
        mDrawable2?.let {
            canvas.drawBitmap(getCircularBitmap(getBitmap(mDrawable2)!!)!!, null, avatarBounds, paint)

        }

//
//
//        paint.color = Color.BLUE
//        val curveTopLeft = Path().apply {
//            // 1 เริ่มวางปากกาที่จุด x y
//            moveTo(avatarBounds.left, avatarBounds.left)
//            // 2 วาดไปยังจุด x y
//            lineTo(avatarBounds.top, avatarBounds.right)
//            // 3 ระบาย จากจุดเริ่มต้นที่ -180 แล้ว กวาดตามเข็มนาฬิกาไปอีก 90
//            arcTo(avatarBounds, -180f, 90f, false)
////            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
////            lineTo(bounds.topRight.x, bounds.topRight.y)
////            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
//            close()
//        }
//        canvas.drawPath(curveTopLeft, paint)
//
//        val curveBottomLeft = Path().apply {
//            moveTo(avatarBounds.top, avatarBounds.right)
//            lineTo(avatarBounds.right, avatarBounds.right)
//            // 3 ระบาย จากจุดเริ่มต้นที่ 90f แล้ว กวาดตามเข็มนาฬิกาไปอีก 90
//            arcTo(avatarBounds, 90f, 90f, false)
////            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
////            lineTo(bounds.topRight.x, bounds.topRight.y)
////            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
//            close()
//        }
//        canvas.drawPath(curveBottomLeft, paint)
//
//        val curveTopRight = Path().apply {
//            moveTo(avatarBounds.bottom, avatarBounds.left)
//            lineTo(avatarBounds.top, avatarBounds.left)
//            arcTo(avatarBounds, 270f, 90f, false)
////            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
////            lineTo(bounds.topRight.x, bounds.topRight.y)
////            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
//            close()
//        }
//        canvas.drawPath(curveTopRight, paint)
////
//
//        val curveBottomRight = Path().apply {
//            moveTo(avatarBounds.right, avatarBounds.right)
//            lineTo(avatarBounds.right, avatarBounds.left)
//            arcTo(avatarBounds, 0f, 90f, false)
////            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
////            lineTo(bounds.topRight.x, bounds.topRight.y)
////            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
//            close()
//        }
//        canvas.drawPath(curveBottomRight, paint)

//        createShader(paint)
    }

    //    private fun drawCurvedShape(canvas: Canvas, bounds: RectF, avatarBounds: RectF) {
//        val paint = Paint()
//        paint.shader = createGradient(bounds)
//
//        val handlePoint = PointF(bounds.left + (bounds.width() * 0.25f), bounds.top)
//
//        val curvePath = Path().apply {
//            moveTo(bounds.bottomLeft.x, bounds.bottomLeft.y)
//            lineTo(avatarBounds.centerLeft.x, avatarBounds.centerLeft.y)
//            arcTo(avatarBounds, -180f, 180f, false)
//            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
//            lineTo(bounds.topRight.x, bounds.topRight.y)
//            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
//            close()
//        }
//
//        canvas.drawPath(curvePath, paint)
//
//    }
//
//
//    private fun createGradient(bounds: RectF): LinearGradient {
//        val colors = intArrayOf(paint.color.darkerShade(), paint.color, paint.color.darkerShade())
//        val stops = floatArrayOf(0.0f, 0.3f, 1.0f)
//
//        return LinearGradient(
//                bounds.centerLeft1.x, bounds.centerLeft1.y,
//                bounds.centerRight.x, bounds.centerRight.y,
//                colors,
//                stops,
//                Shader.TileMode.REPEAT
//        )
//    }

//
//    protected fun createShader(paint: Paint) {
//        val bitmap: Bitmap = calculateDrawableSizes()!!
//        if (bitmap != null && bitmap.width > 0 && bitmap.height > 0) {
//            shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//            paint.setShader(shader)
//        }
//    }
//
//    protected fun getBitmap(): Bitmap? {
//        var bitmap: Bitmap? = null
//        if (drawable != null) {
//            if (drawable is BitmapDrawable) {
//                bitmap = (drawable as BitmapDrawable).bitmap
//            }
//        }
//        return bitmap
//    }
//
//    fun calculateDrawableSizes(): Bitmap? {
//        val bitmap = getBitmap()
//        if (bitmap != null) {
//            val bitmapWidth = bitmap.width
//            val bitmapHeight = bitmap.height
//            if (bitmapWidth > 0 && bitmapHeight > 0) {
//                val width = Math.round(viewWidth - 2f * borderWidth).toFloat()
//                val height = Math.round(viewHeight - 2f * borderWidth).toFloat()
//                val scale: Float
//                var translateX = 0f
//                var translateY = 0f
//                if (bitmapWidth * height > width * bitmapHeight) {
//                    scale = height / bitmapHeight
//                    translateX = Math.round((width / scale - bitmapWidth) / 2f).toFloat()
//                } else {
//                    scale = width / bitmapWidth.toFloat()
//                    translateY = Math.round((height / scale - bitmapHeight) / 2f).toFloat()
//                }
//                matrix.setScale(scale, scale)
//                matrix.preTranslate(translateX, translateY)
//                matrix.postTranslate(borderWidth, borderWidth)
//                calculate(bitmapWidth, bitmapHeight, width, height, scale, translateX, translateY)
//                return bitmap
//            }
//        }
//        reset()
//        return null
//    }
//
//    fun reset() {
//        bitmapRadius = 0
//        bitmapCenterX = 0f
//        bitmapCenterY = 0f
//    }
//
//    fun calculate(bitmapWidth: Int, bitmapHeight: Int,
//                  width: Float, height: Float,
//                  scale: Float,
//                  translateX: Float, translateY: Float) {
//        bitmapCenterX = Math.round(bitmapWidth / 2f).toFloat()
//        bitmapCenterY = Math.round(bitmapHeight / 2f).toFloat()
//        bitmapRadius = Math.round(width / scale / 2f + 0.5f)
//    }
//
//
//    fun getRoundedCroppedBitmap(bitmap: Bitmap, radius: Int): Bitmap? {
//        val finalBitmap: Bitmap
//        finalBitmap = if (bitmap.width != radius || bitmap.height != radius) Bitmap.createScaledBitmap(bitmap, radius, radius,
//                false) else bitmap
//        val output = Bitmap.createBitmap(finalBitmap.width,
//                finalBitmap.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(output)
//        val paint = Paint()
//        val rect = Rect(0, 0, finalBitmap.width,
//                finalBitmap.height)
//        paint.isAntiAlias = true
//        paint.isFilterBitmap = true
//        paint.isDither = true
//        canvas.drawARGB(0, 0, 0, 0)
//        paint.color = Color.parseColor("#BAB399")
//        canvas.drawCircle(finalBitmap.width / 2 + 0.7f, finalBitmap.height / 2 + 0.7f, finalBitmap.width / 2 + 0.1f, paint)
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//        canvas.drawBitmap(finalBitmap, rect, rect, paint)
//        return output
//    }

    fun getCircularBitmap(bitmap: Bitmap): Bitmap? {
        val output: Bitmap
        output = if (bitmap.width > bitmap.height) {
            Bitmap.createBitmap(bitmap.height, bitmap.height, Bitmap.Config.ARGB_8888)
        } else {
            Bitmap.createBitmap(bitmap.width, bitmap.width, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        var r = 0f
        r = if (bitmap.width > bitmap.height) {
            bitmap.height / 2.toFloat()
        } else {
            bitmap.width / 2.toFloat()
        }
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(r, r, r, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    fun setDrawable1(drawable: Drawable){
        mDrawable1 = drawable

    }

    fun setDrawable2(drawable: Drawable){
        mDrawable2 = drawable
    }

    protected fun getBitmap(drawable: Drawable?): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable != null) {
            if (drawable is BitmapDrawable) {
                bitmap = (drawable as BitmapDrawable).bitmap
            }
        }
        return bitmap
    }


}