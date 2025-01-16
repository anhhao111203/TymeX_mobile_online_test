package com.example.currency_converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currency_converter.data.network_check.isNetworkAvailable
import com.example.currency_converter.viewmodel.ExchangeRateViewModel

@Composable
fun CurrencyConverterApp(
    modifier: Modifier = Modifier, // Optional parameter for applying layout and styling
    viewModel: ExchangeRateViewModel, // ViewModel to manage exchange rate data
    context: Context // Context for displaying Toast messages
) {
    // State variable to track whether the source currency dropdown is expanded:
    var expandedSource by remember { mutableStateOf(false) }

    // State variable to track whether the target currency dropdown is expanded:
    var expandedTarget by remember { mutableStateOf(false) }

    // State variable to store the amount to be converted (input by the user):
    var fromAmount by remember { mutableStateOf("") }

    // State variable to store the currently selected source currency (default: USD):
    var selectedSourceCurrency by remember { mutableStateOf("USD") }

    // State variable to store the currently selected target currency (default: VND):
    var selectedTargetCurrency by remember { mutableStateOf("VND") }

    // State variable to store the result of the currency conversion:
    var convertedAmount by remember { mutableStateOf("") }

    // Collects the exchange rates from the ViewModel as a state to be used in the UI:
    val exchangeRates by viewModel.exchangeRates.collectAsState()

    // Collects any error messages from the ViewModel as a state:
    val error by viewModel.error.collectAsState()

    // State variable to track the network connectivity status:
    var isConnected by remember { mutableStateOf(isNetworkAvailable(context)) }

    // Trigger refresh when network changes or on click:
    LaunchedEffect(isConnected) {
        if (isConnected) {
            viewModel.fetchExchangeRates()
        }
    }

    // List of currency codes to be used in the dropdown menus:
    val currencyCodeList = exchangeRates.keys.toList()

    Box(
        modifier = modifier
            .background(color = Color.Gray.copy(alpha = 0.1f))
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // If there's an error, display a message with a refresh button:
        if (error != null || !isConnected) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No internet connection. Please connect to the internet.",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    onClick = {
                        isConnected = isNetworkAvailable(context)  // Recheck network on refresh
                        if (isConnected) {
                            viewModel.fetchExchangeRates()
                            Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = "Refresh")
                }
            }
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                // App Title
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.money),
                        contentDescription = "Money Converter Image",
                        modifier = modifier.size(100.dp)
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Text(
                        text = "Currency Converter",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = "Convert your money easily and check live exchange rates",
                        fontSize = 16.sp,
                        color = Color.Gray.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }

                // Exchange Card
                Column(
                    modifier = modifier.padding(16.dp)
                ) {
                    // From currency and amount input
                    Column(modifier = modifier.padding(16.dp)) {
                        Row(
                            modifier = modifier.padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "From",
                                fontSize = 16.sp,
                                color = Color.Gray.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier.clickable { expandedSource = true }
                            ) {
                                Text(
                                    text = selectedSourceCurrency,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.down_arrow),
                                    contentDescription = "Down Arrow Icon",
                                    modifier = modifier.size(24.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = expandedSource,
                                onDismissRequest = { expandedSource = false },
                                modifier = modifier
                                    .size(300.dp)
                                    .background(color = Color.White)
                            ) {
                                currencyCodeList.forEach { code ->
                                    DropdownMenuItem(
                                        text = { Text(text = code) },
                                        onClick = {
                                            selectedSourceCurrency = code
                                            expandedSource = false

                                            val amount = fromAmount.toDoubleOrNull()
                                            if (amount != null) {
                                                convertedAmount = viewModel.calculateConvertedAmount(
                                                    amount,
                                                    selectedSourceCurrency,
                                                    selectedTargetCurrency
                                                )?.toString() ?: ""
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        TextField(
                            modifier = modifier.fillMaxWidth(),
                            value = fromAmount,
                            onValueChange = { input ->
                                fromAmount = input
                                val amount = input.toDoubleOrNull()
                                convertedAmount = if (amount != null) {
                                    viewModel.calculateConvertedAmount(
                                        amount,
                                        selectedSourceCurrency,
                                        selectedTargetCurrency
                                    )?.toString() ?: ""
                                } else {
                                    ""
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                                unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    // Divider and Swap Button
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(
                            thickness = 1.dp,
                            modifier = modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {
                                val temp = selectedSourceCurrency
                                selectedSourceCurrency = selectedTargetCurrency
                                selectedTargetCurrency = temp

                                val amount = fromAmount.toDoubleOrNull()
                                if (amount != null) {
                                    convertedAmount = viewModel.calculateConvertedAmount(
                                        amount,
                                        selectedSourceCurrency,
                                        selectedTargetCurrency
                                    )?.toString() ?: ""
                                }
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.up_and_down_arrows),
                                contentDescription = "Up and down arrows icon",
                                modifier = modifier.size(40.dp)
                            )
                        }
                        Divider(
                            thickness = 1.dp,
                            modifier = modifier.weight(1f)
                        )
                    }
                    // To currency and amount input
                    Column(modifier = modifier.padding(16.dp)) {
                        Row(
                            modifier = modifier.padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "To",
                                fontSize = 16.sp,
                                color = Color.Gray.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier.clickable { expandedTarget = true }
                            ) {
                                Text(
                                    text = selectedTargetCurrency,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.down_arrow),
                                    contentDescription = "Down Arrow Icon",
                                    modifier = modifier.size(24.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = expandedTarget,
                                onDismissRequest = { expandedTarget = false },
                                modifier = modifier
                                    .size(300.dp)
                                    .background(color = Color.White)
                            ) {
                                currencyCodeList.forEach { code ->
                                    DropdownMenuItem(
                                        text = { Text(text = code) },
                                        onClick = {
                                            selectedTargetCurrency = code
                                            expandedTarget = false

                                            val amount = fromAmount.toDoubleOrNull()
                                            if (amount != null) {
                                                convertedAmount = viewModel.calculateConvertedAmount(
                                                    amount,
                                                    selectedSourceCurrency,
                                                    selectedTargetCurrency
                                                )?.toString() ?: ""
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        TextField(
                            modifier = modifier.fillMaxWidth(),
                            value = convertedAmount,
                            readOnly = true,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                                unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
        }
    }
}