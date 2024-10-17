package com.maids.device_price_classification_backend.device;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping
    public Page<Device> fetch(Pageable pageable) {
        return deviceService.fetch(pageable);
    }

    @GetMapping("/{id}")
    public Device fetch(@PathVariable Long id) {
        return deviceService.fetch(id);
    }

    @PostMapping
    public Device create(@RequestBody Device device) {
        return deviceService.create(device);
    }

    @PostMapping("predict/{id}")
    public String predict(@PathVariable Long id) throws IOException {
        return deviceService.predict(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return deviceService.delete(id);
    }
}
