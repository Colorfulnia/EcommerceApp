package com.tao.phonewebdemo.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.tao.phonewebdemo.R
import com.tao.phonewebdemo.databinding.ActivityOrdersBinding
import com.tao.phonewebdemo.model.local.AppDatabase
import com.tao.phonewebdemo.model.local.CartDao
import com.tao.phonewebdemo.model.local.Order
import com.tao.phonewebdemo.model.local.OrderDao
import com.tao.phonewebdemo.view.adapter.OrderAdapter

class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var orderDao: OrderDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerCheckout.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        initDatabase()
        val data = cartDao.getAllCart()
        val cartAdapter = OrderAdapter(ArrayList(data))

        binding.recyclerCheckout.adapter = cartAdapter

        var orderDetails = "\n"
        var price = 0
        for (i in data) {
            orderDetails = "${i.name + i.price + i.quantity}" + orderDetails
            price += (i.price?.toInt() ?: 0) * (i.quantity?.toInt() ?: 0)
        }

        val name = binding.NameOrder.text.toString()
        val number = binding.PhoneNumber.text.toString()
        val address = binding.addressOrder.text.toString()
        val cardNumber = binding.CardNumber.text.toString()
        val cardName = binding.CardName.text.toString()
        val cvv = binding.CVV.text.toString()
        val exp = binding.expiryDate.text.toString()

        orderDao.insertOrder(Order(orderDetails = orderDetails, orderid = 1, price = price.toString(), personName = name, address = address, cardnumber = cardNumber, cardName = cardName, cvv = cvv, expdate = exp, personNumber = number))

        binding.completeOrder.setOnClickListener {
            Toast.makeText(this, "ORDER SUCCESSFULLY PLACED", Toast.LENGTH_SHORT).show()

        }
    }

    private fun initDatabase() {
        appDatabase = AppDatabase.getAppDatabase(applicationContext)!!
        cartDao = appDatabase.getCartDao()
        orderDao = appDatabase.getOrderDao()
    }
}