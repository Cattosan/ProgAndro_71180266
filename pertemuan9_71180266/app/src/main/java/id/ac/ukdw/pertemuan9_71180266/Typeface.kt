package id.ac.ukdw.pertemuan9_71180266

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import org.w3c.dom.*

class Typeface : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context: Context) : super(context){
        init()
    }

    private fun init() {
        if(!isInEditMode()){
            val font: Typeface = Typeface.createFromAsset(context.assets, "font/fa_solid_nh.ttf")
            setTypeface(font)
        }
    }
}