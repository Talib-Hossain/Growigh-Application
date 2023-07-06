package com.example.growighassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class OnboardingItemsAdapter(private val onboardingItems: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemsAdapter.OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    override fun onBindViewHolder(holder: OnboardingItemsAdapter.OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val onboardingImage = view.findViewById<ImageView>(R.id.onBoardingImage)
        private val onboardingText = view.findViewById<TextView>(R.id.onboardingText)
        private val onboardingDesc = view.findViewById<TextView>(R.id.onboardingDesc)
        //private val onboardButtonImage = view.findViewById<ImageView>(R.id.onboardingButtonImage)

        fun bind(onboardingItem: OnboardingItem) {

            onboardingImage.setImageResource(onboardingItem.onboardingImage)
            onboardingText.text = onboardingItem.onboardingText
            onboardingDesc.text = onboardingItem.onboardingDesc
            //onboardButtonImage.setImageResource(onboardingItem.nextButtonImage)

        }
    }

}