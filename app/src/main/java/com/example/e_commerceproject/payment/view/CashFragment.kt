package com.example.e_commerceproject.payment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.details.viewmodel.DetailsViewModel
import com.example.e_commerceproject.details.viewmodel.DetailsViewModelFactory
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.network.remotesource.DetailsRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.payment.client.OrderClient
import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.model.OrderRepository
import com.example.e_commerceproject.payment.viewModel.CashViewModel
import com.example.e_commerceproject.payment.viewModel.CashViewModelFactory
import com.example.e_commerceproject.payment.viewModel.OrderViewModel
import com.example.e_commerceproject.payment.viewModel.OrderViewModelFactory
import com.example.e_commerceproject.profile.view.ProfileFragment



import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
const val COUPON =""

class CashFragment : Fragment() {
 lateinit var  cartViewModel: CartViewModel
    lateinit var placeorderbtn : Button
    lateinit var validate_btn : TextView
    lateinit var back_arrow : ImageView
 lateinit var   coupons: EditText
 lateinit var   subTotal: TextView
 lateinit var   shippingfees: TextView
 lateinit var   discount: TextView
 lateinit var   totalprice: TextView
  lateinit var viewModel : CashViewModel
  var coupon =""
    var subtotal : Double = 0.0
    lateinit var addedOrderModel: AddedOrderModel
    lateinit var orderViewModel: OrderViewModel
    lateinit var orderVmFactory: OrderViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            class Token : TypeToken<AddedOrderModel>()
            addedOrderModel = Gson().fromJson(bundle.getString("addedOrderModel"), Token().type)
        }
        orderVmFactory = OrderViewModelFactory(
            OrderRepository.getInstance(
                OrderClient.getInstance(),
                requireContext()
            ))
        orderViewModel = ViewModelProvider(this, orderVmFactory).get(OrderViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var cashFrag=  inflater.inflate(R.layout.fragment_cash, container, false)
        placeorderbtn = cashFrag.findViewById(R.id.placeorder_btn)
        back_arrow = cashFrag.findViewById(R.id.cash_backarrow)
        subTotal= cashFrag.findViewById(R.id.subtotal)
        shippingfees= cashFrag.findViewById(R.id.shippingfee)
        discount= cashFrag.findViewById(R.id.discount_cost)
        totalprice= cashFrag.findViewById(R.id.totalprice)
        validate_btn= cashFrag.findViewById(R.id.validate)
        return cashFrag
        coupons= cashFrag.findViewById(R.id.Coupon)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var args = this.arguments
         coupon= args?.get("COUPON").toString()

        val retrofitService = RetrofitService.getInstance()
        val mRepository = CouponsRepository(retrofitService)

        viewModel = ViewModelProvider(this, CashViewModelFactory(mRepository)).get(CashViewModel::class.java)
        viewModel.getMyCoupons()
        placeorderbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                orderViewModel.postOrder(addedOrderModel)
                Toast.makeText(context, "place your Order ", Toast.LENGTH_LONG).show()
                viewModel.myCoupons.observe(viewLifecycleOwner,  {
                    Log.d("TAG", "inside observe")
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
                })
            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }
        })
        back_arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                Toast.makeText(context, "place your Order ", Toast.LENGTH_LONG).show()
                val payment= PaymentFragment()
                var bundle = Bundle()

                payment.arguments = bundle


                bundle.putString("addedOrderModel", Gson().toJson(addedOrderModel))
                payment.setArguments(bundle)

                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, payment)
                    ?.commit()

            }

        })

}


}