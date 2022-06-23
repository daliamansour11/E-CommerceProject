package com.example.e_commerceproject.authentication.register.model

import android.os.Parcelable
import com.example.e_commerceproject.authentication.login.model.Customerr
import com.example.e_commerceproject.authentication.login.model.MarketingConsent
import com.google.gson.annotations.SerializedName

data class CustomerModel(val customer: Customer2)

/*
data class Customer (

    val id: Long,
    var email: String,
    var accepts_marketing: Boolean,
    val created_at: String,
    val updated_at: String,
    var first_name: String,
    var last_name: String,
    val orders_count: Long,
    val state: String,
    val total_spent: String,
    val last_order_id: Any?,
    val note: Any?,
    val verified_email: Boolean,
    val multipass_identifier: Any?,
    val tax_exempt: Boolean,
    var phone: String,
    var tags: String,
    val last_order_name: Any?,
    val currency: String,
    var addresses: List<Address>,
    val accepts_marketing_updated_at: String,
    val marketing_opt_in_level: Any?,
    val tax_exemptions: List<Any?>,
    val sms_marketing_consent: SMSMarketingConsent,
    val admin_graphql_api_id: String,
    val default_address: Address
)
data class Address (
    var id: Long,
    var customer_id: Long,
    var first_name: String,
    var last_name: String,
    var company: Any?,
    var address1: String,
    var address2: Any?,
    var city: String,
    var province: String,
    var country: String,
    var zip: String,
    var phone: String,
    var name: String,
    var province_code: String,
    var country_code: String,
    var country_name: String,
    var default: Boolean
)


data class SMSMarketingConsent (
    val state: String,
    val opt_in_level: String,
    val consent_updated_at: Any? = null,
    val consent_collected_from: String
)

data class Post(val id : Int , val title : String , var body : String , var userId : Int)



*/

/*
data class Customer1(
    val id: Long ? = null,
    val email: String? = null,

    val accepts_marketing: Boolean? = null,

    val created_at: String? = null,

    val updated_at: String? = null,

    val first_name: String? = null,

    val last_name: String? = null,

    val orders_count: Long? = null,

    val state: String? = null,

    val total_spent: String? = null,

    val last_order_id: Any? = null,

    val note: Any? = null,

    val verified_email: Boolean? = null,

    val multipass_identifier: Any? = null,

    val tax_exempt: Boolean? = null,

    val phone: String? = null,
    val tags: String? = null,

    val last_order_name: Any? = null,

    val currency: String? = null,
    val addresses: List<Address1>? = null,

    val accepts_marketing_updated_at: String? = null,

    val marketing_opt_in_level: Any? = null,

    val tax_exemptions: List<Any?>? = null,

    val email_marketing_consent: MarketingConsent1? = null,

    val sms_marketing_consent: MarketingConsent1? = null,

    val admin_graphql_api_id: String? = null,

    val default_address: Address1? = null
)

data class Address1 (
    val id: Long? = null,

    val customer_id: Long? = null,

    val first_name: String? = null,

    val last_name: String? = null,

    val company: Any? = null,
    val address1: String? = null,
    val address2: Any? = null,
    val city: String? = null,
    val province: String? = null,
    val country: String? = null,
    val zip: String? = null,
    val phone: String? = null,
    val name: String? = null,

    val province_code: Any? = null,

    val country_code: String? = null,

    val country_name: String? = null,

    val default: Boolean? = null
)

data class MarketingConsent1 (
    val state: String? = null,

    val opt_in_level: String? = null,

    val consent_updated_at: Any? = null,

    val consent_collected_from: String? = null
)
*/


data class Customer2(
    val accepts_marketing: Boolean? = null,
    val accepts_marketing_updated_at: String? = null,
    var addresses: List<CustomerAddress>? = null,
    val admin_graphql_api_id: String? = null,
    val created_at: String? = null,
    val currency: String? = null,
    val default_address: DefaultAddress? = null,
    val email: String?=null,
    val first_name: String?=null,
    val id: Long? = null,
    val last_name: String?=null,
    val last_order_id: Any? = null,
    val last_order_name: Any? = null,
    val marketing_opt_in_level: Any? = null,
    val multipass_identifier: Any? = null,
    val note: Any? = null,
    val orders_count: Int? = null,
    val phone: String?=null,
    val sms_marketing_consent: SmsMarketingConsent? = null,
    val state: String? = null,
    val tags: String?=null,
    val tax_exempt: Boolean? = null,
    val tax_exemptions: List<Any>? = null,
    val total_spent: String? = null,
    val updated_at: String? = null,
    val verified_email: Boolean = true,
)

data class CustomerAddress(
    val address1: String? = null,
    val address2: Any? = null,
    val city: String? = null,
    val company: Any? = null,
    val country: String? = null,
    val country_code: String? = null,
    val country_name: String? = null,
    val customer_id: String? = null,
    val default: Boolean? = null,
    val first_name: String? = null,
    val id: Long? = null,
    val last_name: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val province: String? = null,
    val province_code: String? = null,
    val zip: String? = null
)
data class DefaultAddress(
    val address1: String? = null,
    val address2: Any? = null,
    val city: String? = null,
    val company: Any? = null,
    val country: String? = null,
    val country_code: String? = null,
    val country_name: String? = null,
    val customer_id: Long? = null,
    val default: Boolean? = null,
    val first_name: String? = null,
    val id: Long? = null,
    val last_name: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val province: String? = null,
    val province_code: String? = null,
    val zip: String? = null
)


data class SmsMarketingConsent(
    val consent_collected_from: String? = null,
    val consent_updated_at: Any? = null,
    val opt_in_level: String? = null,
    val state: String? = null
)


/*
*
*  Log.i("TAG", "onViewCreated: ${firstNameEdittxt.text.toString()}  ${lastNameEdittxt.text.toString()} ${phoneEditText.text.toString()} ${passwordEditText.text.toString()}  ${emailEditText.text.toString()}")
            if( firstNameEdittxt.text.toString() != null && lastNameEdittxt.text.toString() != null && emailEditText.text.toString() != null &&passwordEditText.text.toString() != null &&confirmPasswordEditText.text.toString() != null &&phoneEditText.text.toString() != null ){

                viewModel.postRegisterCustomer(customerdata)
                viewModel.customerdata.observe(viewLifecycleOwner, {

                    //Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
                    Log.i("TAG", "onViewCreated: ${it.customer.phone}")
                    Toast.makeText(requireContext() , "Registered Successfuly " , Toast.LENGTH_SHORT).show()
                })
            } else{
                Toast.makeText(requireContext() , "Please enter all the fileds" , Toast.LENGTH_SHORT).show()
            }
            *
            *
            *
            *
            *  first_name = firstNameEdittxt.text.toString() ,
            last_name = lastNameEdittxt.text.toString() ,
            phone = phoneEditText.text.toString(),
            tags = passwordEditText.text.toString(),
            email = emailEditText.text.toString()
            *
            *
            *
            *
             registernowbtn = view.findViewById(R.id.registernowbtn)
        firstNameEdittxt = view.findViewById(R.id.register_first_name_edittext)
        firstNameEdittxt = view.findViewById(R.id.register_first_name_edittext)
        emailEditText = view.findViewById(R.id.register_email_edittext)
        passwordEditText = view.findViewById(R.id.register_password_edittext)
        confirmPasswordEditText = view.findViewById(R.id.register_confirmpassword_edittext)
        phoneEditText = view.findViewById(R.id.register_phone_edittext)

*
*
*
*  lateinit var firstNameEdittxt :EditText
    lateinit var firstNameEdittxt :EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmPasswordEditText: EditText
    lateinit var phoneEditText: EditText
*
* */