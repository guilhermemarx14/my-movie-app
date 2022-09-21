package com.guilhermemarx14.mymovieapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
    private var fragments: MutableList<Fragment> = mutableListOf()
    private var fragmentTitles: MutableList<String> = mutableListOf()

    fun addFragment(fragment: Fragment, title:String){
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    override fun getItem(position: Int): Fragment = fragments[position]


    override fun getCount(): Int =  fragments.size

    override fun getPageTitle(position: Int): CharSequence = fragmentTitles[position]


}