# Device Price Classification System

This project is a **Device Price Classification System** with a RESTful API built using **Spring Boot** for managing mobile devices and interacting with a **Python-based machine learning model** for predicting the price range of devices based on their specifications.

## Table of Contents

- [Project Overview](#project-overview)
- [Model Overview](#model-overview)
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Backend (Java Spring Boot)](#backend-java-spring-boot)
  - [Python API (Machine Learning)](#python-api-machine-learning)
- [Running the Application](#running-the-application)
  - [Running the Spring Boot Application](#running-the-spring-boot-application)
  - [Running the Python Prediction API](#running-the-python-prediction-api)
- [API Endpoints](#api-endpoints)
- [API Usage](#api-usage)
- [Testing](#Testing)
- [License](#license)

## Project Overview

This system provides functionality for:

- CRUD operations to manage mobile device information (such as battery power, RAM, processor details, etc.).
- Integration with a Python-based machine learning model for price prediction based on the device's technical specifications.
- RESTful API for device management and price prediction.

The price prediction is handled by a separate Python API using a machine learning model, and the predicted price range is saved back into the Spring Boot application's database.

## Model Overview

The machine learning model used in this project is based on **Logistic Regression** and has been optimized through manual feature selection and scaling. The model achieves an accuracy of **99%** on the test data, ensuring highly reliable predictions for device price ranges.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete devices.
- **Price Prediction**: Predict the price range of a device by calling a Python machine learning API.
- **API Documentation**: Clear RESTful API to interact with devices and price predictions.
- **Modular Setup**: Java for backend and Python for machine learning make the system modular and flexible.

## Architecture

The project is divided into two main parts:

1. **Java Spring Boot Backend**: Manages device data, handles CRUD operations, and makes HTTP requests to the Python API for price prediction.
2. **Python API**: Uses a machine learning model to predict the price range of a device based on its features. The model is trained on a dataset of mobile devices.

## Prerequisites

### Backend (Spring Boot)

- **Java 11** or higher.
- **Maven** to manage dependencies.
- **Spring Boot** ensure that you have a Spring Boot environment set up.
- **MySQL** database.

### Machine Learning Model (Python)

- **Python 3.x** installed with the following libraries:
  - `Flask` - for the API.
  - `pandas` - for data manipulation.
  - `scikit-learn` - for model prediction.
  - `joblib` - for saving and loading the model pipeline.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/khaledmardini/device-price-classification-system.git
   ```

### Backend (Java Spring Boot)

2. Install the necessary dependencies:

   ```bash
   mvn clean install
   ```

3. Set up the database (MySQL) and configure the `application.properties` file.

### Python API (Machine Learning)

1. Set up a virtual environment for the Python project:

   ```bash
   python3 -m venv venv
   source venv/bin/activate  # For Windows, use venv\Scripts\activate
   ```

2. Install the dependencies:

   ```bash
   pip install -r requirements.txt
   ```

## Running the Application

### Running the Python Prediction API

1. Activate your virtual environment and run the Python API:

   ```bash
   python prediction.py
   ```

2. The Python API will start at `http://127.0.0.1:5000`.

### Running the Spring Boot Application

1. Run the Spring Boot application using Maven:

   ```bash
   mvn spring-boot:run
   ```

2. The application will start on `http://localhost:8080`.

## API Endpoints

### 1. **GET /api/devices/**

Retrieve a list of all devices stored in the system.

### 2. **GET /api/devices/{id}**

Retrieve details of a specific device by its `id`.

### 3. **POST /api/devices**

Add a new device to the system.

### 4. **POST /api/devices/predict/{id}**

Predict the price range of a device and update the `price_range` field in the database.

### 5. **DELETE /api/devices/{id}**

Delete a specific device from the system.

## API Usage

You can use **Postman**, **curl**, or any API testing tool to interact with the endpoints.

### Example: Get All Devices

```bash
curl -X GET http://localhost:8080/api/devices
```

### Example: Predict Price for a Device

```bash
curl -X POST http://localhost:8080/api/devices/predict/1
```

### Example: Add a New Device

```bash
curl -X POST http://localhost:8080/api/devices \
-H "Content-Type: application/json" \
-d '{"battery_power": 1500, "blue": 1, "clock_speed": 2.3, "dual_sim": 1, "int_memory": 64, "ram": 4096}'
```

## Testing

Prediction for the first 10 devices from the Test dataset

| Id  | Prediction |
| --- | ---------- |
| 1   | 3          |
| 2   | 3          |
| 3   | 2          |
| 4   | 3          |
| 5   | 1          |
| 6   | 3          |
| 7   | 3          |
| 8   | 1          |
| 9   | 3          |
| 10  | 0          |

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
