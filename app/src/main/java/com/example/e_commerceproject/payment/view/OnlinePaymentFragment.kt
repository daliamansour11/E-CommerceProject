package com.example.e_commerceproject.payment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceproject.R
import android.app.DownloadManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_commerceproject.payment.client.OrderClient
import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.model.OrderRepository
import com.example.e_commerceproject.payment.viewModel.OrderViewModel
import com.example.e_commerceproject.payment.viewModel.OrderViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class OnlinePaymentFragment : Fragment() {
    lateinit var btnConfirm : Button
    lateinit var back_Arrow : ImageView
    val SECRET_KEY = "sk_test_51LDbiOJJQIjHTLoEsrKxxy2IWsObOhcaKXHww3yZLYPb9uFgRkqg78LRJZVz04glPMQGRUxNx8H32JSXRcOM7t8v00bJ0Owzhy"
    val PUBLISH_KEY = "pk_test_51LDbiOJJQIjHTLoEjTW1t6Oe5mPumiadvnmSyqt2wblD4sP2nf4TY3J8yrnCtvplrQxcpzX8WamsuoUuMx6xlZ0k00qa5GBilX"
    lateinit var paymentSheet : PaymentSheet
    lateinit var customerId: String
    lateinit var ephericalKey: String
    lateinit var clientSecret: String
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

        var onlinepayment = inflater.inflate(R.layout.fragment_online_payment, container, false)
        btnConfirm = onlinepayment.findViewById(R.id.confirmPaymentButton)
        back_Arrow = onlinepayment.findViewById(R.id.online_backarrow)

        return onlinepayment
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnConfirm.setOnClickListener {
            paymentFlow()
            orderViewModel.postOrder(addedOrderModel)
        }

        PaymentConfiguration.init(requireContext(), PUBLISH_KEY)
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        val request: StringRequest = object : StringRequest(
            Request.Method.POST, "https://api.stripe.com/v1/customers",
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    customerId = jsonObject.getString("id")
                    Toast.makeText(
                        requireContext(),
                        "Customer Id: " + customerId,
                        Toast.LENGTH_SHORT
                    ).show()
                    getEphericalKey(customerId)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: HashMap<String, String> = HashMap<String, String>()
                header.put("Authorization", "Bearer $SECRET_KEY")
                return header
            }
        }

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)


        back_Arrow.setOnClickListener {
            Toast.makeText(context, "place your Order ", Toast.LENGTH_LONG).show()
            val payment = PaymentFragment()
            var bundle = Bundle()

            bundle.putString("addedOrderModel", Gson().toJson(addedOrderModel))
            payment.setArguments(bundle)
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, payment)
                ?.commit()
        }
    }

    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(requireContext(), "Payment Success!!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getEphericalKey(id: String){
        val request: StringRequest = object : StringRequest(

            Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
            Response.Listener {response ->
                try{
                    val jsonObject = JSONObject(response)
                    ephericalKey = jsonObject.getString("id")
                    Toast.makeText(requireContext(),"Epherical Key: "+ephericalKey, Toast.LENGTH_SHORT).show()
                    getClientSecret(customerId,ephericalKey)
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: HashMap<String, String> = HashMap<String,String>()
                header.put("Authorization" , "Bearer $SECRET_KEY")
                header.put("Stripe-Version" , "2020-08-27")
                return header
            }

            override fun getParams(): Map<String, String> {
                val param: HashMap<String, String> = HashMap<String,String>()
                param.put("customer" ,customerId)
                return param
            }
        }
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }

    private fun getClientSecret(customerId: String, ephericalKey: String) {
        val request: StringRequest = object : StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
            Response.Listener {response ->
                try{
                    val jsonObject = JSONObject(response)
                    clientSecret = jsonObject.getString("client_secret")
                    Toast.makeText(requireContext(),"Client Secret: "+ clientSecret, Toast.LENGTH_SHORT).show()
                    /// Lauch payment Flow
                    // paymentFlow()
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: HashMap<String, String> = HashMap<String,String>()
                header.put("Authorization" , "Bearer $SECRET_KEY")
                return header
            }

            override fun getParams(): Map<String, String> {
                val param: HashMap<String, String> = HashMap<String,String>()
                param.put("customer" ,customerId)
                param.put("amount" ,"10"+"00")
                param.put("currency" ,"usd")
                param.put("automatic_payment_methods[enabled]" ,"true")
                return param
            }
        }

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }

    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(clientSecret, PaymentSheet.Configuration("ITI",
            PaymentSheet.CustomerConfiguration(customerId,ephericalKey)))
    }

}
