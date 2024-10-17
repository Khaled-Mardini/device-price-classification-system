package com.maids.device_price_classification_backend.device;

import java.util.List;
import java.io.IOException;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import com.maids.device_price_classification_backend.http.HttpClient;

@Service
public class DeviceService {
    @Autowired
    DeviceRepository deviceRepository;
    HttpClient httpClient = new HttpClient();
    ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    public List<Device> fetch() {
        return deviceRepository.findAll();
    }

    public Page<Device> fetch(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    public Device fetch(Long id) {
        return deviceRepository.findById(id).orElseThrow();
    }

    public Device create(Device device) {
        return deviceRepository.save(device);
    }

    public String delete(Long id) {
        if (deviceRepository.existsById(id)) {
            deviceRepository.deleteById(id);
            return "{\n\t\"message\": Device Deleted\n}";
        }
        return "{\n\t\"message\": Device didn't found\n}";
    }

    @Transactional
    public String predict(Long id) throws IOException {

        Device device = deviceRepository.findById(id).orElseThrow();

        // Send a POST request to the prediction API endpoint
        String response = httpClient.post("http://127.0.0.1:5000/api/predict", mapper.writeValueAsString(device));

        // Check if the API request failed.
        if (response.contains("failed"))
            return response;

        // Extract the predicted price range from the JSON response
        JsonNode json = mapper.readTree(response);
        int price = json.get("prediction").asInt();

        // Update and save the Predicted Price Range into Database
        device.setPrice_range(price);
        deviceRepository.save(device);

        return "{\n\t\"Prediction\": " + price + "\n\t\"message\": Database Updated\n}";
    }

}
