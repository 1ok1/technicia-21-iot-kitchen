package com.technichia.iot.kitchen.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.technichia.iot.kitchen.android.databinding.FragmentProductListingBinding

class ProductListingFragment : Fragment() {
    private var binding: FragmentProductListingBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this creates a vertical layout Manager
        binding?.recyclerview?.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Product>()

        // This loop will create 20 Views containing
        // the image with the count of view
        data.add(Product(R.mipmap.cyclinder, "Gas Cylinder", "17.6 Kg", "1 kg", true))
        data.add(Product(R.mipmap.daal, "Daal", "1Kg", "100gm",true))
        data.add(Product(R.mipmap.horlicks, "Horlicks", "1Kg", "1kg",false))
        data.add(Product(R.mipmap.oil, "Oil", "1L", "100ml",true))
        data.add(Product(R.mipmap.wheat, "Wheat Flour", "5Kg", "7Kg",false))

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        binding?.recyclerview?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}