# Import necessary libraries
import joblib
import pandas as pd
from flask import Flask, request, jsonify

# Initialize the Flask app
app = Flask(__name__)

# Load the pre-trained machine learning model (pipeline)
pipeline = joblib.load('model_pipeline.pkl')


# Define an API endpoint for making predictions
@app.route('/api/predict', methods=['POST'])
def predict():
    # Get the data from the POST request, and force it to be parsed as JSON
    data = request.get_json(force=True)

    # Convert the incoming JSON data into a DataFrame (with one row)
    data = pd.DataFrame([data])

    # Drop the columns that should not be used for prediction
    ignore_columns = ['id', 'clock_speed', 'dual_sim', 'fc', 'n_cores', 'pc',
                      'sc_h', 'sc_w', 'talk_time', 'three_g', 'price_range', 'createdAt']
    data = data.drop(columns=ignore_columns)

    # Make predictions with the loaded pipeline
    prediction = pipeline.predict(data)

    # Return the prediction as a JSON response
    return jsonify({'prediction': int(prediction[0])})


# Run the Flask app
if __name__ == "__main__":
    app.run(debug=True)
