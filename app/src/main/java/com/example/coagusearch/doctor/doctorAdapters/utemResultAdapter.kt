package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataCategoryResponse

class utemResultAdapter(var companies: MutableList<UserBloodTestDataCategoryResponse>) :
    RecyclerView.Adapter<utemResultAdapter.ResultCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultCardViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.utem_result_bar, parent, false)
        return ResultCardViewHolder(v)
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    override fun onBindViewHolder(holder: ResultCardViewHolder, position: Int) {
        val company = companies[position]
        if (company.ishigh()){
            holder.valueImage.setImageResource(R.drawable.highvaluecopy)
            holder.thumpImage.setImageResource(R.drawable.thumpred)
        }
        else if (company.isLow()){
            holder.valueImage.setImageResource(R.drawable.lowvaluecopy)
            holder.thumpImage.setImageResource(R.drawable.thumpred)
        }
        else holder.valueImage.setImageResource(R.drawable.normalvaluecopy)
        holder.valueName.text=company.categoryName
        var param1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            company.startWeight().toFloat()
        )
        var param2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            company.midWeight().toFloat()
        )
        var param3 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            company.endWeight().toFloat()
        )
        val lparams = holder.thumpImage.getLayoutParams() as ConstraintLayout.LayoutParams
        lparams.horizontalBias = company.valueBias().toFloat()
        holder.thumpImage.setLayoutParams(lparams)
        holder.barStart.layoutParams = param1
        holder.barEnd.layoutParams = param3
        holder.blueRegion.layoutParams = param2
        holder.normalRangeStart.layoutParams = param1
        holder.normalRangeEnd.layoutParams = param3
        holder.dummy.layoutParams = param2
        holder.resultValue.text=company.value.toString()
        holder.startValue.text=company.minimumValue.toInt().toString()
        holder.endValue.text=company.maximumValue.toInt().toString()
        holder.normalRangeStart.text=company.optimalMinimumValue.toInt().toString()
        holder.normalRangeEnd.text=company.optimalMaximumValue.toInt().toString()
    }

    class ResultCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var valueImage = itemView.findViewById<ImageView>(R.id.valueImage)
        var valueName = itemView.findViewById<TextView>(R.id.valueName)
        var resultValue = itemView.findViewById<TextView>(R.id.value)
        var barStart = itemView.findViewById<TextView>(R.id.start)
        var barEnd = itemView.findViewById<TextView>(R.id.end)
        var blueRegion = itemView.findViewById<TextView>(R.id.blueRegion)
        var thumpImage = itemView.findViewById<ImageView>(R.id.thump)
        var startValue = itemView.findViewById<TextView>(R.id.startValue)
        var endValue = itemView.findViewById<TextView>(R.id.endValue)
        var normalRangeStart = itemView.findViewById<TextView>(R.id.normalRangeStart)
        var normalRangeEnd = itemView.findViewById<TextView>(R.id.normalRangeEnd)
        var dummy = itemView.findViewById<TextView>(R.id.dummy)
    }
}