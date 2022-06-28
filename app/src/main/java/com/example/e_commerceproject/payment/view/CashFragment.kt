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
import com.example.e_commerceproject.home.viewmodel.CouponsViewModel
import com.example.e_commerceproject.home.viewmodel.CouponsViewModelFactory
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

const val COUPON = ""

class CashFragment : Fragment() {
    lateinit var cartViewModel: CartViewModel
    lateinit var placeorderbtn: Button
    lateinit var validate_btn: TextView
    lateinit var back_arrow: ImageView
    lateinit var coupons: TextView
    lateinit var subTotal: TextView
    lateinit var totalprice: TextView
    lateinit var discount_cost: TextView
    lateinit var viewModel: CashViewModel

    lateinit var  couponsViewModel: CouponsViewModel
    override fun setInitialSavedState(state: SavedState?) {

    }
    var coupon = ""

    var totalPric = 0.0
    var priceRuleId : Long = 507328175
    lateinit var addedOrderModel: AddedOrderModel
    lateinit var orderViewModel: OrderViewModel
    lateinit var orderVmFactory: OrderViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            class Token : TypeToken<AddedOrderModel>()
            addedOrderModel = Gson().fromJson(bundle.getString("addedOrderModel"), Token().type)
        }
        orderVmFactory = OrderViewModelFactory(
            OrderRepository.getInstance(
                OrderClient.getInstance(),
                requireContext()
            )
        )
        orderViewModel = ViewModelProvider(this, orderVmFactory).get(OrderViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var cashFrag = inflater.inflate(R.layout.fragment_cash, container, false)
        placeorderbtn = cashFrag.findViewById(R.id.placeorder_btn)
        back_arrow = cashFrag.findViewById(R.id.cash_backarrow)
        subTotal = cashFrag.findViewById(R.id.subtotal)
        totalprice = cashFrag.findViewById(R.id.totalprice)
        discount_cost = cashFrag.findViewById(R.id.discount_cost)
        validate_btn = cashFrag.findViewById(R.id.validate)
        coupons = cashFrag.findViewById(R.id.Coupon)
        return cashFrag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var args = this.arguments
        if (args == null) {
        } else {
            totalPric = args?.get("TOTAL_PRICE") as Double
            Log.i("TAG", "onViewCreatedmmmmmmmmmmmmmmmmmmmmmmm: ${totalPric}")
        }

        subTotal.text = "${totalPric} EGP"
        var priceRule: Int = -10



        val retrofitService = RetrofitService.getInstance()
        val mRepository = CouponsRepository(retrofitService)

        viewModel = ViewModelProvider(this, CashViewModelFactory(mRepository)).get(CashViewModel::class.java)
        viewModel.getMyCoupons()
        viewModel.myCoupons.observe(viewLifecycleOwner, {
            Log.d("TAG", "inside observe")
            Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")

            if(it != null ){
                priceRuleId = it.discount_codes[1].price_rule_id

                coupons.text = it.discount_codes[1].code
            }

        })
        couponsViewModel = ViewModelProvider(this, CouponsViewModelFactory(mRepository)).get(CouponsViewModel::class.java)

        couponsViewModel.validateCoupons()
        couponsViewModel.validate_Response.observe(viewLifecycleOwner, {
            Log.d("TAG", "inside observe")
            Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")

            if(it != null ){
                validate_btn.text = it.discount_codes[1].usage_count.toString()

            }

        })
        Log.i("TAG", "onViewCreated:rrrrrrrrrrrrbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ${priceRuleId}")


        couponsViewModel.getPriceRuleDiscountValue("${1089622311051}")
        couponsViewModel.priceRulesValue.observe(viewLifecycleOwner, {
            Log.d("TAG", "inside observe")
            Log.i("TAG", "onViewCreated:rrrrrrrrrrrrbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ${it}")

            if(it != null ){
                totalprice.text = it.price_rule.value
                Log.i("TAG", "onViewCreated: bbbbbbbbccccccccccccccccccccccc ${priceRule}")
                priceRule = (it.price_rule.value).toInt()
                Log.i("TAG", "onViewCreated: uuuuuuuuuuuuuuuuuuu ${priceRule}")

                Log.i("TAG", "onViewCreated: priceRulesValuesssssssssssss ${it.price_rule.value} ")

            }
        })
        Log.i("TAG", "onViewCreated: uuuuuuuuuuuuuuuuuuuttttttttttttttttt ${priceRule}")

        discount_cost.text = "${totalPric.plus(priceRule)} EGP"




        placeorderbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //addedOrderModel.line_items[0].price = "${discount_cost.text}"
                orderViewModel.postOrder(addedOrderModel)
                Toast.makeText(context, "place your Order ", Toast.LENGTH_LONG).show()
                viewModel.myCoupons.observe(viewLifecycleOwner, {
                    Log.d("TAG", "inside observe")
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
                })
                val profileFragment = ProfileFragment()

                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, profileFragment)
                    ?.commit()
            }

        })
        back_arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val payment = PaymentFragment()
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