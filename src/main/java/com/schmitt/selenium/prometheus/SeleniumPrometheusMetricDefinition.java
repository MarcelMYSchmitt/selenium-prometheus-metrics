package com.schmitt.selenium.prometheus;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class SeleniumPrometheusMetricDefinition {

	private final AtomicLong loginAvailabilityStatus = new AtomicLong();

	public SeleniumPrometheusMetricDefinition(MeterRegistry registry) {

		Gauge.builder("LOGIN_AVAILABILITY", this, value -> loginAvailabilityStatus.get())
				.description("status of login provider").register(registry);

	}

	public void setLoginAvailabilityStatus(int value) {
		this.loginAvailabilityStatus.set(value);
	}

	public long getLoginAvailabilityStatus() {
		return this.loginAvailabilityStatus.get();
	}
}