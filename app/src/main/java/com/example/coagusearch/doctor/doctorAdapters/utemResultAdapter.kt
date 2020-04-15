package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R

class utemResultAdapter(var companies : MutableList<String>) : RecyclerView.Adapter<utemResultAdapter.ResultCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultCardViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.utem_result_bar,parent,false)
        return ResultCardViewHolder(v)
    }
    override fun getItemCount(): Int {
        return companies.size
    }
    fun add(item:String, position:Int) {
        companies.add(position, item)
        notifyItemInserted(position)
    }
    fun remove(item:String) {
        val position = companies.indexOf(item)
        companies.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: ResultCardViewHolder, position: Int) {
        val company = companies[position]
        if(company.equals("a")){
            holder.valueImage.setImageResource(R.drawable.normalvaluecopy)
            var param1 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            var param2 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2f
            )
            var param3 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3F
            )
            val lparams =
                holder.thumpImage.getLayoutParams() as ConstraintLayout.LayoutParams

            lparams.horizontalBias = 0.25f
            holder.thumpImage.setLayoutParams(lparams)
            holder.barStart.layoutParams=param1
            holder.barEnd.layoutParams=param3
            holder.blueRegion.layoutParams=param2
            holder.normalRangeStart.layoutParams=param1
            holder.normalRangeEnd.layoutParams=param3
            holder.dummy.layoutParams=param2
        }
        else if(company.equals("b")){
            holder.valueImage.setImageResource(R.drawable.highvaluecopy)
            var param1 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            var param2 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2F
            )
            var param3 = LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                4F
            )
            val lparams =
                holder.thumpImage.getLayoutParams() as ConstraintLayout.LayoutParams

            lparams.horizontalBias = 0.75f
            holder.thumpImage.setLayoutParams(lparams)
            holder.thumpImage.setImageResource(R.drawable.thumpred)
            holder.barStart.layoutParams=param1
            holder.barEnd.layoutParams=param3
            holder.blueRegion.layoutParams=param2
            holder.normalRangeStart.layoutParams=param1
            holder.normalRangeEnd.layoutParams=param3
            holder.dummy.layoutParams=param2

        }


        else {
            holder.valueImage.setImageResource(R.drawable.normalvaluecopy)
            var param1 = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            var param2 = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2f
            )
            var param3 = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3F
            )
            val lparams =
                holder.thumpImage.getLayoutParams() as ConstraintLayout.LayoutParams

            if (company.equals("a")) {
                lparams.horizontalBias = 0.25f
            } else if (company.equals("c")) {
                lparams.horizontalBias = 0.10f
            } else {
                lparams.horizontalBias = 0.05f
            }
            holder.thumpImage.setLayoutParams(lparams)
            holder.barStart.layoutParams = param1
            holder.barEnd.layoutParams = param3
            holder.blueRegion.layoutParams = param2
            holder.normalRangeStart.layoutParams = param1
            holder.normalRangeEnd.layoutParams = param3
            holder.dummy.layoutParams = param2
        }

    }

    class ResultCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var valueImage = itemView.findViewById<ImageView>(R.id.valueImage)
        var valueName = itemView.findViewById<TextView>(R.id.valueName)
        var resultValue = itemView.findViewById<TextView>(R.id.value)
        var barStart = itemView.findViewById<TextView>(R.id.start)
        var barEnd = itemView.findViewById<TextView>(R.id.end)
        var blueRegion = itemView.findViewById<TextView>(R.id.blueRegion)
        var thumpImage = itemView.findViewById<ImageView>(R.id.thump)
        var startValue = itemView.findViewById<TextView>(R.id.startValue)
        var endValue = itemView.findViewById<TextView>(R.id.endValue )
        var normalRangeStart = itemView.findViewById<TextView>(R.id.normalRangeStart)
        var normalRangeEnd = itemView.findViewById<TextView>(R.id.normalRangeEnd)
        var dummy = itemView.findViewById<TextView>(R.id.dummy )
    }
}