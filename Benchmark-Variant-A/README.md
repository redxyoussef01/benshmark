# Benchmark Project

This repository contains benchmark performance testing results across different scenarios and variants.

## Project Structure

- **Benchmark-Variant-A**: Java JAX-RS benchmark application (Variant A)
- **benchmarkD**: Benchmark variant D application
- **benchmarkR**: Benchmark variant R application
- **JMeter Test Files**: Load testing configurations
- **Benchmark Results**: Performance test images and reports

## Benchmark Results

### Scenario 1 - READ-heavy Operations

#### Variant A
![S1-VA-1](S1-VA-1.png)
![S1-VA-2](S1-VA-2.png)
![S1-VA-3](S1-VA-3.png)

#### Variant C
![S1-VC-1](S1-VC-1.png)
![S1-VC-2](S1-VC-2.png)
![S1-VC-3](S1-VC-3.png)

#### Variant D
![S1-VD-1](S1-VD-1.png)
![S1-VD-2](S1-VD-2.png)
![S1-VD-3](S1-VD-3.png)

### Scenario 2 - Mixed Operations

#### Variant A
![S2-VA-1](S2-VA-1.png)
![S2-VA-2](S2-VA-2.png)
![S2-VA-3](S2-VA-3.png)

#### Variant C
![S2-VC-1](S2-VC-1.png)
![S2-VC-2](S2-VC-2.png)
![S2-VC-3](S2-VC-3.png)

#### Variant D
![S2-VD-1](S2-VD-1.png)
![S2-VD-2](S2-VD-2.png)
![S2-VD-3](S2-VD-3.png)

### Scenario 3 - Heavy Operations

#### Variant A
![S3-VA-1](s3-va-1.png)
![S3-VA-2](s3-va-2.png)
![S3-VA-3](s3-va-3.png)

#### Variant C
![S3-VC-1](S3-vc-1.png)
![S3-VC-2](S3-vc-2.png)
![S3-VC-3](S3-vc-3.png)

#### Variant D
![S3-VD-1](s3-vd-1.png)
![S3-VD-2](s3-vd-2.png)
![S3-VD-3](s3-vd-3.png)

## JMeter Test Files

- `Scenario 1 - READ-heavy.jmx` - Read-heavy operation tests
- `HEAVY-body (Paliers 30-60).jmx` - Heavy body tests with load levels 30-60
- `MIXED (Paliers 50-100).jmx` - Mixed operation tests with load levels 50-100
- `items.jmx` - Item-related tests
- `JOIN-filter .jmx` - Join filter tests

## Test Data Files

- `item_ids.csv` - Item identifiers for testing
- `category_ids.csv` - Category identifiers for testing
- `payload_item_1k.csv` - 1K item payload dataset
- `payload_item_5k.csv` - 5K item payload dataset
- `payload_category_1k.csv` - 1K category payload dataset

## Reports

- `Rapport_Benchmark.pdf` - Comprehensive benchmark report

## Variants

This project tests three different implementation variants:

- **Variant A**: Initial implementation in `Benchmark-Variant-A/`
- **Variant C**: Alternative implementation in `benchmarkR/`
- **Variant D**: Alternative implementation in `benchmarkD/`

Each variant is tested across multiple scenarios with different load profiles to measure performance differences.

## Getting Started

### Build Instructions

#### Variant A
```bash
cd Benchmark-Variant-A
./mvnw clean package
```

#### Variant D
```bash
cd benchmarkD/benchmarkD
./mvnw clean package
```

#### Variant R
```bash
cd benchmarkR/benchmarkR
./mvnw clean package
```

## Performance Metrics

The benchmark tests measure:
- Response times
- Throughput
- Error rates
- Resource utilization

Results are captured in the image files organized by scenario and variant.

---

Generated for comparison of benchmark variants A, C, and D across three test scenarios.
