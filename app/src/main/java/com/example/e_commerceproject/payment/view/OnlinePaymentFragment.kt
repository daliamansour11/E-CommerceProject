package com.example.e_commerceproject.payment.view

import androidx.fragment.app.Fragment

class OnlinePaymentFragment : Fragment() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_online_payment, container, false)
//    }
//private val viewModel: OnlinePaymentViewModel by viewModels {
//    OnlinePaymentViewModel.Factory(application)
//}
//
//
//
//
//    private val settings: StripeSettings  {
//        StripeSettings(applicationContext)
//    }
//
//    private val stripe: Stripe by lazy {
//        StripeFactory(requireContext()).create()
//    }
//
//    private val paymentSession: PaymentSession by lazy {
//        PaymentSession(
//            this,
//            PaymentSessionConfig.Builder()
//                .setPrepopulatedShippingInfo(DEFAULT_SHIPPING_INFO)
//                .setShippingInformationValidator(ShippingInfoValidator())
//                .setShippingMethodsFactory(ShippingMethodsFactory())
//                .setBillingAddressFields(BillingAddressFields.PostalCode)
//                .setShouldShowGooglePay(args.isGooglePayReady)
//                .build()
//        )
//    }
//    private val args: CheckOutContract.Args {
//        requireNotNull(intent?.extras?.getParcelable(CheckOutContract.EXTRA_ARGS))
//    }
//
//    private val storeCart: DraftOrder by lazy { args.cart }
//
//    private val paymentsClient: PaymentsClient by lazy {
//        PaymentsClientFactory(this).create()
//    }
//
//    private val googlePayJsonFactory: GooglePayJsonFactory by lazy {
//        GooglePayJsonFactory(this)
//    }
//    private var shippingCosts = 0L
//
//    private val totalPrice: String
//        get() = storeCart.total_price + shippingCosts
//
//    private var paymentSessionData: PaymentSessionData? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val selectCustomization = PaymentAuthConfig.Stripe3ds2ButtonCustomization.Builder()
//            .setBackgroundColor("#EC4847")
//            .setTextColor("#000000")
//            .build()
//        val uiCustomization =
//            PaymentAuthConfig.Stripe3ds2UiCustomization.Builder.createWithAppTheme(this)
//                .setButtonCustomization(
//                    selectCustomization,
//                    PaymentAuthConfig.Stripe3ds2UiCustomization.ButtonType.SELECT
//                )
//                .build()
//        PaymentAuthConfig.init(
//            PaymentAuthConfig.Builder()
//                .set3ds2Config(
//                    PaymentAuthConfig.Stripe3ds2Config.Builder()
//                        .setUiCustomization(uiCustomization)
//                        .build()
//                )
//                .build()
//        )
//
//        initPaymentSession()
//
//        updateCartItems(totalPrice.toInt())
//
//        updateConfirmPaymentButton(totalPrice)
//
//        viewBinding.buttonAddShippingInfo.setOnClickListener {
//            paymentSession.presentShippingFlow()
//        }
//        viewBinding.buttonAddPaymentMethod.setOnClickListener {
//            paymentSession.presentPaymentMethodSelection()
//        }
//
//        viewBinding.buttonConfirmPayment.setOnClickListener {
//            paymentSessionData?.let {
//                createPaymentIntent(it)
//            }
//        }
//        viewBinding.buttonConfirmSetup.setOnClickListener {
//            paymentSessionData?.let {
//                createSetupIntent(it)
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        val isPaymentIntentResult = stripe.onPaymentResult(
//            requestCode,
//            data,
//            object : ApiResultCallback<PaymentIntentResult> {
//                override fun onSuccess(result: PaymentIntentResult) {
//                    stopLoading()
//                    processStripeIntent(
//                        result.intent,
//                        isAfterConfirmation = true,
//                        paymentMethod = null
//                    )
//                }
//
//                override fun onError(e: Exception) {
//                    stopLoading()
//                    displayError(e.message)
//                }
//            }
//        )
//
//        if (isPaymentIntentResult) {
//            startLoading()
//        } else {
//            val isSetupIntentResult = stripe.onSetupResult(
//                requestCode,
//                data,
//                object : ApiResultCallback<SetupIntentResult> {
//                    override fun onSuccess(result: SetupIntentResult) {
//                        stopLoading()
//                        processStripeIntent(
//                            result.intent,
//                            isAfterConfirmation = true,
//                            paymentMethod = null
//                        )
//                    }
//
//                    override fun onError(e: Exception) {
//                        stopLoading()
//                        displayError(e.message)
//                    }
//                }
//            )
//            if (!isSetupIntentResult && data != null) {
//                paymentSession.handlePaymentData(requestCode, resultCode, data)
//            }
//        }
//
//        if (requestCode == LOAD_PAYMENT_DATA_REQUEST_CODE) {
//            when (resultCode) {
//                Activity.RESULT_OK -> {
//                    if (data != null) {
//                        handleGooglePayResult(data)
//                    }
//                }
//                else -> {
//                    resetCheckout()
//                }
//            }
//        }
//    }
//
//    private fun handleGooglePayResult(data: Intent) {
//        val paymentData = PaymentData.getFromIntent(data) ?: return
//        val paymentDataJson = JSONObject(paymentData.toJson())
//
//        val paymentMethodCreateParams =
//            PaymentMethodCreateParams.createFromGooglePay(paymentDataJson)
//
//        startLoading()
//        viewModel.createPaymentMethod(
//            paymentMethodCreateParams
//        ).observe(this) { result ->
//            result.fold(
//                onSuccess = {
//                    payWithPaymentMethod(it)
//                },
//                onFailure = {
//                    displayError(it)
//                    resetCheckout()
//                }
//            )
//        }
//    }
//
//    private fun updateConfirmPaymentButton(cartTotal: String) {
//        viewBinding.buttonConfirmPayment.text = getString(
//            R.string.pay_label,
//            StoreUtils.getPriceString(cartTotal, null)
//        )
//    }
//
//    private fun updateCartItems(
//        totalPrice: Int,
//        shippingCost: Int = 0
//    ) {
//        viewBinding.cartItems.removeAllViewsInLayout()
//
//        val currencySymbol = storeCart.currency.getSymbol(Locale.US)
//
//        addLineItems(currencySymbol, storeCart.lineItems)
//
//        addLineItems(
//            currencySymbol,
//            listOf(
//                StoreLineItem(
//                    getString(R.string.checkout_shipping_cost_label),
//                    1,
//                    shippingCost.toLong(),
//                    false
//                )
//            )
//        )
//
//        val totalViewBinding = CartItemBinding.inflate(
//            layoutInflater,
//            viewBinding.cartItems,
//            false
//        ).also {
//            it.label.text = getString(R.string.checkout_total_cost_label)
//            it.totalPrice.text = getDisplayPrice(currencySymbol, totalPrice)
//        }
//        viewBinding.cartItems.addView(totalViewBinding.root)
//    }
//
//    private fun addLineItems(currencySymbol: String, items: List<StoreLineItem>) {
//        items.forEach { item ->
//            val cartItemViewBinding = CartItemBinding.inflate(
//                layoutInflater,
//                viewBinding.cartItems,
//                false
//            )
//            val displayPrice = getDisplayPrice(currencySymbol, item.totalPrice.toInt())
//            val cartItem = cartItemViewBinding.cartItem
//            cartItem.contentDescription = if (item.isProduct) {
//                getString(
//                    R.string.cart_item_description,
//                    item.quantity,
//                    item.description,
//                    displayPrice
//                )
//            } else {
//                getString(R.string.shipping_price, displayPrice)
//            }
//            fillOutCartItemView(item, cartItemViewBinding, currencySymbol)
//            viewBinding.cartItems.addView(cartItemViewBinding.root)
//        }
//    }
//
//    private fun fillOutCartItemView(
//        item: StoreLineItem,
//        viewBinding: CartItemBinding,
//        currencySymbol: String
//    ) {
//        viewBinding.label.text = item.description
//
//        if (item.isProduct) {
//            val quantityPriceString = "X " + item.quantity + " @"
//            viewBinding.quantity.text = quantityPriceString
//            // unit price
//            viewBinding.unitPrice.text = getDisplayPrice(currencySymbol, item.unitPrice.toInt())
//        }
//
//        // total price
//        viewBinding.totalPrice.text = getDisplayPrice(currencySymbol, item.totalPrice.toInt())
//    }
//
//    private fun createCreatePaymentIntentParams(
//        shippingInformation: ShippingInformation?,
//        stripeAccountId: String?
//    ): Map<String, Any> {
//        return mapOf(
//            "country" to StripeSettings.COUNTRY,
//            "customer_id" to args.customerId,
//            "products" to storeCart.line_items.flatMap { lineItem ->
//                mutableListOf<String>().also { products ->
//                    repeat(lineItem.quantity) {
//                        products.add(lineItem)
//                    }
//                }
//            }
//        ).plus(
//            shippingInformation?.let {
//                mapOf("shipping" to it.toParamMap())
//            }.orEmpty()
//        ).plus(
//            stripeAccountId?.let {
//                mapOf("stripe_account" to it)
//            }.orEmpty()
//        )
//    }
//
//    private fun createSetupIntentParams(
//        stripeAccountId: String?
//    ): Map<String, Any> {
//        return mapOf(
//            "customer_id" to args.customerId,
//            "country" to StripeSettings.COUNTRY
//        ).plus(
//            stripeAccountId?.let {
//                mapOf("stripe_account" to it)
//            }.orEmpty()
//        )
//    }
//
//    private fun createPaymentIntent(paymentSessionData: PaymentSessionData) {
//        when {
//            paymentSessionData.useGooglePay -> payWithGoogle()
//            else -> {
//                paymentSessionData.paymentMethod?.let { paymentMethod ->
//                    payWithPaymentMethod(paymentMethod)
//                } ?: displayError("No payment method selected")
//            }
//        }
//    }
//
//    private fun createSetupIntent(paymentSessionData: PaymentSessionData) {
//        val paymentMethod = paymentSessionData.paymentMethod
//        if (paymentMethod == null) {
//            displayError("No payment method selected")
//            return
//        }
//
//        startLoading()
//        viewModel.createSetupIntent(
//            createSetupIntentParams(settings.stripeAccountId)
//        ).observe(this) { result ->
//            stopLoading()
//
//            result.fold(
//                onSuccess = { response ->
//                    onStripeIntentClientSecretResponse(
//                        response,
//                        paymentMethod
//                    )
//                },
//                onFailure = ::displayError
//            )
//        }
//    }
//
//    private fun payWithPaymentMethod(paymentMethod: PaymentMethod) {
//        startLoading()
//        viewModel.createPaymentIntent(
//            createCreatePaymentIntentParams(
//                paymentSessionData?.shippingInformation,
//                settings.stripeAccountId
//            )
//        ).observe(this) { result ->
//            result.fold(
//                onSuccess = { response ->
//                    onStripeIntentClientSecretResponse(response, paymentMethod)
//                },
//                onFailure = ::displayError
//            )
//        }
//    }
//
//    private fun displayError(throwable: Throwable) = displayError(throwable.message)
//
//    private fun displayError(errorMessage: String?) {
//        MaterialAlertDialogBuilder(requireContext())
//            .setTitle("Error")
//            .setMessage(errorMessage)
//            .setNeutralButton("OK") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .create()
//            .show()
//    }
//
//    private fun resetCheckout() {
//        paymentSessionData = null
//
////        viewBinding.progressBar.isInvisible = true
////
////        viewBinding.buttonConfirmPayment.tag = false
////        viewBinding.buttonConfirmPayment.isEnabled = false
////        viewBinding.buttonConfirmSetup.tag = false
////        viewBinding.buttonConfirmSetup.isEnabled = false
//
//        // reset payment method and shipping if authentication fails
//        initPaymentSession()
//        viewBinding.buttonAddPaymentMethod.text = getString(R.string.add_payment_method)
//        viewBinding.buttonAddShippingInfo.text = getString(R.string.add_shipping_details)
//    }
//
//    private fun processStripeIntent(
//        stripeIntent: StripeIntent,
//        isAfterConfirmation: Boolean = false,
//        paymentMethod: PaymentMethod?
//    ) {
//        if (stripeIntent.requiresAction()) {
//            stripe.handleNextActionForPayment(this, stripeIntent.clientSecret!!)
//        } else if (stripeIntent.requiresConfirmation()) {
//            confirmStripeIntent(
//                stripeIntent.id!!,
//                paymentMethod
//            )
//        } else if (stripeIntent.status == StripeIntent.Status.Succeeded) {
//            if (stripeIntent is PaymentIntent) {
//                finishPayment()
//            } else if (stripeIntent is SetupIntent) {
//                finishSetup()
//            }
//        } else if (stripeIntent.status == StripeIntent.Status.RequiresPaymentMethod) {
//            if (isAfterConfirmation) {
//                resetCheckout()
//            } else {
//                if (stripeIntent is PaymentIntent) {
//                    stripe.confirmPayment(
//                        this,
//                        ConfirmPaymentIntentParams.createWithPaymentMethodId(
//                            paymentMethodId = paymentMethod?.id.orEmpty(),
//                            clientSecret = requireNotNull(stripeIntent.clientSecret)
//                        )
//                    )
//                } else if (stripeIntent is SetupIntent) {
//                    stripe.confirmSetupIntent(
//                        this,
//                        ConfirmSetupIntentParams.create(
//                            paymentMethodId = paymentMethod?.id.orEmpty(),
//                            clientSecret = requireNotNull(stripeIntent.clientSecret)
//                        )
//                    )
//                }
//            }
//        } else {
//            displayError(
//                "Unhandled Payment Intent Status: " + stripeIntent.status.toString()
//            )
//        }
//    }
//
//    private fun confirmStripeIntent(
//        stripeIntentId: String,
//        paymentMethod: PaymentMethod?
//    ) {
//        val params = mapOf(
//            "payment_intent_id" to stripeIntentId
//        ).plus(
//            settings.stripeAccountId?.let {
//                mapOf("stripe_account" to it)
//            }.orEmpty()
//        )
//
//        startLoading()
//        viewModel.confirmStripeIntent(
//            params
//        ).observe(this) { result ->
//            stopLoading()
//            result.fold(
//                onSuccess = {
//                    onStripeIntentClientSecretResponse(
//                        it,
//                        paymentMethod
//                    )
//                },
//                onFailure = ::displayError
//            )
//        }
//    }
//
//    private fun onStripeIntentClientSecretResponse(
//        response: JSONObject,
//        paymentMethod: PaymentMethod?
//    ) {
//        if (response.has("success")) {
//            val success = response.getBoolean("success")
//            if (success) {
//                finishPayment()
//            } else {
//                displayError("Payment failed")
//            }
//        } else {
//            val clientSecret = response.getString("secret")
//            when {
//                clientSecret.startsWith("pi_") ->
//                    retrievePaymentIntent(clientSecret, paymentMethod)
//                clientSecret.startsWith("seti_") ->
//                    retrieveSetupIntent(clientSecret, paymentMethod)
//                else -> throw IllegalArgumentException("Invalid client_secret: $clientSecret")
//            }
//        }
//    }
//
//    private fun retrievePaymentIntent(
//        clientSecret: String,
//        paymentMethod: PaymentMethod?
//    ) {
//        startLoading()
//        viewModel.retrievePaymentIntent(
//            clientSecret
//        ).observe(this) { result ->
//            stopLoading()
//            result.fold(
//                onSuccess = {
//                    processStripeIntent(
//                        it,
//                        isAfterConfirmation = false,
//                        paymentMethod = paymentMethod
//                    )
//                },
//                onFailure = ::displayError
//            )
//        }
//    }
//
//    private fun retrieveSetupIntent(
//        clientSecret: String,
//        paymentMethod: PaymentMethod?
//    ) {
//        startLoading()
//        viewModel.retrieveSetupIntent(
//            clientSecret
//        ).observe(this) { result ->
//            stopLoading()
//            result.fold(
//                onSuccess = {
//                    processStripeIntent(
//                        it,
//                        isAfterConfirmation = false,
//                        paymentMethod = paymentMethod
//                    )
//                },
//                onFailure = ::displayError
//            )
//        }
//    }
//
//    private fun finishPayment() {
//        finishWithResult(
//            CheckOutContract.Result.PaymentIntent(
//                storeCart.total_price + shippingCosts
//            )
//        )
//    }
//
//    private fun finishSetup() {
//        finishWithResult(CheckOutContract.Result.SetupIntent)
//    }
//
//    private fun finishWithResult(result: CheckOutContract.Result) {
//        paymentSession.onCompleted()
//        setResult(
//            Activity.RESULT_OK,
//            Intent()
//                .putExtra(CheckOutContract.EXTRA_RESULT, result)
//        )
//        finish()
//    }
//
//    private fun initPaymentSession() {
//        paymentSession.init(PaymentSessionListenerImpl(this))
//        paymentSession.setCartTotal(storeCart.total_price)
//    }
//
////    private fun startLoading() {
////        viewBinding.progressBar.isVisible = true
////        viewBinding.buttonAddPaymentMethod.isEnabled = false
////        viewBinding.buttonAddShippingInfo.isEnabled = false
////
////        viewBinding.buttonConfirmPayment.tag = viewBinding.buttonConfirmPayment.isEnabled
////        viewBinding.buttonConfirmPayment.isEnabled = false
////
////        viewBinding.buttonConfirmSetup.tag = viewBinding.buttonConfirmSetup.isEnabled
////        viewBinding.buttonConfirmSetup.isEnabled = false
////    }
//
////    private fun stopLoading() {
////        viewBinding.progressBar.isInvisible = true
////        viewBinding.buttonAddPaymentMethod.isEnabled = true
////        viewBinding.buttonAddShippingInfo.isEnabled = true
////
////        viewBinding.buttonConfirmPayment.isEnabled =
////            java.lang.Boolean.TRUE == viewBinding.buttonConfirmPayment.tag
////        viewBinding.buttonConfirmSetup.isEnabled =
////            java.lang.Boolean.TRUE == viewBinding.buttonConfirmSetup.tag
////    }
//
//    private fun getPaymentMethodDescription(paymentMethod: PaymentMethod): String {
//        return when (paymentMethod.type) {
//            PaymentMethod.Type.Card -> {
//                paymentMethod.card?.let {
//                    "${it.brand.displayName}-${it.last4}"
//                }.orEmpty()
//            }
//            PaymentMethod.Type.Fpx -> {
//                paymentMethod.fpx?.let {
//                    "${getDisplayName(it.bank)} (FPX)"
//                }.orEmpty()
//            }
//            else -> ""
//        }
//    }
//
//    private fun getDisplayName(name: String?): String {
//        return (name.orEmpty())
//            .split("_")
//            .joinToString(separator = " ") { it.capitalize(Locale.ROOT) }
//    }
//
//    private fun onPaymentSessionDataChanged(data: PaymentSessionData) {
//        paymentSessionData = data
//
//        viewBinding.buttonConfirmPayment.isEnabled = data.isPaymentReadyToCharge
//        viewBinding.buttonConfirmSetup.isEnabled = data.isPaymentReadyToCharge
//
//        data.shippingMethod?.let { shippingMethod ->
//            viewBinding.buttonAddShippingInfo.text = shippingMethod.label
//            shippingCosts = shippingMethod.amount
//        }
//
//        paymentSession.setCartTotal(totalPrice)
//        updateCartItems(totalPrice.toInt(), shippingCosts.toInt())
//        updateConfirmPaymentButton(totalPrice)
//
//        when {
//            data.useGooglePay -> {
//                updateForGooglePay()
//            }
//            else -> {
//                data.paymentMethod?.let { paymentMethod ->
//                    viewBinding.buttonAddPaymentMethod.text =
//                        getPaymentMethodDescription(paymentMethod)
//                }
//            }
//        }
//    }
//
//    private fun updateForGooglePay() {
//        viewBinding.buttonAddPaymentMethod.text = getString(R.string.google_pay)
//    }
//
//    private fun getDisplayPrice(currencySymbol: String, price: Int): String {
//        return currencySymbol + PayWithGoogleUtils.getPriceString(price, storeCart.currency)
//    }
//
//    /**
//     * Launch the Google Pay sheet
//     */
//    private fun payWithGoogle() {
//        startLoading()
//        AutoResolveHelper.resolveTask(
//            paymentsClient.loadPaymentData(
//                PaymentDataRequest.fromJson(
//                    googlePayJsonFactory.createPaymentDataRequest(
//                        transactionInfo = GooglePayJsonFactory.TransactionInfo(
//                            currencyCode = "USD",
//                            totalPrice = storeCart.total_price.toInt(),
//                            totalPriceStatus = GooglePayJsonFactory.TransactionInfo.TotalPriceStatus.Final
//                        ),
//                        merchantInfo = GooglePayJsonFactory.MerchantInfo(
//                            merchantName = getString(R.string.app_name)
//                        ),
//                        shippingAddressParameters = GooglePayJsonFactory.ShippingAddressParameters(
//                            isRequired = true,
//                            allowedCountryCodes = setOf("US"),
//                            phoneNumberRequired = true
//                        ),
//                        billingAddressParameters = GooglePayJsonFactory.BillingAddressParameters(
//                            isRequired = true
//                        )
//                    ).toString()
//                )
//            ),
//            this@PaymentFragment,
//            LOAD_PAYMENT_DATA_REQUEST_CODE
//        )
//    }
//
//    private class PaymentSessionListenerImpl constructor(
//        activity: PaymentFragment
//    ) : PaymentSession.PaymentSessionListener {
//
//        private val activityRef = WeakReference(activity)
//
//        override fun onCommunicatingStateChanged(isCommunicating: Boolean) {}
//
//        override fun onError(errorCode: Int, errorMessage: String) {
//            activityRef.get()?.displayError(errorMessage)
//        }
//
//        override fun onPaymentSessionDataChanged(data: PaymentSessionData) {
//            activityRef.get()?.onPaymentSessionDataChanged(data)
//        }
//    }
//
//    private class ShippingInfoValidator : PaymentSessionConfig.ShippingInformationValidator {
//        override fun getErrorMessage(shippingInformation: ShippingInformation): String {
//            return "A US address is required"
//        }
//
//        override fun isValid(shippingInformation: ShippingInformation): Boolean {
//            return Locale.US.country == shippingInformation.address?.country
//        }
//    }
//
//    private class ShippingMethodsFactory : PaymentSessionConfig.ShippingMethodsFactory {
//        override fun create(shippingInformation: ShippingInformation): List<ShippingMethod> {
//            val isCourierSupported = "94110" == shippingInformation.address?.postalCode
//            val currency = Currency.getInstance(StripeSettings.CURRENCY.toUpperCase(Locale.ROOT))
//            val courierMethod = if (isCourierSupported) {
//                ShippingMethod(
//                    label = "1 Hour Courier",
//                    identifier = "courier",
//                    detail = "Arrives in the next hour",
//                    amount = 1099,
//                    currency = currency
//                )
//            } else {
//                null
//            }
//            return listOfNotNull(
//                ShippingMethod(
//                    label = "UPS Ground",
//                    identifier = "ups-ground",
//                    detail = "Arrives in 3-5 days",
//                    amount = 0,
//                    currency = currency
//                ),
//                ShippingMethod(
//                    label = "FedEx",
//                    identifier = "fedex",
//                    detail = "Arrives tomorrow",
//                    amount = 599,
//                    currency = currency
//                ),
//                courierMethod
//            )
//        }
//    }
//
//    private companion object {
//        private const val LOAD_PAYMENT_DATA_REQUEST_CODE = 5000
//
//        private val DEFAULT_SHIPPING_INFO = ShippingInformation(
//            Address.Builder()
//                .setCity("San Francisco")
//                .setCountry("US")
//                .setLine1("123 Market St")
//                .setLine2("#345")
//                .setPostalCode("94107")
//                .setState("CA")
//                .build(),
//            "Fake Name",
//            "(555) 555-5555"
//        )
//    }
//}



}