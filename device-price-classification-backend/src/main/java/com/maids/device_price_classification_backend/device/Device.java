package com.maids.device_price_classification_backend.device;

import org.hibernate.annotations.CreationTimestamp;

import lombok.experimental.Accessors;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int battery_power;

    @Column(nullable = false)
    private boolean blue;

    @Column(nullable = false)
    private float clock_speed;

    @Column(nullable = false)
    private boolean dual_sim;

    @Column(nullable = false)
    private int fc;

    @Column(nullable = false)
    private boolean four_g;

    @Column(nullable = false)
    private int int_memory;

    @Column(nullable = false)
    private float m_dep;

    @Column(nullable = false)
    private int mobile_wt;

    @Column(nullable = false)
    private int n_cores;

    @Column(nullable = false)
    private int pc;

    @Column(nullable = false)
    private int px_height;

    @Column(nullable = false)
    private int px_width;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int sc_h;

    @Column(nullable = false)
    private int sc_w;

    @Column(nullable = false)
    private int talk_time;

    @Column(nullable = false)
    private boolean three_g;

    @Column(nullable = false)
    private boolean touch_screen;

    @Column(nullable = false)
    private boolean wifi;

    @Builder.Default
    private int price_range = -1;

    @CreationTimestamp
    Instant createdAt;
}
