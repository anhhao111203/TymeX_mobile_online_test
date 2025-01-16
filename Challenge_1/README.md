# Currency Converter Application

A modern **Currency Converter App** built using Kotlin,Jetpack Compose and ExchangeRateAPI, which allows users to convert between currencies. 

---

## Features

- 🌍 **Live Currency Conversion**: Convert between various currencies based on real-time exchange rates.
- 📱 **Intuitive UI**: Simple and user-friendly interface designed with Jetpack Compose.
- 🛠 **Offline Support**: Provides feedback for network issues and retries fetching data.
- 🔄 **Currency Swapping**: Swap between source and target currencies with a single tap.

---

## Video Demo

Check out the demo video of the app:

[Currency Converter App Demo](https://www.youtube.com/shorts/9gChdLadip4)

## How to Run

1. Clone the repository.
2. Open in Android Studio.
3. Build and run the app.

## Implementation Details

### **Key Components**
- **Live Exchange Rates**:
  - Integrated `ViewModel` to fetch and manage exchange rate data.
  - Handled network connectivity changes using `isNetworkAvailable()`.
- **Composable UI**:
  - Designed modular components for dropdowns, text fields, and buttons using Jetpack Compose.
